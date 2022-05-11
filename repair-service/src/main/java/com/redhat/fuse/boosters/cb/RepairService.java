package com.redhat.fuse.boosters.cb;


public interface RepairService {
    Repair getRepair(String repairInfo);
    Resident getResident(String residentInfo);
}