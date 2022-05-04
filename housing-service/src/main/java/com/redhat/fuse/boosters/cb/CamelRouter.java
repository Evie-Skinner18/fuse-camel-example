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

    @Value("${repairService.host}")
    String repairServiceHost;

    @Value("${repairService.port}")
    String repairServicePort;


    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
        rest("/housing/{repairInfo}").description("Housing REST service")
            .consumes("application/json")
            .produces("application/json")

            .get().outType(HousingResponse.class)
            // wrap a HousingResponse bean in a Camel-specific object called a Message
            // Message has headers and other magic that Camel can do
                .responseMessage().code(200).endResponseMessage()
                .to("direct:housingImpl");
            
            from("direct:housingImpl")
                .streamCaching()
                .removeHeaders("Camel*") // if not remove camel headers it rewrites the url
                .setHeader(HTTP_QUERY, simple("repairInfo=${header.repairInfo}"))
                .log("repairInfo=${header.repairInfo}")
                .to("http://localhost:8081/camel/repairs?bridgeEndpoint=true")
                .to("bean:housingService?method=getHousingResponse");
    }

}