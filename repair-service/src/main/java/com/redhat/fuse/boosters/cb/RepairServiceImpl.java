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
        Repair repair = getRepairFromXml();
        return repair;
    }

    private Repair getRepairFromXml() {
        // 1.Create a DocumentBuilder instance
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        // 2. Create a Document from the above DocumentBuilder.
        Document xmlDocument = documentBuilder.newDocument();

        // 3. Create the elements you want using the Element class and its appendChild method.   

        // root element
        Element repair = document.createElement("repair");
        xmlDocument.appendChild(repair); 
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);    
         
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();    
        Repair repair = (Repair) jaxbUnmarshaller.unmarshal(xmlDocument);  

        // // child element
        // Element user = document.createElement("user");
        // users.appendChild(user);  

        // // Attribute of child element
        // user.setAttribute("uid", "1");   
      
        return repair;
    }

}