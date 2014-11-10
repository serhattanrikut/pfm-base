/**
 * 
 */
package com.gtc.pfm.service.user;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.domain.User;
import com.gtc.pfm.persistence.repository.user.UserRepository;
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
@Service("UserService")
public class UserServiceImpl extends PfmServiceBase implements UserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * default constructor
     */
    public UserServiceImpl() {
        
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.service.user.UserService#creaetUser(com.gtc.pfm.domain.User)
     */
    @Override
    public String creaetUser(User user) throws ProviderException, ObjectExistsException {
        
        LoggingUtil.debug(logger, "creating user:"+user);
        
        String userId = userRepository.creaetUser(user);
        
        LoggingUtil.debug(logger, "user created with id:"+userId);
        
        return userId;
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.service.user.UserService#findUserById(java.lang.String)
     */
    @Override
    public User findUserById(String id) throws ProviderException, ObjectNotFoundException, InvalidArgumentException {
        
        LoggingUtil.debug(logger, "finding user by id:"+id);
        
        User user = this.userRepository.findUserById(id);
        
        LoggingUtil.debug(logger, "found user by id:"+id);
        
        return user;
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.service.user.UserService#findUserByName(java.lang.String)
     */
    @Override
    public User findUserByName(String name) throws ProviderException, ObjectNotFoundException, InvalidArgumentException {
        
        LoggingUtil.debug(logger, "finding user by user name:"+name);
        
        User user = this.userRepository.findUserByName(name);
        
        LoggingUtil.debug(logger, "found user by user name:"+name);
        
        return user;
    }

    /* (non-Javadoc)
     * @see com.gtc.pfm.service.user.UserService#findAllUsers(int, int)
     */
    @Override
    public List<User> findAllUsers(int firstRow, int lastRow) throws ProviderException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the userRepository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * @param userRepository the userRepository to set
     */
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
}
