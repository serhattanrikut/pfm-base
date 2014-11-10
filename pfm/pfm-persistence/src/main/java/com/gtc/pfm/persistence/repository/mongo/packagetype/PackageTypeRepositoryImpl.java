/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.packagetype;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.domain.PackageType;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.persistence.repository.mongo.PfmMongoRepositoryBase;
import com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

/**
 * mongodb implementation of {@link PackageTypeRepository}
 * 
 * @author stanriku
 *
 */
@Repository
public class PackageTypeRepositoryImpl extends PfmMongoRepositoryBase implements
        PackageTypeRepository {

    private static final Log logger = LogFactory.getLog(PackageTypeRepositoryImpl.class);

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository#createPackageType(com.gtc.pfm.domain.PackageType)
     */
    @Override
    public String createPackageType(PackageType packageType) throws ProviderException {
        
        if(packageType == null) 
            throw new ProviderException("package type can not be null");
        else 
            LoggingUtil.trace(logger, "creating package type:"+packageType);
        
        getMongoTemplate().save(packageType);
        LoggingUtil.trace(logger, "creatied package type with id:"+packageType.getId());
        
        return packageType.getId();
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository#deletePacakgeType(java.lang.String)
     */
    @Override
    public void deletePacakgeType(String id) throws ObjectNotFoundException, ProviderException {
        
        if(id == null || id.length() == 0)
            throw new ProviderException("id can not be null");
        else
            LoggingUtil.trace(logger, "deleting package type by id:"+id);
        
        PackageType packageType =  getMongoTemplate().findAndRemove(query(where("_id").is(ObjectId.massageToObjectId(id))), PackageType.class);
        
        if(packageType == null)
            throw new ObjectNotFoundException(id,null,"could not find PackageType by id:"+id);
        LoggingUtil.trace(logger, "deleted package type"+packageType);

    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository#updatePackageType(com.gtc.pfm.domain.PackageType)
     */
    @Override
    public void updatePackageType(PackageType packageType) throws ObjectNotFoundException,
            ProviderException {
        
        if(packageType == null) 
            throw new ProviderException("package type can not be null");
        else 
            LoggingUtil.trace(logger, "updating package type:"+packageType);
        
        getMongoTemplate().updateFirst(query(where("_id").is(ObjectId.massageToObjectId(packageType.id))),
                new Update().set("updated", Calendar.getInstance().getTime()).set(PackageType.typeField, packageType.getType()).
                set(PackageType.nameFiled, packageType.getName()), PackageType.class);
        
        LoggingUtil.trace(logger, "updated package type");

    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository#findPackageTypeById(java.lang.String)
     */
    @Override
    public PackageType findPackageTypeById(String id) throws ObjectNotFoundException,
            ProviderException {
        
        if(id == null || id.length() == 0)
            throw new ProviderException("id can not be null");
        else
            LoggingUtil.trace(logger, "finding package type by id:"+id);
        
        PackageType packageType =  getMongoTemplate().findAndRemove(query(where("_id").is(ObjectId.massageToObjectId(id))), PackageType.class);
        
        if(packageType == null)
            throw new ObjectNotFoundException(id,null,"could not find PackageType by id:"+id);
        LoggingUtil.trace(logger, "found package type"+packageType);
        return packageType;
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository#findPackageTypeByName(java.lang.String)
     */
    @Override
    public PackageType findPackageTypeByName(String name) throws ObjectNotFoundException,
            ProviderException {
        
        if(name == null || name.length() == 0)
            throw new ProviderException("name can not be null");
        else
            LoggingUtil.trace(logger, "finding package type by name:"+name);
        
        PackageType packageType =  getMongoTemplate().findAndRemove(query(where(PackageType.nameFiled).is(ObjectId.massageToObjectId(name))), PackageType.class);
        
        if(packageType == null)
            throw new ObjectNotFoundException(null,name,"could not find PackageType by name:"+name);
        LoggingUtil.trace(logger, "found package type"+packageType);
        return packageType;
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository#findPackageTypeByType(int)
     */
    @Override
    public PackageType findPackageTypeByType(int type) throws ObjectNotFoundException,
            ProviderException {

        LoggingUtil.trace(logger, "finding package type by type:"+type);
        
        PackageType packageType =  getMongoTemplate().findAndRemove(query(where(PackageType.typeField).is(ObjectId.massageToObjectId(type))), PackageType.class);
        
        if(packageType == null)
            throw new ObjectNotFoundException("could not find PackageType by name:"+type);
        LoggingUtil.trace(logger, "found package type"+packageType);
        return packageType;
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository#findAllPackageTypes()
     */
    @Override
    public List<PackageType> findAllPackageTypes(RowFilter rowFilter) throws ProviderException {
        if(rowFilter == null)
            throw new ProviderException("row filter can not be null");
        LoggingUtil.trace(logger, "finding all package types");
        List<PackageType> packageTypes = getMongoTemplate().findAll(PackageType.class);
        LoggingUtil.trace(logger, "found package types size:"+ packageTypes != null ? packageTypes.size():0);
        return packageTypes;
    }

}
