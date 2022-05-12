package com.redhat.fuse.boosters.cb;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;


@Service("repairService")
public class RepairServiceImpl implements RepairService {

    @Override
    public Resident getResident(@Header("residentInfo") String residentInfo) {
        return new Resident(residentInfo);
    }

}