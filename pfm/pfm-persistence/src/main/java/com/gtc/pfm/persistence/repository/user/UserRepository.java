/**
 * 
 */
package com.gtc.pfm.persistence.repository.user;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.User;
import com.gtc.pfm.persistence.repository.PfmRepositoryInft;

import java.util.List;


/**
 * This interface identifies all data access methods related with {@link User} exposed to other layers and services
 * 
 * @author stanriku
 *
 */
public interface UserRepository extends PfmRepositoryInft{

    /**
     * persists given {@link User} into repository
     * 
     * @param user
     * @return String id
     * @throws ProviderException
     * @throws ObjectExistsException
     */
    public String creaetUser(User user) throws ProviderException,ObjectExistsException;
    
    /**
     * fetches user by id
     * 
     * @param id
     * @return {@link User}
     * @throws ProviderException
     * @throws ObjectNotFoundException
     */
    public User findUserById(String id) throws ProviderException, ObjectNotFoundException, InvalidArgumentException;
    
    /**
     * fetches user by user name
     * 
     * @param name
     * @return {@link User}
     * @throws ProviderException
     * @throws ObjectNotFoundException
     */
    public User findUserByName(String name) throws ProviderException, ObjectNotFoundException, InvalidArgumentException;
    
    /**
     * returns {@link User}s in between given row range
     * 
     * @param firstRow
     * @param lastRow
     * @return list of {@link User}s
     * @throws ProviderException
     */
    public List<User> findAllUsers(int firstRow, int lastRow) throws ProviderException;
    
    
}
