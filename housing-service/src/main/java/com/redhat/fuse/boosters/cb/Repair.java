package com.redhat.fuse.boosters.cb;

import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement; 

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