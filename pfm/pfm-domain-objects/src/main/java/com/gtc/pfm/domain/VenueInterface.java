/**
 * 
 */
package com.gtc.pfm.domain;

/**
 * @author stanriku
 *
 */
public interface VenueInterface extends PfmDomainObjectInterface {

    public int getType();
    
    public double getLongitude();
    
    public double getLatitude();
    
    public String getAddress();
    
    public String getName();
    
}
