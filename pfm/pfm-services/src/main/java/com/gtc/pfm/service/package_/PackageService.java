/**
 * 
 */
package com.gtc.pfm.service.package_;

import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.Location;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.domain.User;
import com.gtc.pfm.domain.Venue;
import com.gtc.pfm.service.PfmService;

import java.util.List;

/**
 * Interface class that identifies available operations of package layer as service
 * 
 * @author stanriku
 *
 */
public interface PackageService extends PfmService{

    /**
     * creates {@link Package}
     * 
     * @param _package
     * @return String id of {@link Package}
     * @throws ProviderException
     * @throws ObjectExistsException
     */
    public String createPackage(Package _package) throws ProviderException, ObjectExistsException;
    
    /**
     * update given {@link Package}
     * 
     * @param _package
     * @throws ProviderException
     * @throws ObjectExistsException
     */
    public void updatePackage(Package _package) throws ProviderException,ObjectNotFoundException;
    
    /**
     * deletes {@link Package} by id
     * 
     * @param id
     * @throws ProviderException
     * @throws ObjectNotFoundException
     */
    public void deletePackage(String id) throws ProviderException,ObjectNotFoundException;
    
    /**
     * validates weather given {@link User} is eligible (recipient of the package) and then  updates "open" attribute of {@link Package} as true. 
     * 
     * @param packageId
     * @param userId
     * @throws ProviderException
     * @throws ObjectNotFoundException
     */
    public void openPackage(String packageId, String userId) throws ProviderException,ObjectNotFoundException;
    
    /**
     * finds {@link Package} by id
     * 
     * @param id
     * @return {@link Package}
     * @throws ProviderException
     * @throws ObjectNotFoundException
     */
    public Package findPackageById(String id) throws ProviderException,ObjectNotFoundException;
    
    /**
     * finds all available, ready to get opened,  packages dropped for a user in a specific location
     * 
     * @param userId
     * @param {@link Location}
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findAvailablePackagesByUserIdAndLocation(String userId, Location location, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds all (open and wrapped) packages dropped by a user in a specific location
     * 
     * @param userId
     * @param {@link Location}
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findAllPackagesByUserIdAndLocation(String userId, Location location, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds all opened packages dropped by a user in a specific location
     * 
     * @param userId
     * @param {@link Location}
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findOpenPackagesByUserIdAndLocation(String userId, Location location, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds all available, ready to get opened,  packages dropped for a user in a specific location
     * 
     * @param recipientUserId
     * @param {@link Location}
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findAvailablePackagesByRecepientIdAndLocation(String recipientUserId, Location location, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds all (open and wrapped) packages dropped for a user in a specific location
     * 
     * @param recipientUserId
     * @param {@link Location}
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findAllPackagesByRecepientIdAndLocation(String recipientUserId, Location location, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds all opened packages dropped by a user in a specific location
     * 
     * @param recipientUserId
     * @param {@link Location}
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findOpenPackagesByRecepientIdAndLocation(String recipientUserId, Location location, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds available,read to get opened, {@link Package}s in specified {@link Venue}
     * 
     * @param venueId
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findAvailablePackagesByVenueId(String venueId, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds opened {@link Package}s in specified {@link Venue}
     * 
     * @param venueId
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findOpenPackagesByVenueId(String venueId, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds all (opened and wrapped) {@link Package}s in specified {@link Venue}
     * 
     * @param venueId
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findAllPackagesByVenueId(String venueId,RowFilter rowFilter) throws ProviderException;
    
    /**
     * fetches all {@link Package}s in between given row range
     * 
     * @param {@link RowFilter}
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findAllPackages(RowFilter rowFilter) throws ProviderException;
    
    /**
     * fetches all {@link Package}s in given status(open or not) and between given row range
     * 
     * @param open
     * @param rowFilter
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findAllPackagesByStatus(boolean open, RowFilter rowFilter) throws ProviderException;
    
    /**
     * finds packages by user, location and status
     * 
     * @param userId
     * @param location
     * @param open
     * @param rowFilter
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findPackagesByUserIdAndLocationAndPackageStatus(String userId, Location location, boolean open, RowFilter rowFilter)
            throws ProviderException;
    
    /**
     * 
     * finds packages by recepient, location and status
     * 
     * @param recipientUserId
     * @param location
     * @param open
     * @param rowFilter
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findPackagesByRecepientIdAndLocationAndPackageStatus(String recipientUserId, Location location, boolean open, RowFilter rowFilter)
            throws ProviderException;
    
    /**
     * finds packages by venue id and package status
     * 
     * @param venueId
     * @param open
     * @param rowFilter
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findPackagesByVenueIdIdAndPackageStatus(String venueId, boolean open, RowFilter rowFilter)
            throws ProviderException;
    
    /**
     * finds all packages by package status in a given {@link Location}
     * 
     * @param location
     * @param open
     * @param rowFilter
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findPackagesByLocationAndPackageStatus(Location location, boolean open, RowFilter rowFilter)
            throws ProviderException;
    
    /**
     * finds all packages in a given {@link Location}
     * 
     * @param location
     * @param rowFilter
     * @return list of {@link Package}
     * @throws ProviderException
     */
    public List<Package> findPackagesByLocation(Location location, RowFilter rowFilter)
            throws ProviderException;
     
    
}
