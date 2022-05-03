package com.redhat.fuse.boosters.cb;


/**
 * Service interface for name service.
 * 
 */
public interface RepairService {

    /**
     * Generate Name
     *
     * @return a string name
     */
    Repair getRepair(String repairInfo);

}