package com.couchCamelOfbiz;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import com.couchCamelOfbiz.customProcessor.JsonToCsvProcessor;
import com.couchCamelOfbiz.customProcessor.ProcessForTransport;
import com.couchCamelOfbiz.customProcessor.ProcessorForGetToken;
import com.couchCamelOfbiz.customProcessor.ProcessorSetFileRequest;

public class CouchCamelOfbizRoute extends RouteBuilder{
    @Override
    public void configure()throws Exception{

        System.out.println(":>>>>Start-Route<<<<<:");
        
//Route 1 Get the data from couch DB and convert to CSV.        
        from("couchdb:http://localhost:5984/order")
			  .startupOrder(1)
			  .process(new JsonToCsvProcessor())
			  .to("file:/home/r2/Desktop/ofBizFile?fileExist=append&fileName=orders-${date:now:dd-MM-yyyy}.csv");

//Route 2 We are creating another route, because we want to save all the orders first in CSV, then process this single CSV.
        from("file:/home/r2/Desktop/ofBizFile?delay=1000&initialDelay=10000")
        	 .startupOrder(2)
//Copy the sending file into back-up folder.
        	 .to("file:/home/r2/Desktop/ofBizFile/backUp?fileExist=append&fileName=orders-${date:now:dd-MM-yyyy'T'HH:mm:ss}.csv")
//This process will connect and pass the exchange to the 3rd route via direct.        	 
        	 .process(new ProcessForTransport())
//This below part of code working independently to get token but not able to set token in exchange header.        	 
        	 .setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
			 .to("https4://hc-india.hotwax.co/api/control/getAuthenticationToken?throwExceptionOnFailure=false")
			 .process(new ProcessorForGetToken());

//Route 3 will responsible for send file to server with request response. 
        from("direct:sendFileExchange") 
                .startupOrder(3)
				.process(new ProcessorSetFileRequest())
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
				.to("https4://hc-india.hotwax.co/api/control/uploadAndImportFileFromCSVFile?throwExceptionOnFailure=false")
				.to("stream:out");

    }
}

