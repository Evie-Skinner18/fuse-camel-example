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
        HousingMessage housingMessage = new HousingMessage(residentInfo, 
            northgatePropertyNumber, 
            partyReference);
        
        String name = housingMessage.residentInfo.firstName.trim().toLowerCase();
        boolean residentIsCalledBarry = name.equals("barry");
        boolean isValidResident = !residentIsCalledBarry;
        housingMessage.residentInfo.isValidResident = isValidResident;    

        return new HousingResponse(housingMessage);
    }

    public Repair getRepairFromXml(@Header("repairInfo") String repairInfo) {
        Repair repair = new Repair(repairInfo);
      
        return repair;
    }

}