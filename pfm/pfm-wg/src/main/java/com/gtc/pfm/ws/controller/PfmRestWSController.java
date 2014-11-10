/**
 * 
 */
package com.gtc.pfm.ws.controller;

import com.gtc.pfm.service.package_.PackageService;
import com.gtc.pfm.service.user.UserService;
import com.gtc.pfm.service.venue.VenueService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * Abstract restful web service controller class.This class provides
 * injected service instances to extending classes
 * 
 * @author stanriku
 *
 */
public abstract class PfmRestWSController {
    
    @Autowired
    protected PackageService packageService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected VenueService venueService;
    
    
    /**
     * 
     */
    public PfmRestWSController() {
        
    }


    /**
     * @return the packageService
     */
    public PackageService getPackageService() {
        return packageService;
    }


    /**
     * @param packageService the packageService to set
     */
    public void setPackageService(PackageService packageService) {
        this.packageService = packageService;
    }


    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }


    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * @return the venueService
     */
    public VenueService getVenueService() {
        return venueService;
    }


    /**
     * @param venueService the venueService to set
     */
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }
    
    
}
