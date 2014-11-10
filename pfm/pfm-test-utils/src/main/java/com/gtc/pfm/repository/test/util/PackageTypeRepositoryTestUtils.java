package com.gtc.pfm.repository.test.util;

import com.gtc.pfm.domain.PackageType;
import com.gtc.pfm.domain.PackageTypeEnum;

/**
 * provides mock implementation and test util methods for {@link PackageType}
 * 
 * @author stanriku
 *
 */
public class PackageTypeRepositoryTestUtils {

    /**
     * 
     */
    public PackageTypeRepositoryTestUtils() {
        
    }
    
    /**
     * creates in-memory not persistent {@link PackageType}
     * @param type
     * @param name
     * @return PackageType
     */
    public PackageType createPackageTypeMock(int type, String name) {
        PackageType packageType = new PackageType(type,name);
        return packageType;
    }
    
    /**
     * creates in-memory "private" {@link PacakgeType}
     * @return PackageType
     */
    public PackageType createPrivatePackageTypeMock() {
        PackageType packageType = new PackageType(PackageTypeEnum.PRIVATE.getType(),
                PackageTypeEnum.PRIVATE.getName());
        return packageType;
    }
    
    /**
     * creates in-memory "public" {@link PacakgeType}
     * @return PackageType
     */
    public PackageType createPublicPackageTypeMock() {
        PackageType packageType = new PackageType(PackageTypeEnum.PUBLIC.getType(),
                PackageTypeEnum.PUBLIC.getName());
        return packageType;
    }
    
    /**
     * creates in-memory "promotion" {@link PacakgeType}
     * @return PackageType
     */
    public PackageType createPromotionPackageTypeMock() {
        PackageType packageType = new PackageType(PackageTypeEnum.PROMOTION.getType(),
                PackageTypeEnum.PROMOTION.getName());
        return packageType;
    }

}
