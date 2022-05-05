package com.redhat.fuse.boosters.cb;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;
import org.apache.camel.jsonpath.JsonPath;

@Service("housingService")
public class HousingServiceImpl implements HousingService {

    @Override   
    public HousingResponse getHousingResponse( @JsonPath("$.residentInfo") String residentInfo, 
        @JsonPath("$.northgatePropertyNumber") String northgatePropertyNumber, 
        @JsonPath("$.partyReference") String partyReference) {
        String housingResponse = "We have found this resident matching the details submitted: " 
            + "Resident info: " + residentInfo + " "
            + "Northgate property number: " + northgatePropertyNumber + " "
            + "Party reference: " + partyReference;

        return new HousingResponse(housingResponse);
    }

    public Repair getRepairFromXml(@Header("repairInfo") String repairInfo) {
        Repair repair = new Repair(repairInfo);
      
        return repair;
    }

}