/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.user;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.commons.validator.ValidatorUtil;
import com.gtc.pfm.domain.User;
import com.gtc.pfm.domain.aa.Group;
import com.gtc.pfm.persistence.repository.aa.GroupRepository;
import com.gtc.pfm.persistence.repository.mongo.PfmMongoRepositoryBase;
import com.gtc.pfm.persistence.repository.user.UserRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;







import java.util.List;

/**
 * provides data access methods for {@link User}s
 * 
 * @author stanriku
 *
 */
@Repository
public class UserRepositoryImpl extends PfmMongoRepositoryBase implements UserRepository {

    private static Log logger = LogFactory.getLog(UserRepositoryImpl.class);
    
    @Autowired
    private GroupRepository groupRepository;
    

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.UserRepository#creaetUser(com.gtc.pfm.domain.User)
     */
    @Override
    public String creaetUser(User user) throws ProviderException, ObjectExistsException {
        
        getMongoTemplate().insert(user);
        
        return user.getId();
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.UserRepository#findUserById(java.lang.String)
     */
    @Override
    public User findUserById(String id) throws ProviderException, ObjectNotFoundException, InvalidArgumentException {
        
        User user = getMongoTemplate().findById(id, User.class);
        
        if(user == null) {
            logger.debug("could not found user by id"+id);
            throw new ObjectNotFoundException(id, "", "could not found user by id"+id);
        }
        loadUserGroup(user, user.getGroupId());
        return user;
    }

    /**
     * loads group of given {@link User} object
     * @param user
     * @param groupId
     * @throws InvalidArgumentException
     * @throws ProviderException
     * @throws ObjectNotFoundException
     */
    private void loadUserGroup(User user, String groupId) throws InvalidArgumentException, ProviderException, ObjectNotFoundException {
    	ValidatorUtil.stringNullCheck(user.getGroupId(), "user is not assigned to any gruop yet..");
        Group group = groupRepository.findGroupById(user.getGroupId());
        user.setGroup(group);
    }
    
    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.UserRepository#findUserByName(java.lang.String)
     */
    @Override
    public User findUserByName(String name) throws ProviderException, ObjectNotFoundException, InvalidArgumentException {
        
        User user = getMongoTemplate().findOne(query(where(User.usernameField).is(name)), User.class);
        if(user == null){
            LoggingUtil.debug(logger, "could not find user by name:"+name);
            throw new ObjectNotFoundException(null,name,"could not find user by name:"+name);
        }
        loadUserGroup(user, user.getGroupId());
        
        return user;
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.persistence.repository.UserRepository#findAllUsers(int, int)
     */
    @Override
    public List<User> findAllUsers(int firstRow, int lastRow) throws ProviderException {
       
        List<User> userList = getMongoTemplate().findAll(User.class);
        return userList;
    }

	/**
	 * @return the groupRepository
	 */
	public GroupRepository getGroupRepository() {
		return groupRepository;
	}

	/**
	 * @param groupRepository the groupRepository to set
	 */
	public void setGroupRepository(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}
    

}
