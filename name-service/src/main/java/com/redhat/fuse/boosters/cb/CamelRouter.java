package com.redhat.fuse.boosters.cb;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * A simple Camel REST DSL route that implement the name service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
        rest("/name").description("Name REST service")
            .consumes("application/json")
            .produces("application/json")

            // tweak so this returns a List<Name>
            .get().description("Generate a Name").outType(Name.class)
                .responseMessage().code(200).endResponseMessage()
                .to("bean:nameService?method=getName(${header.name})");
        // @formatter:on
    }

}