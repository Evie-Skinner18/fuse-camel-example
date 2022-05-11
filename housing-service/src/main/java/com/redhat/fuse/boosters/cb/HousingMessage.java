package com.redhat.fuse.boosters.cb;

import com.google.gson.Gson;

public class HousingMessage {
    public ResidentInfo residentInfo;
    public String northgatePropertyNumber;
    public String partyReference;

    public HousingMessage(String residentInfo, 
        String northgatePropertyNumber,
        String partyReference) {
        Gson jsonConverter = new Gson();
        ResidentInfo residentInfoBean = jsonConverter.fromJson(residentInfo, ResidentInfo.class);

        this.residentInfo = residentInfoBean;
        this.northgatePropertyNumber = northgatePropertyNumber;
        this.partyReference = partyReference;        
    }

}