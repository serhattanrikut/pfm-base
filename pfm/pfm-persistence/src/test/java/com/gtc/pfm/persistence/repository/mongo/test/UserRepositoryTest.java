/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.test;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.gtc.pfm.commons.security.PfmAuthorities;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.User;
import com.gtc.pfm.domain.aa.Group;
import com.gtc.pfm.domain.aa.Role;
import com.gtc.pfm.persistence.repository.aa.GroupRepository;
import com.gtc.pfm.persistence.repository.aa.RoleRepository;
import com.gtc.pfm.persistence.repository.user.UserRepository;

/**
 * @author stanriku
 *
 */
@ContextConfiguration
public class UserRepositoryTest extends MongoTestBase {

    @Autowired 
    UserRepository repository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    GroupRepository groupRepository;
    
    @Before 
    public void setUp() throws Exception {
        super.setUp();
        super.dropCollection(User.class);
        super.createCollection(User.class);
    }

    @Test
    public void testCreateUser() throws Exception {
       
        Role admin = new Role(PfmAuthorities.ADMINISTRATOR.getAuthority(), "admin role");
        
        roleRepository.createRole(admin);
        
        Group adminGroup = new Group(PfmAuthorities.ADMINISTRATOR.getAuthority(), "admin group");
        adminGroup.addRole(admin);
        
        groupRepository.createGroup(adminGroup);
       
        User user = new User("serhattanrikut@gmail.com", "serhattanrikut", "serhattanrikut", "serhat", "tanrikut",
                "st", "st", 6, 6, "m", "Istanbul", "Istanbul 3400", "5305467694", "", 5,adminGroup,Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        repository.creaetUser(user);
        
        Assert.assertNotNull(user.getId());
    }
   

    @After 
    public void tearDown() throws Exception {
        super.tearDown();
        repository.deleteAll(Package.class);
    }

}
