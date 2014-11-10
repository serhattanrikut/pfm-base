/**
 * 
 */
package com.gtc.pfm.service.aa;

import java.util.List;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.aa.Group;
import com.gtc.pfm.service.PfmService;

/**
 * @author stanriku
 *
 */
public interface GroupService extends PfmService {

	/**
	 * creates {@link Group}
	 * 
	 * @param name
	 * @param description
	 * @return {@link Group}
	 * @throws ProviderException
	 * @throws ObjectExistsException
	 */
	public Group createGroup(String name, String description) throws ProviderException, ObjectExistsException, InvalidArgumentException;
	
	/**
	 * creates {@link Group}
	 * 
	 * @param group
	 * @return
	 * @throws ProviderException
	 * @throws ObjectExistsException
	 * @throws InvalidArgumentException
	 */
	public Group createGroup(Group group) throws ProviderException, ObjectExistsException, InvalidArgumentException;
	 
	/**
	 * finds {@link Group} by the given id
	 * 
	 * @param id
	 * @return {@link Group}
	 * @throws ProviderException
	 * @throws ObjectNotFoundException
	 * @throws InvalidArgumentException
	 */
	public Group findGroupById(String id) throws ProviderException, ObjectNotFoundException, InvalidArgumentException;
	
	/**
	 * finds {@link Group} by the given name
	 * 
	 * @param name
	 * @return {@link Group}
	 * @throws ProviderException
	 * @throws ObjectNotFoundException
	 * @throws InvalidArgumentException
	 */
	public Group findGroupByName(String name) throws ProviderException,ObjectNotFoundException, InvalidArgumentException;
	
	/**
	 * deletes {@link Group} by the given id
	 * 
	 * @param id
	 * @throws ProviderException
	 * @throws ObjectNotFoundException
	 * @throws InvalidArgumentException
	 */
	public void deleteGroupById(String id) throws ProviderException, ObjectNotFoundException, InvalidArgumentException;
	
	/**
	 * deletes {@link Group} by the given name
	 * @param name
	 * @throws ProviderException
	 * @throws ObjectNotFoundException
	 * @throws InvalidArgumentException
	 */
	public void deleteGroupByName(String name) throws ProviderException,ObjectNotFoundException, InvalidArgumentException;
	/**
	 * finds all Groups
	 * 
	 * @param firstRow
	 * @param lastRow
	 * @return
	 * @throws ProviderException
	 */
	List<Group>  findAllGroups(int firstRow, int lastRow) throws ProviderException;


}
