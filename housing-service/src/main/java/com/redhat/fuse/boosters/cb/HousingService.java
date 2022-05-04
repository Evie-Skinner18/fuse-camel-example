package com.redhat.fuse.boosters.cb;

public interface HousingService {
    HousingResponse getHousingResponse(String repairInfo);
    Repair getRepairFromXml(String repairInfo);

}