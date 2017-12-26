package com.couchCamelOfbiz.customProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.couchCamelOfbiz.pojo.AdjustmentList;
import com.couchCamelOfbiz.pojo.ItemList;
import com.couchCamelOfbiz.pojo.JsonUtil;
import com.couchCamelOfbiz.pojo.OrderPojo;
import com.couchCamelOfbiz.pojo.PaymentList;

public class JsonToCsvProcessor  implements Processor{
	
	private static String paymentMethodTypeIdCash=null;
	private static String paymentMethodTypeIdCreditCard=null;
    private String headers = "\"externalId\",\"productStoreId\",\"customerId\",\"cash\",\"externalCreditcCard\",\"giftCard\",\"orderTypeId\",\"orderItemTypeId\",\"productPricePurposeId\",\"entryDate\",\"grandTotal\",\"productId\",\"productName\",\"quantity\",\"unitListPrice\",\"unitPrice\",\"upc\",\"orderId\",\"remainingAmount\",\"totalQuantity\",\"placingCustomerPartyId\",\"endUserCustomerPartyId\",\"shipToCustomerPartyId\",\"billToCustomerPartyId\",\"billFromVendorPartyId\"";
	private static boolean flagHeader = true; 
//	private static boolean flagExchange = true; 
	
//Main exchange method
public void process(Exchange exchange)throws Exception{
		String jsonString = exchange.getIn().getBody(String.class);
		System.out.println("Sending string to convert:");
		exchange.getOut().setBody(convertJsonToCSV(jsonString).toString());
		System.out.println("Send exchange out:");
	}

//Convert method
public StringBuilder convertJsonToCSV(String jsonStringArgs){
	
	System.out.println("convertJsonToCSV invoked:");	
	
	StringBuilder csvFile = new StringBuilder();
	
	if(flagHeader){
	System.out.println("FlagHeader 1:"+flagHeader);	
	csvFile.append(headers).append(System.getProperty("line.separator"));		
    flagHeader = false;
    System.out.println("FlagHeader 2:"+flagHeader);
	}
	
	//Get data from exchange.
	System.out.println("Sending String to JsonUtil:");
	OrderPojo orderGetSet = JsonUtil.convertJsonToJava(jsonStringArgs,OrderPojo.class);
	System.out.println("Sending and successfully converted:"); 
    
	//PaymentList
    for(PaymentList paymentObject:orderGetSet.getPaymentList()){
	//Set variable
    if(paymentObject.getPaymentMethodTypeId().equals("CASH")||paymentObject.getPaymentMethodTypeId().equals("EXTERNAL_CREDIT_CARD"))  
    {   
    	if(paymentObject.getPaymentMethodTypeId().equals("CASH")){
    	JsonToCsvProcessor.paymentMethodTypeIdCash = paymentObject.getPaymentMethodTypeId();
    	}else{
        JsonToCsvProcessor.paymentMethodTypeIdCreditCard = paymentObject.getPaymentMethodTypeId();   
    	     }	
    }	
	} 
    
        
    //ItemList  
    for(ItemList itemObject:orderGetSet.getItemList()){
    	       
   	csvFile.append("\n\"").append(orderGetSet.getId()).append("\",");   	    
    //Dummy 
    csvFile.append("\"POS_STORE\",");
    csvFile.append("\"").append(orderGetSet.getCustomerId()).append("\",");  
    //PaymentList    
    csvFile.append("\"").append(paymentMethodTypeIdCash).append("\",");
    csvFile.append("\"").append(paymentMethodTypeIdCreditCard).append("\",");                
    //Gift
    csvFile.append("  ,");
    csvFile.append("\"SALES_ORDER\",");
    csvFile.append("\"PRODUCT_ORDER_ITEM\",");
    csvFile.append("\"PURCHASE\",");
   
    //Entry date
    csvFile.append("\"").append(orderGetSet.getEntryDate()).append("\",");
    csvFile.append(orderGetSet.getGrandTotal()).append(",");
    
    //Item list
    csvFile.append("\"").append(itemObject.getProductId()).append("\",");
    csvFile.append("\"").append(itemObject.getProductName()).append("\",");
    csvFile.append("").append(itemObject.getQuantity()).append(",");
    csvFile.append("").append(itemObject.getUnitListPrice()).append(",");
    csvFile.append(itemObject.getUnitPrice()).append(",");
    csvFile.append("\"").append(itemObject.getUpc()).append("\",");
    		   
    csvFile.append("\"").append(orderGetSet.getOrderId()).append("\",");
    csvFile.append(orderGetSet.getRemainingAmount()).append(",");
    csvFile.append(orderGetSet.getTotalQuantity()).append(",");
    	   	   
    //Dummy
    csvFile.append("\"PLACING_CUSTOMER_PARTY_ID\",");
    csvFile.append("\"END_USER_CUSTOMER_PARTY_ID\",");
    csvFile.append("\"SHIP_TO_CUSTOMER_PARTY_ID\",");
    csvFile.append("\"BILL_TO_CUSTOMER_PARTY_ID\",");
    csvFile.append("\"BILL_FROM_VENDOR_PARTY_ID\"");
    csvFile.append(System.getProperty("line.separator"));   	      
    }
    //AdjustmentList
    if(!(orderGetSet.getAdjustmentList().isEmpty())){
    	  
        for(AdjustmentList adjustObject:orderGetSet.getAdjustmentList()){
    	csvFile.append("\"").append(orderGetSet.getId()).append("\",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"").append(adjustObject.getAdjustmentType()).append("\",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append(adjustObject.getAdjustmentAmount()).append(",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \",").append("\"  \"");
//    	csvFile.append(System.getProperty("line.separator"));   	           
            }
     }
    return csvFile;
	}


}
