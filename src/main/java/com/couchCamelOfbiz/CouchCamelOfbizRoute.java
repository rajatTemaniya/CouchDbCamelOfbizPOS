package com.couchCamelOfbiz;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import com.couchCamelOfbiz.customProcessor.JsonToCsvProcessor;
import com.couchCamelOfbiz.customProcessor.ProcessorForGetToken;
import com.couchCamelOfbiz.customProcessor.ProcessorGetToken;

public class CouchCamelOfbizRoute extends RouteBuilder{
    @Override
    public void configure()throws Exception{

        System.out.println(":>>>>Start-Route<<<<<:");
        

/*
//      Route 1                    
        from("couchdb:http://localhost:5984/order")
        		  .startupOrder(1)
                  .process(new JsonToCsvProcessor())
                  .to("file:/home/r2/Desktop/ofBizFile?fileExist=append&fileName=orders.csv")
                  .wireTap("direct:incoming");
                 
//	   Route 2
        from("direct:incoming")
        		 .startupOrder(2)
        		 .setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
       			 .to("https4://hc-india.hotwax.co/api/control/getAuthenticationToken?throwExceptionOnFailure=false")
       			 .process(new ProcessorGetToken())
       			 .to("direct:httpsHit");
      
//     Route3                 
       	from("direct:httpsHit")		 
       			.startupOrder(3)
       			.setHeader(Exchange.HTTP_METHOD, constant("POST"))
       			.setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
       			.to("https4://hc-india.hotwax.co/api/control/uploadAndImportFileFromCSVFile?throwExceptionOnFailure=false")
       			.to("stream:out")
       			.end();
*/       	
    
      /*
 
//   Route 1                    
        from("couchdb:http://localhost:5984/order")
        		  .startupOrder(1)
                  .process(new JsonToCsvProcessor())
                  .to("file:/home/r2/Desktop/ofBizFile?fileExist=append&fileName=orders-${in.header.CouchDbId}.csv")
                  ;
//	 Route 2
        from("file:/home/r2/Desktop/ofBizFile?noop=true")
//                 .startupOrder(200)
//        		 .autoStartup(false)
//                 .setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
//       			 .to("https4://hc-india.hotwax.co/api/control/getAuthenticationToken?throwExceptionOnFailure=false")
//       			 .process(new ProcessorGetToken())
       			 .to("http4://apimockery.com/mock/XTkipd")
//       			 .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
//       			 .setHeader(Exchange.HTTP_METHOD, constant("POST"))
//   	  	         .setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
//       			 .to("https4://hc-india.hotwax.co/api/control/uploadAndImportFileFromCSVFile?throwExceptionOnFailure=false")
       			 .to("stream:out");
       			 ;
       */
        
        from("couchdb:http://localhost:5984/order")
			.startupOrder(1)
			.process(new JsonToCsvProcessor())
			.to("file:/home/r2/Desktop/ofBizFile?fileExist=append&fileName=orders-${date:now:yyyyMMdd}.csv");
        
        from("file:/home/r2/Desktop/ofBizFile")
        	.startupOrder(2)
//        	.setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
//			.to("https4://hc-india.hotwax.co/api/control/getAuthenticationToken?throwExceptionOnFailure=false")
//			.process(new ProcessorForGetToken())
			.to("direct:incoming");
           
         from("direct:incoming")  
				.process(new ProcessorGetToken())
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
				.to("https4://hc-india.hotwax.co/api/control/uploadAndImportFileFromCSVFile?throwExceptionOnFailure=false")
				.to("stream:out");
 
        
       /* from("couchdb:http://localhost:5984/order")
			.startupOrder(1)
			.process(new JsonToCsvProcessor())
			.to("file:/home/r2/Desktop/ofBizFile?fileExist=append&fileName=orders-${in.header.random(5,10)}.csv")
			.setHeader(Exchange.HTTP_QUERY,constant("USERNAME=admin&PASSWORD=hotwax@786"))
			.to("https4://hc-india.hotwax.co/api/control/getAuthenticationToken?throwExceptionOnFailure=false")
			.process(new ProcessorGetToken())
//			.to("http4://apimockerockery.com/mock/Jd7qhL")
            .to("stream:out");
        */
        
        
        
       }
}

