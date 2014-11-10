/**
 * 
 */
package com.gtc.pfm.ws.controller;

import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.Location;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.service.package_.PackageService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

/**
 * This class exposes business methods of {@link PackageService} as restful web service
 * 
 * @author stanriku
 *
 */
@RequestMapping("/package")
@Controller
public class PackageRestWSController extends PfmRestWSController{

    private static Log logger = LogFactory.getLog(PackageRestWSController.class);
    
    /**
     * default construcotor
     */
    public PackageRestWSController() {
        
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/createPackage",
            produces=MediaType.APPLICATION_JSON_VALUE,
            consumes=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createPackage(@RequestBody Package _package) {
        
        String packageId = null;
        try {
            packageId = packageService.createPackage(_package);
        } catch (ProviderException e) {
           logger.error("",e);
        } catch (ObjectExistsException e) {
            logger.error("",e);
        }
        
        return packageId;
    }

    @RequestMapping(method=RequestMethod.GET, value="/findPackageById/{id}", 
            produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Package findPackageById(@PathVariable String id) {
        
        Package package_ = null;
        try {
            package_ = packageService.findPackageById(id);
        } catch (ProviderException e) {
            logger.error("an error occured while finding package by id:"+id,e);
        } catch (ObjectNotFoundException e) {
            logger.error("package not found by id:"+id,e);
        }
        
        return package_;
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/deletePackage/{id}")
    public void deletePackage(@PathVariable String id) {
        try {
            packageService.deletePackage(id);
        } catch (ProviderException e) {
            logger.error("an error occured while deleting package by id:"+id,e);
        } catch (ObjectNotFoundException e) {
            logger.error("package not found:",e);
        }
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/openPackage", 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public void openPackage(@RequestParam("pi") String packageId,@RequestParam("ui") String userId) {
        try {
            packageService.openPackage(packageId, userId);
        } catch (ProviderException e) {
            logger.error("an error occured while opening package by id:"+packageId,e);
        } catch (ObjectNotFoundException e) {
            logger.error("package not found:",e);
        }
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findAvailablePackagesByUserIdAndLocation", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findAvailablePackagesByUserIdAndLocation(@RequestParam("ui") String userId, 
            @JSONRequestParameter(value="location") Location location, @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findAvailablePackagesByUserIdAndLocation(userId, location, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding available packages by user id["+userId+
                    "], location["+location+"] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findAllPackagesByUserIdAndLocation", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findAllPackagesByUserIdAndLocation(@RequestParam("ui") String userId, 
            @JSONRequestParameter(value="location") Location location, @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findAllPackagesByUserIdAndLocation(userId, location, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding all packages by user id["+userId+
                    "], location["+location+"] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
        
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findOpenPackagesByUserIdAndLocation", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findOpenPackagesByUserIdAndLocation(@RequestParam("ui") String userId, 
            @JSONRequestParameter(value="location") Location location, @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findOpenPackagesByUserIdAndLocation(userId, location, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding open packages by user id["+userId+
                    "], location["+location+"] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }

    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findAvailablePackagesByRecepientIdAndLocation", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findAvailablePackagesByRecepientIdAndLocation(@RequestParam("ri") String recipientUserId, 
            @JSONRequestParameter(value="location") Location location, @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findAvailablePackagesByRecepientIdAndLocation(recipientUserId, location, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding available packages by recipient id["+recipientUserId+
                    "], location["+location+"] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
    

    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findAllPackagesByRecepientIdAndLocation", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findAllPackagesByRecepientIdAndLocation(@RequestParam("ri") String recipientUserId, 
            @JSONRequestParameter(value="location") Location location,  @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findAllPackagesByRecepientIdAndLocation(recipientUserId, location, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding all packages by recipient id["+recipientUserId+
                    "], location["+location+"] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findOpenPackagesByRecepientIdAndLocation", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findOpenPackagesByRecepientIdAndLocation(@RequestParam("ri")  String recipientUserId, 
            @JSONRequestParameter(value="location") Location location, @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findOpenPackagesByRecepientIdAndLocation(recipientUserId, location, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding open packages by recipient id["+recipientUserId+
                    "], location["+location+"] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findAvailablePackagesByVenueId", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findAvailablePackagesByVenueId(@RequestParam("vi") String venueId, 
            @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findAvailablePackagesByVenueId(venueId, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding available packages by venue id["+venueId+
                    "] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
    

    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findOpenPackagesByVenueId", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findOpenPackagesByVenueId(@RequestParam("vi")  String venueId, 
            @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findOpenPackagesByVenueId(venueId, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding open packages by venue id["+venueId+
                    "] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
    

    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findAllPackagesByVenueId", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findAllPackagesByVenueId(@RequestParam("vi") String venueId, @JSONRequestParameter(value="rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findAllPackagesByVenueId(venueId, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding all packages by venue id["+venueId+
                    "] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findAllPackages", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody Packages findAllPackages(@RequestBody RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findAllPackages(rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding all packages by row filter["+rowFilter+"]",e);
        }
        
        Packages packageList = new Packages(packages);
       
        return packageList;
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findAllPackagesByStatus", 
            produces=MediaType.APPLICATION_JSON_VALUE, 
            consumes={MediaType.APPLICATION_JSON_VALUE,
                          MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                          MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody Packages findAllPackagesByStatus(@RequestParam(value="status") boolean status, @JSONRequestParameter(value="rowFilter") RowFilter rowFilter)  {
        
        List<Package> packages = null;
        try {
            packages = packageService.findAllPackagesByStatus(status,rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding all packages by status["+status+"] and row filter["+rowFilter+"]",e);
        }
        
        Packages packageList = new Packages(packages);
       
        return packageList;
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findPackagesByUserIdAndLocationAndPackageStatus", 
            produces=MediaType.APPLICATION_JSON_VALUE, 
            consumes={MediaType.APPLICATION_JSON_VALUE,
                          MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                          MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody Packages findPackagesByUserIdAndLocationAndPackageStatus(@RequestParam("ui") String userId, 
            @JSONRequestParameter("location") Location location, @RequestParam(value="status") Boolean open, @JSONRequestParameter("rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        
        try {
            packages = packageService.findPackagesByUserIdAndLocationAndPackageStatus(userId, location, open, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding  packages by user id["+userId+
                    "], location["+location+"], status(open)["+open+"] and row filter["+rowFilter+"]",e);
        }
        
        Packages packageList = new Packages(packages);
        
        return packageList;
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findPackagesByRecepientIdAndLocationAndPackageStatus", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody Packages findPackagesByRecepientIdAndLocationAndPackageStatus(@RequestParam("ri") String recipientUserId, 
            @JSONRequestParameter("location") Location location,  @RequestParam("status") boolean open, @JSONRequestParameter("rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findPackagesByRecepientIdAndLocationAndPackageStatus(recipientUserId, location, open, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding  packages by recipient id["+recipientUserId+
                    "], location["+location+"], status(open) ["+open+"] and row filter["+rowFilter+"]",e);
        }
        
        Packages packageList = new Packages(packages);
        
        return packageList;
        
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findPackagesByVenueIdIdAndPackageStatus", 
            produces=MediaType.APPLICATION_JSON_VALUE, 
            consumes={MediaType.APPLICATION_JSON_VALUE,
                          MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                          MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody Packages findPackagesByVenueIdIdAndPackageStatus(@RequestParam("vi") String venueId, @RequestParam("status") boolean open, 
            @JSONRequestParameter("rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findPackagesByVenueIdIdAndPackageStatus(venueId, open, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding packages by venue id["+venueId+
                    "], status(open) ["+open+"] and row filter["+rowFilter+"]",e);
        }
        
        Packages packageList = new Packages(packages);
        
        return packageList;
        
    }
    
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findPackagesByLocationAndPackageStatus", 
            produces=MediaType.APPLICATION_JSON_VALUE, 
            consumes={MediaType.APPLICATION_JSON_VALUE,
                          MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                          MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findPackagesByLocationAndPackageStatus(@JSONRequestParameter("location") Location location, @RequestParam("status") boolean open, 
            @JSONRequestParameter("rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findPackagesByLocationAndPackageStatus(location, open, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding  packages by location["+location+
                    "], status(open) ["+open+"] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
    

    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/findPackagesByLocation", 
    produces=MediaType.APPLICATION_JSON_VALUE, 
    consumes={MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                  MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody List<Package> findPackagesByLocation(@JSONRequestParameter("location") Location location, @JSONRequestParameter("rowFilter") RowFilter rowFilter) {
        
        List<Package> packages = null;
        try {
            packages = packageService.findPackagesByLocation(location, rowFilter);
        } catch (ProviderException e) {
            logger.error("an error occured while finding  packages by location["+location+
                    "] and row filter["+rowFilter+"]",e);
        }
        
        return packages;
    }
}
