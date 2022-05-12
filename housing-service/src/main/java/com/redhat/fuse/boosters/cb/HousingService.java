package com.redhat.fuse.boosters.cb;

public interface HousingService {
    HousingResponse getHousingResponse(String residentInfo, 
        String northgatePropertyNumber, 
        String partyReference);

}