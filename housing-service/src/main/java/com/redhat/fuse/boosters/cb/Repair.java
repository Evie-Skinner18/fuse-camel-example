package com.redhat.fuse.boosters.cb;

import javax.xml.bind.annotation.XmlRootElement; 
//remove
@XmlRootElement
public class Repair {

    private String repairInfo;

    public Repair() {
    }

    public Repair(String repairInfo) {
        this.repairInfo = repairInfo;
    }

    public String getRepairInfo() {
        return repairInfo;
    }

    public void setRepairInfo(String repairInfo) {
        this.repairInfo = repairInfo;
    }

}