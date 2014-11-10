/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.commons.security.PfmAuthorities;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.aa.Group;
import com.gtc.pfm.domain.aa.Role;
import com.gtc.pfm.persistence.repository.aa.GroupRepository;
import com.gtc.pfm.persistence.repository.aa.RoleRepository;

/**
 * @author stanriku
 *
 */
@ContextConfiguration
public class GroupRepositoryTest extends MongoTestBase {

	 @Autowired 
     private GroupRepository repository;
	 
	 @Autowired 
	 private RoleRepository roleRepository;
	
	/**
	 * 
	 */
	public GroupRepositoryTest() {
		// TODO Auto-generated constructor stub
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
    
    
    /**
     * 
     */
    @Before 
    public void setUp() {

        mongoTemplate.dropCollection("package");
        mongoTemplate.createCollection("package");

    }
    
    @After 
    public void tearDown() throws ProviderException {
        repository.deleteAll(Package.class);
    }

    @Test
    public void testCreateGroup() throws Exception {
    	
    	Role role = new Role(PfmAuthorities.ADMINISTRATOR.getAuthority(), "admin role");
    	roleRepository.createRole(role);
    	Group group = new Group("administrators","admin group");
    	group.addRole(role);
        repository.createGroup(group);
        Assert.assertNotNull(group.getId());
        Assert.assertNotNull(group.getRoles());
        Assert.assertEquals(1, group.getRoles().size());
    }

}
