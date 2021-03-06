package com.redhat.fuse.boosters.cb;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
        rest("/repairs").description("Repairs REST service")
            .consumes("application/json")
            .produces("application/json")
            
            .get("/residents").description("Search for a resident").outType(Resident.class)
                .responseMessage().code(200).endResponseMessage()
                .to("bean:repairService?method=getResident(${header.residentInfo})");    
        // @formatter:on
    }

}