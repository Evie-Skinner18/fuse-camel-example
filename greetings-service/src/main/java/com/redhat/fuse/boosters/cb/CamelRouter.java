package com.redhat.fuse.boosters.cb;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
        
        rest("/greetings/{name}/{address}").description("Greetings REST service")
            .consumes("application/json")
            .produces("application/json")

            .get().outType(Greetings.class)
                .responseMessage().code(200).endResponseMessage()
                .to("direct:greetingsImpl");

        from("direct:greetingsImpl").description("Greetings REST service implementation route")
            .streamCaching()
            .hystrix().id("CamelHystrix")
                // see application.properties how hystrix is configured
                .log(" Try to call name Service")
                .log("${body}")
                .to("http://"+nameServiceHost+":"+nameServicePort+"/camel/name/{name}?bridgeEndpoint=true")
                .log(" Successfully called name Service")
            .onFallback()
                // we use a fallback without network that provides a response message immediately
                .log(" We are falling back!!!!")
                .transform().simple("{ name: \"default fallback\", address: \"fallback address\" }")
            .end()
            .to("bean:greetingsService?method=getGreetings");     
        // @formatter:on
    }

}