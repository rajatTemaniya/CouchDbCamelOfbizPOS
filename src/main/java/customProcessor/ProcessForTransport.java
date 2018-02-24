package customProcessor;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

public class ProcessForTransport implements Processor {

    @Override
    //This process will send the file exchange to the 3rd route by direct component 
    public void process(Exchange exchange)throws Exception {

        CamelContext camelContext = exchange.getContext();
        ProducerTemplate producer = camelContext.createProducerTemplate();
        try {
            producer.send("direct:sendFileExchange",exchange);
        } catch (Exception e) {
            System.out.println("Exception occure :'ProcessForTransport':");
            e.printStackTrace();
        }
    }
}
