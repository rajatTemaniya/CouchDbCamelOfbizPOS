package customProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import pojo.JsonUtil;
import pojo.TokenPojo;

public class ProcessorForGetToken implements Processor {

    //Get the token from the server and not passing anywhere.
    public void process(Exchange exchange)throws Exception {
        String jsonTokenString = exchange.getIn().getBody(String.class);
        String token="";
        TokenPojo tokenGetSet = JsonUtil.convertJsonToJava(jsonTokenString,TokenPojo.class);
        token = tokenGetSet.getToken();
        exchange.getIn().setHeader("bearer",token);
    }
}
