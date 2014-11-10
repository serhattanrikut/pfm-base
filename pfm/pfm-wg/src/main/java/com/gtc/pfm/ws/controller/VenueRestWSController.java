/**
 * 
 */
package com.gtc.pfm.ws.controller;

import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.domain.Venue;
import com.gtc.pfm.persistence.repository.venue.VenueRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * This class exposes business methods of {@link VenueRepository} as restful web service
 * 
 * @author stanriku
 *
 */
@RequestMapping("/venue")
@Controller
public class VenueRestWSController extends PfmRestWSController {

    private static Log logger = LogFactory.getLog(VenueRestWSController.class);
    
    /**
     * 
     */
    public VenueRestWSController() {
        
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/findVenueById/{id}", 
            headers="Accept=application/json")
    public @ResponseBody Venue findVenueById(@PathVariable String id) {
        
        Venue venue = null;
        
        try {
            venue = this.venueService.findVenueById(id);
        } catch (Exception e) {
            logger.error("an error occured while finding venue by id:"+id,e);
        }
        
        return venue;
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/createVenue", 
            headers="Accept=application/json")
    public @ResponseBody String createVenue(@RequestBody Venue venue) {
        
        String id = null;
        
        try {
            venueService.createVenue(venue);
        } catch (ProviderException e) {
            logger.error("an error occured while creating venue:"+venue,e);
        }
        
        return id;
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/deleteVenueById/{venueId}", 
            headers="Accept=application/json")
    public void deleteVenueById(@PathVariable String venueId) {
        
        try {
            venueService.deleteVenueById(venueId);
        } catch (ObjectNotFoundException e) {
           logger.error("venue not found by id:"+venueId,e);
        } catch (ProviderException e) {
            logger.error("an error occured while deleting venue by id:"+venueId,e);
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/findVenueByName/{name}", 
            headers="Accept=application/json")
    public @ResponseBody Venue findVenueByName(@PathVariable String name) {
        
        Venue venue = null;
        try {
            venueService.findVenueByName(name);
        } catch (ObjectNotFoundException e) {
           logger.error("venue not found by name:"+name);
        } catch (ProviderException e) {
            logger.error("an error occured while finding venue by name:"+name,e);
        }
        
        return venue;
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/findAllVenues", 
            headers="Accept=application/json")
    public @ResponseBody List<Venue> findAllVenues(@RequestBody RowFilter rowFilter) {
        
        List<Venue> venues =null;
        
        try {
            venues = venueService.findAllVenues(rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding all venues by rowFilter:"+rowFilter,e);
        }
        
        return venues;
    }
}
