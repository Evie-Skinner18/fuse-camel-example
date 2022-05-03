package com.redhat.fuse.boosters.cb;

import org.springframework.stereotype.Service;
import org.apache.camel.jsonpath.JsonPath;

@Service("housingService")
public class HousingServiceImpl implements HousingService {

    private static String THE_HOUSING_RESPONSE = "We have raised a repair for:";

    @Override   
    public HousingResponse getHousingResponse( @JsonPath("$.name") String name) {
        String housingResponse = THE_HOUSING_RESPONSE + " " + name;

        return new HousingResponse(housingResponse);
    }

}