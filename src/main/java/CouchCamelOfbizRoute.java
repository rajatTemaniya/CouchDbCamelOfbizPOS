import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import customProcessor.JsonToCsvProcessor;
import customProcessor.ProcessForTransport;
import customProcessor.ProcessorForGetToken;
import customProcessor.ProcessorSetFileRequest;
import propertyClass.RouteProperty;

public class CouchCamelOfbizRoute extends RouteBuilder {

    public void configure()throws Exception {

        //Treat any fault as a exception
        getContext().setHandleFault(true);
        //If any exception occur after retrying by default,If re-delivery exhausted this errorHandler code store the file after 3 re-delivery.    
        errorHandler(deadLetterChannel(RouteProperty.deadLetterPath)
            .useOriginalMessage()
            .asyncDelayedRedelivery()
            .maximumRedeliveries(3)
            .redeliveryDelay(1000)
            .retryAttemptedLogLevel(LoggingLevel.WARN));

        //Route 1 fetch data from the couch DB and put into local directory as a CSV file.       
        from(RouteProperty.route1FromCouchDb)
            .startupOrder(1)
            .process(new JsonToCsvProcessor())
            .to(RouteProperty.route1ToFile);

        //Route 2 get CSV file from the local directory set to multipart_form data also drop same file to the backup folder.
        from(RouteProperty.route2FromFile)
            .startupOrder(2)
            .to(RouteProperty.route2ToFile)
            .process(new ProcessForTransport())
            //This three line of code work individual for getting token.         	 
            .setHeader(Exchange.HTTP_QUERY,constant(RouteProperty.setUserNamePass))
            .to(RouteProperty.route2ToToken)
            .process(new ProcessorForGetToken());

        //Route 3 will start right after route two processorForTransport will end,Send multipart_form data to the server include CSV file.
        from("direct:sendFileExchange")
             .startupOrder(3)
             .process(new ProcessorSetFileRequest())
             .setHeader(Exchange.HTTP_METHOD, constant("POST"))
             .setHeader(Exchange.HTTP_QUERY,constant(RouteProperty.setUserNamePass))
             .to(RouteProperty.route3ToOfbiz)
             .to("stream:out");
    }
}
