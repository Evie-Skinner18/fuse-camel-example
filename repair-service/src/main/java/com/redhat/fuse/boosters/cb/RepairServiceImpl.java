package com.redhat.fuse.boosters.cb;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


@Service("repairService")
public class RepairServiceImpl implements RepairService {

    // get xml
    // serialise it into a Repair bean
    @Override
    public Repair getRepair(@Header("repairInfo") String repairInfo) {
        return new Repair(repairInfo);
    }

}