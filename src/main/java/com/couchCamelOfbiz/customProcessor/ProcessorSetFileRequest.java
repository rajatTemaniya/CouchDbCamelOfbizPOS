package com.couchCamelOfbiz.customProcessor;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class ProcessorSetFileRequest implements Processor {
     
     
	@SuppressWarnings({ "deprecation" })
	public void process(Exchange exchange) throws Exception {
		        //Get order Id from couchDb
		        System.out.println("Start-Last-Processor:");
		
    		    String token = exchange.getIn().getHeader("bearer",String.class);
                System.out.println("Token is:"+token);
    		    
                
    	        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//    		    File file =new File("//home/r2/Desktop/ofBizFile/orders-"+fileDate+".csv");
    	        File file =exchange.getIn().getBody(File.class);
            	String fileName = (String) exchange.getIn().getHeader(Exchange.FILE_NAME);

    	try{      
    	        builder.addPart("configId",new StringBody("IMP_ORDER"));
    	        builder.addPart("fileTypeEnumId",new StringBody("FILE_CSV"));
    	        builder.addPart("_uploadedFile_contentType",new StringBody("text/csv"));
                builder.addPart("uploadedFile", new FileBody(file, ContentType.MULTIPART_FORM_DATA, fileName));
               
    	   }catch(Exception e){
    		   System.out.println("Exception occure while adding 'uploadedFile':"+e);
    	   }
        

    	 exchange.getIn().setBody(builder.build());
    	 System.out.println("Exchange Invoked:>>");
	    
	}

}
