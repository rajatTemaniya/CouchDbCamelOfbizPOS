package com.couchCamelOfbiz.customProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.couchCamelOfbiz.pojo.JsonUtil;
import com.couchCamelOfbiz.pojo.TokenPojo;

public class ProcessorForGetToken implements Processor{
	
	public void process(Exchange exchange)throws Exception{
		System.out.println("Start Token"+exchange.getIn().getBody(String.class));
		String jsonTokenString = exchange.getIn().getBody(String.class);
   		String token="";

    	TokenPojo tokenGetSet = JsonUtil.convertJsonToJava(jsonTokenString,TokenPojo.class);
        token = tokenGetSet.getToken();	
        exchange.getIn().setHeader("bearer",token);
	}
	
}
