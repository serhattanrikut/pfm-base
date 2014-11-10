package com.gtc.pfm.service.venue;

import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.domain.Venue;
import com.gtc.pfm.persistence.repository.venue.VenueRepository;
import com.gtc.pfm.service.PfmServiceBase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author stanriku
 *
 */
@Service("VenueService")
public class VenueServiceImpl extends PfmServiceBase implements VenueService{

    private static Log logger = LogFactory.getLog(VenueServiceImpl.class);
    
    @Autowired
    public VenueRepository venueRepository;
    
    /**
     * default constructor
     */
    public VenueServiceImpl() {
       super();
    }

    @Override
    public String createVenue(Venue venue) throws ProviderException {
        
        LoggingUtil.debug(logger, "creating venue..");
        
        String venueId = this.venueRepository.createVenue(venue);
        
        LoggingUtil.debug(logger, "created venue with id:"+venueId);
        
        return venueId;
    }

    @Override
    public void deleteVenueById(String venueId) throws ObjectNotFoundException, ProviderException {
       
        LoggingUtil.debug(logger, "deleting venue by id:"+venueId);
        
        this.venueRepository.deleteVenueById(venueId);
        
        LoggingUtil.debug(logger, "deleted venue");
        
    }

    @Override
    public Venue findVenueById(String venueId) throws ObjectNotFoundException, ProviderException {
        
        LoggingUtil.debug(logger, "finding venue by id:"+venueId);
        
        Venue venue = this.venueRepository.findVenueById(venueId);
        
        LoggingUtil.debug(logger, "found venue"+venue);
        
        return venue;
    }

    @Override
    public Venue findVenueByName(String name) throws ObjectNotFoundException, ProviderException {
        
        LoggingUtil.debug(logger, "finding venue by name:"+name);
        
        Venue venue = this.venueRepository.findVenueByName(name);
        
        LoggingUtil.debug(logger, "found venue"+venue);
        
        return venue;
    }

    @Override
    public List<Venue> findAllVenues(RowFilter rowFilter) throws ProviderException {
       
        LoggingUtil.debug(logger, "finding all venues"); 
        
        List<Venue> venues = this.venueRepository.findAllVenues(rowFilter);
        
        return venues;
    }

    /**
     * @return the venueRepository
     */
    public VenueRepository getVenueRepository() {
        return venueRepository;
    }

    /**
     * @param venueRepository the venueRepository to set
     */
    public void setVenueRepository(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

}
