/**
 * 
 */
package com.gtc.pfm.persistence.repository.aa;

import java.util.List;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.aa.GroupRoles;
import com.gtc.pfm.domain.aa.Role;
import com.gtc.pfm.persistence.repository.PfmRepositoryInft;

/**
 * @author stanriku
 *
 */
public interface RoleRepository  extends PfmRepositoryInft{

	/**
	 * creates {@link Role}
	 * 
	 * @param name
	 * @param description
	 * @return {@link Role}
	 * @throws ProviderException
	 * @throws ObjectExistsException
	 */
	public Role createRole(String name, String description) throws ProviderException ,ObjectExistsException, InvalidArgumentException;
	
	/**
	 * creates {@link Role}
	 * 
	 * @param role
	 * @return role id as String
	 * @throws ProviderException
	 * @throws ObjectExistsException
	 * @throws InvalidArgumentException
	 */
	public String createRole(Role role) throws ProviderException ,ObjectExistsException, InvalidArgumentException;
	 
	/**
	 * finds {@link Role} by the given id
	 * 
	 * @param id
	 * @return {@link Role}
	 * @throws ProviderException
	 * @throws ObjectNotFoundException
	 * @throws InvalidArgumentException
	 */
	public Role findRoleById(String id) throws ProviderException, ObjectNotFoundException, InvalidArgumentException;
	
	/**
	 * finds {@link Role} by the given name
	 * 
	 * @param name
	 * @return {@link Role}
	 * @throws ProviderException
	 * @throws ObjectNotFoundException
	 * @throws InvalidArgumentException
	 */
	public Role findRoleByName(String name) throws ProviderException,ObjectNotFoundException, InvalidArgumentException;
	
	/**
	 * deletes {@link Role} by the given id
	 * 
	 * @param id
	 * @throws ProviderException
	 * @throws ObjectNotFoundException
	 * @throws InvalidArgumentException
	 */
	public void deleteRoleById(String id) throws ProviderException, ObjectNotFoundException, InvalidArgumentException;
	
	/**
	 * deletes {@link Role} by the given name
	 * @param name
	 * @throws ProviderException
	 * @throws ObjectNotFoundException
	 * @throws InvalidArgumentException
	 */
	public void deleteRoleByName(String name) throws ProviderException,ObjectNotFoundException, InvalidArgumentException;
	/**
	 * finds all roles
	 * 
	 * @param firstRow
	 * @param lastRow
	 * @return
	 * @throws ProviderException
	 */
	public List<Role>  findAllRoles(int firstRow, int lastRow) throws ProviderException;
	
	/**
	 * finds all group and role relations by group id
	 * @param groupId
	 * @return list of {@link GroupRoles}
	 * @throws ProviderException
	 * @throws InvalidArgumentException
	 */
	public List<GroupRoles> findGroupRolesByGroupId(String groupId) throws ProviderException, InvalidArgumentException;
	
	/**
	 * finds all all group and role relations by role id
	 * @param roleId
	 * @return list of {@link GroupRoles}
	 * @throws ProviderException
	 * @throws InvalidArgumentException
	 */
	public List<GroupRoles> findGroupRolesByRoleId(String roleId) throws ProviderException, InvalidArgumentException;
	
	/**
	 * finds 
	 * @param groupId
	 * @return list of {@link Role}
	 * @throws ProviderException
	 * @throws InvalidArgumentException
	 * @throws ObjectNotFoundException
	 */
	public List<Role> findRolesByGroupId(String groupId) throws ProviderException, InvalidArgumentException, ObjectNotFoundException;

}
