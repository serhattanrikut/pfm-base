/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.venue;

import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.domain.Venue;
import com.gtc.pfm.persistence.repository.mongo.PfmMongoRepositoryBase;
import com.gtc.pfm.persistence.repository.venue.VenueRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mongodb implementation of {@link VenueRepository}
 * 
 * @author stanriku
 *
 */
@Repository
public class VenueRepositoryImpl extends PfmMongoRepositoryBase implements VenueRepository {

    private static Log logger = LogFactory.getLog(VenueRepositoryImpl.class);
    
    /**
     * 
     */
    public VenueRepositoryImpl() {
        
    }

    @Override
    public String createVenue(Venue venue) throws ProviderException {
        
        LoggingUtil.debug(logger, "creating venue:"+venue);
       
        getMongoTemplate().insert(venue);
        
        LoggingUtil.debug(logger, "created venue with id:"+venue.getId());
        
        return venue.getId();
    }

    @Override
    public void deleteVenueById(String venueId) throws ObjectNotFoundException, ProviderException {
       
        LoggingUtil.debug(logger, "deleting venue by id:"+venueId);
        
        getMongoTemplate().remove(venueId);
        
        LoggingUtil.debug(logger, "deleted venue by id:"+venueId);
    }

    @Override
    public Venue findVenueById(String venueId) throws ObjectNotFoundException, ProviderException {
        
        LoggingUtil.debug(logger, "finding venue by id:"+venueId);
        
        Venue venue = getMongoTemplate().findById(venueId, Venue.class);
        
        if(venue == null){
            LoggingUtil.debug(logger, "could not fine venue by id:"+venueId);
            throw new ObjectNotFoundException(venueId, null, "could not find Venue by id:"+venueId);
        }
        
        LoggingUtil.debug(logger, "found venue by id:"+venue);
        
        return venue;
    }

    @Override
    public Venue findVenueByName(String name) throws ObjectNotFoundException, ProviderException {
        
        LoggingUtil.debug(logger, "finding venue by name:"+name);
        
        Venue venue = getMongoTemplate().findOne(Query.query(Criteria.where(Venue.nameField).is(name)), Venue.class);        
        if(venue == null){
            LoggingUtil.debug(logger, "could not fine venue by name:"+name);
            throw new ObjectNotFoundException(name, null, "could not find Venue by name:"+name);
        }
        
        LoggingUtil.debug(logger, "found venue by name:"+venue);
        
        return venue;
        
    }

    @Override
    public List<Venue> findAllVenues(RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding all Venues vy row filter:"+rowFilter);
        
        List<Venue> venues = getMongoTemplate().findAll(Venue.class);
        
        LoggingUtil.debug(logger, "found venue size:"+(venues == null ? 0:venues.size()));
        
        return venues;
        
    }

}
