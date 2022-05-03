package com.redhat.fuse.boosters.cb;

/**
 * Service interface for name service.
 * 
 */
public interface HousingService {

    /**
     * Generate Greetings
     *
     * @return a string greetings
     */
    HousingResponse getHousingResponse(String name);

}