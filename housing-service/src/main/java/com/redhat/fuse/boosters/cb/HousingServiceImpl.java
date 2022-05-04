package com.redhat.fuse.boosters.cb;

import org.springframework.stereotype.Service;
import org.apache.camel.jsonpath.JsonPath;

@Service("housingService")
public class HousingServiceImpl implements HousingService {

    private static String THE_HOUSING_RESPONSE = "We have raised a repair for:";

    @Override   
    public HousingResponse getHousingResponse( @JsonPath("$.repairInfo") String repairInfo) {
        String housingResponse = THE_HOUSING_RESPONSE + " " + repairInfo;

        return new HousingResponse(housingResponse);
    }

}