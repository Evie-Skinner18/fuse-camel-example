package com.redhat.fuse.boosters.cb;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;


@Service("repairService")
public class RepairServiceImpl implements RepairService {

    // get xml
    // serialise it into json
    // add it as a string to the Repair bean?
    @Override
    public Repair getRepair(@Header("repairInfo") String repairInfo) {
        String formattedRepairInfo = repairInfo.replace('{', ' ').replace('}', ' ');
        // want to get the values of name and address
        // String residentName = formattedRepairInfo.sli
        return new Repair(formattedRepairInfo);
    }

    private xml getXml() {
        return 
    }

}