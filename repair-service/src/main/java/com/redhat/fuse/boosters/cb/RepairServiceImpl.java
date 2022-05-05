package com.redhat.fuse.boosters.cb;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;


@Service("repairService")
public class RepairServiceImpl implements RepairService {

    @Override
    public Repair getRepair(@Header("repairInfo") String repairInfo) {
        return new Repair(repairInfo);
    }

    @Override
    public Resident getResident(@Header("residentInfo") String residentInfo) {
        return new Resident(residentInfo);
    }

}