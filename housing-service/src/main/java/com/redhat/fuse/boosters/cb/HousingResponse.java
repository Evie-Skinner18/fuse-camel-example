package com.redhat.fuse.boosters.cb;


public class HousingResponse {

    public HousingMessage housingMessage;

    /*
    We have found this resident matching the details submitted: Resident info: {"firstName":"dave","surname":"weeee","uprn":"10001169332"} Northgate property number: 45966 Party reference: 18hh3h4rn
    */
    public HousingResponse(HousingMessage housingMessage) {
        this.housingMessage = housingMessage;
    }
}