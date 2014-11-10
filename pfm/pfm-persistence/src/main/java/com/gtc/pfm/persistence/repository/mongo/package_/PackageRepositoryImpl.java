/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.package_;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.domain.Location;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.persistence.repository.mongo.PfmMongoRepositoryBase;
import com.gtc.pfm.persistence.repository.package_.PackageRepository;

/**
 * provides data access methods for {@link Package}s
 * 
 * @author stanriku
 *
 */
@Repository
public class PackageRepositoryImpl extends PfmMongoRepositoryBase implements PackageRepository{

    private static Log  logger = LogFactory.getLog(PackageRepositoryImpl.class);
    
    /**
     * default constructor
     */
    public PackageRepositoryImpl() {
        
    }

    
    @Override
    public List<Package> findAllPackages(RowFilter rowFilter) throws ProviderException {
        
        if(rowFilter == null)
            throw new ProviderException("rowFilter can not be null");
        
        List<Package> packageList = getMongoTemplate().findAll(Package.class);
   
        return packageList;
    }
    
    @Override
    public List<Package> findAllPackagesByStatus(boolean open, RowFilter rowFilter) throws ProviderException {
        
        if(rowFilter == null)
            throw new ProviderException("rowFilter can not be null");
        
        Query  query = Query.query( where(Package.openField).is(open) ).
                skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow());

        List<Package> packageList =  getMongoTemplate().find(query, Package.class);
        
