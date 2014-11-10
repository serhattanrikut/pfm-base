/**
 * 
 */
package com.gtc.pfm.service.venue;

import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.domain.Venue;
import com.gtc.pfm.service.PfmService;

import java.util.List;

/**
 * This interfaces defines all business logic methods of {@link Venue} 
 * @author stanriku
 *
 */
public interface VenueService extends PfmService{

    /**
     * creates {@link Venue}
     * 
     * @param venue
     * @return id of {@link Venue}
     * @throws ProviderException
     */
    public String createVenue(Venue venue) throws ProviderException;
    
    /**
     * deletes {@link Venue} by given id
     * 
     * @param venueId
     * @throws ObjectNotFoundException
     * @throws ProviderException
     */
    public void deleteVenueById(String venueId) throws ObjectNotFoundException, ProviderException;
    
    /**
     * finds {@link Venue} by given id
     * 
     * @param venueId
     * @return {@link Venue}
     * @throws ObjectNotFoundException
     * @throws ProviderException
     */
    public Venue findVenueById(String venueId) throws ObjectNotFoundException, ProviderException;
    
    
    /**
     * finds {@link Venue} by given venue name
     * 
     * @param name
     * @return {@link Venue}
     * @throws ObjectNotFoundException
     * @throws ProviderException
     */
    public Venue findVenueByName(String name) throws ObjectNotFoundException, ProviderException;
    
    /**
     * finds all {@link Venue}s
     * 
     * @param rowFilter
     * @return list of {@link Venue}s
     * @throws ProviderException
     */
    public List<Venue> findAllVenues(RowFilter rowFilter) throws ProviderException;


}
