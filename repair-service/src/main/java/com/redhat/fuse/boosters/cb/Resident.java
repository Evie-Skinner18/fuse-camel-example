package com.redhat.fuse.boosters.cb;


public class Resident {

    private String residentInfo;
    private String northgatePropertyNumber;
    private String partyReference;

    public Resident() {
    }

    public String getPartyReference() {
        return partyReference;
    }

    public void setPartyReference(String partyReference) {
        this.partyReference = partyReference;
    }   

    public String getNorthgatePropertyNumber() {
        return northgatePropertyNumber;
    }

    public void setNorthgatePropertyNumber(String northgatePropertyNumber) {
        this.northgatePropertyNumber = northgatePropertyNumber;
    }

    public Resident(String residentInfo) {
        this.setResidentInfo(residentInfo);
        this.setNorthgatePropertyNumber("45966");
        this.setPartyReference("18hh3h4rn");
    }

    public String getResidentInfo() {
        return residentInfo;
    }

    public void setResidentInfo(String residentInfo) {
        this.residentInfo = residentInfo;
    }

    // getters and setters or other properties
}