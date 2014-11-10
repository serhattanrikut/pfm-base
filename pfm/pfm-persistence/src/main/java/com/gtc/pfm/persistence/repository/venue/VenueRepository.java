/**
 * 
 */
package com.gtc.pfm.persistence.repository.venue;

import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.domain.Venue;
import com.gtc.pfm.persistence.repository.PfmRepositoryInft;

import java.util.List;

/**
 * This interface identifies all data access methods related with {@link Venue} exposed to other layers and services
 *
 * @author stanriku
 *
 */
public interface VenueRepository extends PfmRepositoryInft {

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
