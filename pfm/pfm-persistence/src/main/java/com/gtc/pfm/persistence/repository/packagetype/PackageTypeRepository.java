/**
 * 
 */
package com.gtc.pfm.persistence.repository.packagetype;

import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.PackageType;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.persistence.repository.PfmRepositoryInft;

import java.util.List;

/**
 * This interface identifies all data access methods related with {@link PackageType} exposed to other layers and services
 * 
 * @author stanriku
 *
 */
public interface PackageTypeRepository extends PfmRepositoryInft{

    /**
     * creates {@link PackageType}
     * 
     * @param packageType
     * @return String
     * @throws ProviderException
     */
   public String createPackageType(PackageType packageType) throws ProviderException;
   
   /**
    * deletes {@link PackageType} by id
    * 
    * @param id
    * @throws ProviderException
    * @throws ObjectNotFoundException
    */
   public void deletePacakgeType(String id) throws ObjectNotFoundException, ProviderException;
   
   /**
    * updates {@link PackageType}
    * 
    * @param packageType
    * @throws ObjectNotFoundException
    * @throws ProviderException
    */
   public void updatePackageType(PackageType packageType) throws ObjectNotFoundException,ProviderException;
   
   /**
    * finds {@link PackageType} by id
    * 
    * @param id
    * @return {@link PackageType}
    * @throws ObjectNotFoundException
    * @throws ProviderException
    */
   public PackageType findPackageTypeById(String id) throws ObjectNotFoundException,ProviderException;
   
   /**
    * finds {@link PackageType} by name
    * 
    * @param name
    * @return {@link PackageType}
    * @throws ObjectNotFoundException
    * @throws ProviderException
    */
   public PackageType findPackageTypeByName(String name) throws ObjectNotFoundException,ProviderException;
   
   /**
    * finds {@link PackageType} by type code
    * 
    * @param type
    * @return {@link PackageType}
    * @throws ObjectNotFoundException
    * @throws ProviderException
    */
   public PackageType findPackageTypeByType(int type) throws ObjectNotFoundException,ProviderException;
   
   /**
    * finds all {@link PackageType}s
    * 
    * @param rowFilter
    * @return {@link PackageType}
    * @throws ProviderException
    */
   public List<PackageType> findAllPackageTypes(RowFilter rowFilter) throws ProviderException;
   
}
