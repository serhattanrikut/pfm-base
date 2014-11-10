/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.test;

import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.PackageType;
import com.gtc.pfm.persistence.repository.packagetype.PackageTypeRepository;
import com.gtc.pfm.repository.test.util.PackageTypeRepositoryTestUtils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Test class for {@link PackageTypeRepository}. Since there is no explicit test spring context xml file 
 * is configured in {@link @ContextConfiguration} annotation,  PackageTypeRepositoryTest-context.xml is searched 
 * and loaded under directory with the same path of this test class in test/resource folder.
 * 
 * @author stanriku
 *
 */
@ContextConfiguration
public class PackageTypeRepositoryTest extends MongoTestBase {

    @Autowired
    private PackageTypeRepository repository;
    public static PackageTypeRepositoryTestUtils packageTypeRepositoryTestUtils = new PackageTypeRepositoryTestUtils();
    
    /**
     * 
     */
    public PackageTypeRepositoryTest() {
        
    }

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void beforeCalss() throws Exception {
        MongoTestBase.beforeCalss();
        
    }

    /**
     * 
     * @throws Exception
     */
    @AfterClass
    public static void afterClass() throws Exception {
        MongoTestBase.afterClass();
    }
    
    @Test
    public void testCreatePackageTypeTest() throws Exception {
        PackageType packageType = packageTypeRepositoryTestUtils.createPrivatePackageTypeMock();
        String id = repository.createPackageType(packageType);
        Assert.assertNotNull(id);
        packageType = repository.findPackageTypeById(id);
        Assert.assertNotNull(packageType);
    }
    
    /**
     * 
     */
    @Before 
    public void setUp() {

        mongoTemplate.dropCollection("packageType");
        mongoTemplate.createCollection("packageType");

    }
    
    @After 
    public void tearDown() throws ProviderException {
        repository.deleteAll(PackageType.class);
    }

    
    
    /**
     * @return the repository
     */
    public PackageTypeRepository getRepository() {
        return repository;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(PackageTypeRepository repository) {
        this.repository = repository;
    }
}
