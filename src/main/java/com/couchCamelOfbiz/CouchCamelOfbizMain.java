package com.couchCamelOfbiz;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class CouchCamelOfbizMain{
	
	
   public static void main(String args[])throws Exception{

           CamelContext context=new DefaultCamelContext();
           context.setStreamCaching(true);
           context.addRoutes(new CouchCamelOfbizRoute());

  try{       
           context.start();
           Thread.sleep(1000000);
           context.stop();
           
    }catch(Exception e){
	       context.stop();
	       System.out.println(":Exception catched in main class 'CouchCamelOfbizMain':");
	       e.printStackTrace();
}
   }
}
