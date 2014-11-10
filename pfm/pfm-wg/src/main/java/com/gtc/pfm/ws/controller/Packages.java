/**
 * 
 */
package com.gtc.pfm.ws.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.gtc.pfm.domain.Package;

/**
 * @author stanriku
 *
 */
@JsonRootName("packages")
public class Packages {

    @JsonProperty("packageList")
    private List<Package> packages;
    
    /**
     * 
     */
    public Packages() {
        
    }

    /**
     * @param packages
     */
    public Packages(List<Package> packages) {
        super();
        this.packages = packages;
    }



    /**
     * @return the packages
     */
    public List<Package> getPackages() {
        return packages;
    }

    /**
     * @param packages the packages to set
     */
    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }
    
    

}
