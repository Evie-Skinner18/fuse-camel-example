package com.redhat.fuse.boosters.cb;


public class Resident {

    private String residentInfo;
    private String northgatePropertyNumber;
    private String partyReference;

    public Resident() {
    }

    public Resident(String residentInfo) {
        this.residentInfo = residentInfo;
        this.northgatePropertyNumber = "45966";
        this.partyReference = "18hh3h4rn";
    }

    public String getResidentInfo() {
        return residentInfo;
    }

    public void setResidentInfo(String residentInfo) {
        this.residentInfo = residentInfo;
    }

    // getters and setters or other properties
}