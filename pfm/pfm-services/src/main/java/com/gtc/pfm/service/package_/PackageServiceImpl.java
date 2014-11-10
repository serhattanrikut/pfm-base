/**
 * 
 */
package com.gtc.pfm.service.package_;

import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.domain.Location;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.persistence.repository.package_.PackageRepository;
import com.gtc.pfm.service.PfmServiceBase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author stanriku
 *
 */
@Service("PackageService")
public class PackageServiceImpl extends PfmServiceBase implements PackageService {
    
    private static Log logger = LogFactory.getLog(PackageServiceImpl.class);

    @Autowired
    PackageRepository packageRepository;
    
    /**
     * 
     */
    public PackageServiceImpl() {
        
    }

    @Override
    public String createPackage(Package _package) throws ProviderException, ObjectExistsException {
       
       LoggingUtil.debug(logger, "creating package.."+_package.getName());
       
       String packageId =  packageRepository.createPackage(_package);
       
       LoggingUtil.debug(logger, "package created with Id:"+packageId);
       
       return packageId;
    }

    @Override
    public void updatePackage(Package _package) throws ProviderException, ObjectNotFoundException {
        
        LoggingUtil.debug(logger, "updating package.."+_package.getName());
        
        packageRepository.updatePackage(_package);
        
        LoggingUtil.debug(logger, "package updated:");
    }

    @Override
    public void deletePackage(String id) throws ProviderException, ObjectNotFoundException {
        
        LoggingUtil.debug(logger, "deleting package with id:"+ id);
        
        packageRepository.deletePackage(id);
        
        LoggingUtil.debug(logger, "package deleted:");
        
    }

    @Override
    public void openPackage(String packageId, String userId) throws ProviderException,
            ObjectNotFoundException {
        
        LoggingUtil.debug(logger, "opening package["+packageId+"] by user id:"+ userId);
        
        packageRepository.openPackage(packageId, userId);
        
        LoggingUtil.debug(logger, "package opened:");
        
    }

    @Override
    public Package findPackageById(String id) throws ProviderException, ObjectNotFoundException {
       
        LoggingUtil.debug(logger, "finding package by id:"+id);
        
        Package _package = packageRepository.findPackageById(id);
        
        LoggingUtil.debug(logger, "package found"+_package.toString());
        
        return _package;
        
    }

    @Override
    public List<Package> findAvailablePackagesByUserIdAndLocation(String userId, Location location,
            RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding available packages  by user id:"+userId + ", location:"+location+ ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findAvailablePackagesByUserIdAndLocation(userId, location, rowFilter);
        
        LoggingUtil.debug(logger, "available packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findAllPackagesByUserIdAndLocation(String userId, Location location,
            RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding all packages  by user id:"+userId + ", location:"+location+ ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findAllPackagesByUserIdAndLocation(userId, location, rowFilter);
        
        LoggingUtil.debug(logger, "all packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findOpenPackagesByUserIdAndLocation(String userId, Location location,
            RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding open packages  by user id:"+userId + ", location:"+location+ ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findOpenPackagesByUserIdAndLocation(userId,location,rowFilter );
        
        LoggingUtil.debug(logger, "open packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findAvailablePackagesByRecepientIdAndLocation(String recipientUserId,
            Location location, RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding available packages  by recipient id:"+recipientUserId + ", location:"+location+ ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findAvailablePackagesByRecepientIdAndLocation(recipientUserId, location, rowFilter);
        
        LoggingUtil.debug(logger, "available packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findAllPackagesByRecepientIdAndLocation(String recipientUserId,
            Location location, RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding all  packages  by recipient id:"+recipientUserId + ", location:"+location+ ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findAllPackagesByRecepientIdAndLocation(recipientUserId, location, rowFilter);
        
        LoggingUtil.debug(logger, "available packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findOpenPackagesByRecepientIdAndLocation(String recipientUserId,
            Location location, RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding open  packages  by recipient id:"+recipientUserId + ", location:"+location+ ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findOpenPackagesByRecepientIdAndLocation(recipientUserId, location, rowFilter);
        
        LoggingUtil.debug(logger, "open packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findAvailablePackagesByVenueId(String venueId, RowFilter rowFilter)
            throws ProviderException {
        
        LoggingUtil.debug(logger, "finding available  packages  by venue id:"+venueId + ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findAvailablePackagesByVenueId(venueId, rowFilter);
        
        LoggingUtil.debug(logger, "available packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findOpenPackagesByVenueId(String venueId, RowFilter rowFilter)
            throws ProviderException {
        
        LoggingUtil.debug(logger, "finding open  packages  by venue id:"+venueId + ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findOpenPackagesByVenueId(venueId, rowFilter);
        
        LoggingUtil.debug(logger, "open packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
        
    }

    @Override
    public List<Package> findAllPackagesByVenueId(String venueId, RowFilter rowFilter)
            throws ProviderException {
        
        LoggingUtil.debug(logger, "finding all  packages  by venue id:"+venueId + ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findAllPackagesByVenueId(venueId, rowFilter);
        
        LoggingUtil.debug(logger, "all packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
        
    }

    @Override
    public List<Package> findAllPackages(RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding all  packages by row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findAllPackages(rowFilter);
        
        LoggingUtil.debug(logger, "all packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
        
    }
    
    public List<Package> findAllPackagesByStatus(boolean open, RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding all  packages by status(open)["+open+"] and row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findAllPackagesByStatus(open, rowFilter);
        
        LoggingUtil.debug(logger, "all open["+open+"]packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findPackagesByUserIdAndLocationAndPackageStatus(String userId,
            Location location, boolean open, RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding packages  by user id:"+userId + ", location:"+location+ ", package status(open):"+open+", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findPackagesByUserIdAndLocationAndPackageStatus(userId, location, open, rowFilter);
        
        LoggingUtil.debug(logger, " packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
        
    }

    @Override
    public List<Package> findPackagesByRecepientIdAndLocationAndPackageStatus(
            String recipientUserId, Location location, boolean open, RowFilter rowFilter)
            throws ProviderException {
        
        LoggingUtil.debug(logger, "finding packages  by recepient id:"+recipientUserId + ", location:"+location+ ", package status(open):"+open+", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findPackagesByUserIdAndLocationAndPackageStatus(recipientUserId, location, open, rowFilter);
        
        LoggingUtil.debug(logger, " packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }

    @Override
    public List<Package> findPackagesByVenueIdIdAndPackageStatus(String venueId, boolean open,
            RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding packages  by venue id:"+venueId + ", package status(open):"+open+", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findPackagesByVenueIdIdAndPackageStatus(venueId, open, rowFilter);
        
        LoggingUtil.debug(logger, " packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
        
    }

    @Override
    public List<Package> findPackagesByLocationAndPackageStatus(Location location, boolean open,
            RowFilter rowFilter) throws ProviderException {
        
        LoggingUtil.debug(logger, "finding packages  by location:"+location+ ", package status(open):"+open+", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findPackagesByLocationAndPackageStatus(location, open, rowFilter);
        
        LoggingUtil.debug(logger, " packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
        
    }

    @Override
    public List<Package> findPackagesByLocation(Location location, RowFilter rowFilter)
            throws ProviderException {
        
        LoggingUtil.debug(logger, "finding packages  by location:"+location+ ", row filter:"+rowFilter);
        
        List<Package> packageList = packageRepository.findPackagesByLocation(location, rowFilter);
        
        LoggingUtil.debug(logger, " packages size:"+ (packageList == null ? "0":packageList.size()));
        
        return packageList;
    }
    
}
