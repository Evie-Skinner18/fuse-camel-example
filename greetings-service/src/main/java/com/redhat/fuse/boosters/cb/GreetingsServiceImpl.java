package com.redhat.fuse.boosters.cb;

import org.springframework.stereotype.Service;
import org.apache.camel.jsonpath.JsonPath;

@Service("greetingsService")
public class GreetingsServiceImpl implements GreetingsService {

    private static String THE_GREETINGS = "We have raised a repair for:";

    @Override
    public Greetings getGreetings( @JsonPath("$.name") String name, @JsonPath("$.address") String address ) {
        String greetingMessage = THE_GREETINGS + " " + name + " at" + address;
        return new Greetings(greetingMessage);
    }

}