        return packageList;
    }

    
    @Override
    public String createPackage(Package _package) throws ProviderException, ObjectExistsException {
        
        getMongoTemplate().insert(_package);
       
        return _package.getId();
    }


    @Override
    public void updatePackage(Package _package) throws ProviderException, ObjectNotFoundException {
        
        getMongoTemplate().updateFirst(query(where("_id").is(ObjectId.massageToObjectId(_package.id))),
               new Update().set("updated", Calendar.getInstance().getTime()), Package.class);
        
        
    }

    @Override
    public void deletePackage(String id) throws ProviderException, ObjectNotFoundException {
       
        Package package_ = getMongoTemplate().findAndRemove(query(where("_id").is(ObjectId.massageToObjectId(id))), Package.class);
        
        if(package_ == null) {
            throw new ObjectNotFoundException(id, null, "package not found by id:"+id);
        }
        
    }

    
    @Override
    public void openPackage(String packageId, String userId) throws ProviderException,
            ObjectNotFoundException {
        
        LoggingUtil.debug(logger, "user["+userId+"] is opening package["+packageId + "]");
        
        getMongoTemplate().updateFirst(query(where("_id").is(ObjectId.massageToObjectId(packageId)).and(Package.recipient_id_Filed).
                is(ObjectId.massageToObjectId(userId))),
                new Update().set(Package.openField, Boolean.TRUE), Package.class);
         
        LoggingUtil.debug(logger, "user["+userId+"] has openined package["+packageId + "]");
        
    }

    @Override
    public Package findPackageById(String id) throws ProviderException, ObjectNotFoundException {
        
        LoggingUtil.debug(logger, "finding package by id:"+id);
        
        Package package_ = getMongoTemplate().findById(id, Package.class);
        
        if(package_ == null) {
            throw new ObjectNotFoundException(id,"Package is not found by id:"+id);
        }
        
        return package_;
    }


    @Override
    public List<Package> findAvailablePackagesByUserIdAndLocation(String userId, Location location, RowFilter rowFilter)
            throws ProviderException {
        
        return findPackagesByUserIdAndLocationAndPackageStatus(userId, location, false, rowFilter);
    }

    @Override
    public List<Package> findAllPackagesByUserIdAndLocation(String userId, Location location, RowFilter rowFilter)
            throws ProviderException {
        
        Point point = PackageRepositoryUtil.convertPoint(location);
        NearQuery query = NearQuery.near(point).maxDistance(new Distance(location.getDistance(), Metrics.KILOMETERS)).query(
                query( where(Package.owner_id_Filed).is(ObjectId.massageToObjectId(userId)) ).
                skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow()) );

        GeoResults<Package> geoPackages =  getMongoTemplate().geoNear(query, Package.class);
        
        List<Package> packageList = PackageRepositoryUtil.convertGeoPackages(geoPackages);
        
        return packageList;
    }
    
    @Override
    public List<Package> findOpenPackagesByUserIdAndLocation(String userId, Location location, RowFilter rowFilter)
            throws ProviderException {
        
        return findPackagesByUserIdAndLocationAndPackageStatus(userId, location, true, rowFilter);
    }


    @Override
    public List<Package> findPackagesByUserIdAndLocationAndPackageStatus(String userId, Location location, boolean open, RowFilter rowFilter)
            throws ProviderException {
        
        Point point = PackageRepositoryUtil.convertPoint(location);
        NearQuery query = NearQuery.near(point).maxDistance(new Distance(location.getDistance(), Metrics.KILOMETERS)).query(
                query( where(Package.owner_id_Filed).is(ObjectId.massageToObjectId(userId)).and(Package.openField).is(open) ).
                skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow()) );

        GeoResults<Package> geoPackages =  getMongoTemplate().geoNear(query, Package.class);
        
        List<Package> packageList = PackageRepositoryUtil.convertGeoPackages(geoPackages);
        
        return packageList;
        
    }

    @Override
    public List<Package> findAvailablePackagesByRecepientIdAndLocation(String recipientUserId,
            Location location, RowFilter rowFilter) throws ProviderException {
        
        return  findPackagesByRecepientIdAndLocationAndPackageStatus(recipientUserId, location, false, rowFilter);
    }

    @Override
    public List<Package> findAllPackagesByRecepientIdAndLocation(String recipientUserId,
            Location location, RowFilter rowFilter) throws ProviderException {
        
        Point point = PackageRepositoryUtil.convertPoint(location);
        NearQuery query = NearQuery.near(point).maxDistance(new Distance(location.getDistance(), Metrics.KILOMETERS)).query(
                query( where(Package.recipient_id_Filed).is(ObjectId.massageToObjectId(recipientUserId)) ).
                skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow()) );

        GeoResults<Package> geoPackages =  getMongoTemplate().geoNear(query, Package.class);
        
        List<Package> packageList = PackageRepositoryUtil.convertGeoPackages(geoPackages);
        
        return packageList;
    }

    @Override
    public List<Package> findOpenPackagesByRecepientIdAndLocation(String recipientUserId,
            Location location, RowFilter rowFilter) throws ProviderException {
        
        return findPackagesByRecepientIdAndLocationAndPackageStatus(recipientUserId, location, true, rowFilter);
    }


    @Override
    public List<Package> findPackagesByRecepientIdAndLocationAndPackageStatus(String recipientUserId, Location location, boolean open, RowFilter rowFilter)
            throws ProviderException {
        
        Point point = PackageRepositoryUtil.convertPoint(location);
        NearQuery query = NearQuery.near(point).maxDistance(new Distance(location.getDistance(), Metrics.KILOMETERS)).query(
                query( where(Package.recipient_id_Filed).is(ObjectId.massageToObjectId(recipientUserId)).and(Package.openField).is(open) ).
                skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow()) );

        GeoResults<Package> geoPackages =  getMongoTemplate().geoNear(query, Package.class);
        
        List<Package> packageList = PackageRepositoryUtil.convertGeoPackages(geoPackages);
        
        return packageList;
        
    }

    @Override
    public List<Package> findAvailablePackagesByVenueId(String venueId, RowFilter rowFilter)
            throws ProviderException {
        
        return findPackagesByVenueIdIdAndPackageStatus(venueId, false, rowFilter);
    }

    @Override
    public List<Package> findOpenPackagesByVenueId(String venueId, RowFilter rowFilter)
            throws ProviderException {
        
        return findPackagesByVenueIdIdAndPackageStatus(venueId, true, rowFilter);
    }

    
    @Override
    public List<Package> findPackagesByVenueIdIdAndPackageStatus(String venueId, boolean open, RowFilter rowFilter)
            throws ProviderException {
        
        Query  query = Query.query( where(Package.venueRef_id_Field).is(ObjectId.massageToObjectId(venueId)).and(Package.openField).is(open) ).
                skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow());

        List<Package> packageList =  getMongoTemplate().find(query, Package.class);
        
        return packageList;
        
    }
    
    @Override
    public List<Package> findAllPackagesByVenueId(String venueId, RowFilter rowFilter)
            throws ProviderException {
        
        Query  query = Query.query( where(Package.venueRef_id_Field).is(ObjectId.massageToObjectId(venueId)) ).
                skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow());

        List<Package> packageList =  getMongoTemplate().find(query, Package.class);
        
        return packageList;
    }

    @Override
    public List<Package> findPackagesByLocationAndPackageStatus(Location location, boolean open, RowFilter rowFilter)
            throws ProviderException {
        
        Point point = PackageRepositoryUtil.convertPoint(location);
        NearQuery query = NearQuery.near(point).maxDistance(new Distance(location.getDistance(), Metrics.KILOMETERS)).query(
                query( where(Package.openField).is(open) ).
                skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow()) );

        GeoResults<Package> geoPackages =  getMongoTemplate().geoNear(query, Package.class);
        
        List<Package> packageList = PackageRepositoryUtil.convertGeoPackages(geoPackages);
        
        return packageList;
    }
    
    @Override
    public List<Package> findPackagesByLocation(Location location, RowFilter rowFilter)
            throws ProviderException {
        
        Point point = PackageRepositoryUtil.convertPoint(location);
        NearQuery query = NearQuery.near(point).maxDistance(new Distance(location.getDistance(), Metrics.KILOMETERS));
        
        query.query( query(new Criteria().all(Package.class)).skip(rowFilter.getFirstRow()).limit(rowFilter.getLastRow()) );

        GeoResults<Package> geoPackages =  getMongoTemplate().geoNear(query, Package.class);
        
        List<Package> packageList = PackageRepositoryUtil.convertGeoPackages(geoPackages);
        
        return packageList;
    }

}
