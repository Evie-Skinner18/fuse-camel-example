package com.redhat.fuse.boosters.cb;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;
import org.apache.camel.jsonpath.JsonPath;

@Service("housingService")
public class HousingServiceImpl implements HousingService {

    private static String THE_HOUSING_RESPONSE = "We have raised a repair for:";

    @Override   
    public HousingResponse getHousingResponse( @JsonPath("$.repairInfo") String repairInfo) {
        String housingResponse = THE_HOUSING_RESPONSE + " " + repairInfo;

        return new HousingResponse(housingResponse);
    }

    public Repair getRepairFromXml(@Header("repairInfo") String repairInfo) {
        // 1.Create a DocumentBuilder instance
        // DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        // // 2. Create a Document from the above DocumentBuilder.
        // Document xmlDocument = documentBuilder.newDocument();

        // // 3. Create the elements you want using the Element class and its appendChild method.   

        // // root element
        // Element repair = document.createElement("repair");
        // xmlDocument.appendChild(repair); 
        
        // JAXBContext jaxbContext = JAXBContext.newInstance(Repair.class);    
         
        // Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();    
        // Repair repair = (Repair) jaxbUnmarshaller.unmarshal(xmlDocument);  

        Repair repair = new Repair(repairInfo);
      
        return repair;
    }

}