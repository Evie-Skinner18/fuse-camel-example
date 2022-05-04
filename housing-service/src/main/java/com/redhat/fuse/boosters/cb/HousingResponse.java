package com.redhat.fuse.boosters.cb;

public class HousingResponse {

    private String housingMessage;

    public HousingResponse() {
    }

    public HousingResponse(String housingMessage) {
        this.housingMessage = housingMessage;
    }

    public String getHousingMessage() {
        return housingMessage;
    }

    public void setHousingMessage(String housingMessage) {
        this.housingMessage = housingMessage;
    }
}