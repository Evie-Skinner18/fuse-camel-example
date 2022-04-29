package com.redhat.fuse.boosters.cb;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static org.apache.camel.Exchange.HTTP_QUERY;

/**
 * A simple Camel REST DSL route that implement the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Value("${nameService.host}")
    String nameServiceHost;

    @Value("${nameService.port}")
    String nameServicePort;


    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
        rest("/greetings/{name}").description("Greetings REST service")
            .consumes("application/json")
            .produces("application/json")

            .get().outType(Greetings.class)
            // wrap a Greetings bean in a Camel-specific object called a Message
            // Message has headers and other magic that Camel can do
                .responseMessage().code(200).endResponseMessage()
                .to("direct:greetingsImpl");

        // from("direct:greetingsImpl")            
        //     .description("Greetings REST service implementation route")
        //     .streamCaching()
        //     // see application.properties how hystrix is configured
        //     .log(" Try to call name Service")
        //     .log("Received Body: ${body}")
        //     .log("name header object: ${header.name}")
        //     .removeHeaders("CamelHttp*")
        //     .setHeader(HTTP_QUERY, simple("name=${header.name}"))
        //     .to("http://"+nameServiceHost+":"+nameServicePort+"/camel/name/?bridgeEndpoint=true")
        //     .log(" Successfully called name Service")
        //     .to("bean:greetingsService?method=getGreetings");  
            
            
            from("direct:greetingsImpl")
                .streamCaching()
                .removeHeaders("Camel*") // if not remove camel headers it rewrites the url
                .setHeader(HTTP_QUERY, simple("name=${header.name}"))
                .log("name=${header.name}")
                .to("http://localhost:8081/camel/name?bridgeEndpoint=true")
                .to("bean:greetingsService?method=getGreetings");
    }

}