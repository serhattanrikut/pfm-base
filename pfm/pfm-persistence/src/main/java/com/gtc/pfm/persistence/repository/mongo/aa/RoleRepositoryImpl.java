/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.aa;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.commons.validator.ValidatorUtil;
import com.gtc.pfm.domain.aa.GroupRoles;
import com.gtc.pfm.domain.aa.Role;
import com.gtc.pfm.persistence.repository.aa.RoleRepository;
import com.gtc.pfm.persistence.repository.mongo.PfmMongoRepositoryBase;

/**
 * @author stanriku
 *
 */
@Repository
public class RoleRepositoryImpl extends PfmMongoRepositoryBase implements RoleRepository {

	private static Log  logger = LogFactory.getLog(RoleRepositoryImpl.class);
	
	/**
	 * 
	 */
	public RoleRepositoryImpl() {
		
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.PfmRepositoryInft#deleteAll(java.lang.Class)
	 */
	@Override
	public <T> void deleteAll(Class<T> entityClass) throws ProviderException {
		logger.debug("deleting all " + entityClass.getName());
		getMongoTemplate().dropCollection(entityClass);
		logger.debug("deleted all " + entityClass.getName());

	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.RoleRepository#createRole(java.lang.String, java.lang.String)
	 */
	@Override
	public Role createRole(String name, String description)
			throws ProviderException, ObjectExistsException,
			InvalidArgumentException {
		Role role = new Role(name, description);
		createRole(role);
		return role;
	}
	
	
	@Override
	public String createRole(Role role) throws ProviderException,
			ObjectExistsException, InvalidArgumentException {
		getMongoTemplate().insert(role);
		logger.debug("created role:"+role);
		return role.getId();
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.RoleRepository#findRoleById(java.lang.String)
	 */
	@Override
	public Role findRoleById(String id) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		ValidatorUtil.stringNullCheck(id, "role id can not be null or zero length while finding role by id");
		Role role = getMongoTemplate().findById(id, Role.class);
		
		if (role == null) {
			logger.debug("could not found role by id" + id);
			throw new ObjectNotFoundException(id, "",
					"could not found role by id" + id);
		}
		
		logger.debug(role + " found by id:"+id);
		return role;
		
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.RoleRepository#findRoleByName(java.lang.String)
	 */
	@Override
	public Role findRoleByName(String name) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		ValidatorUtil.stringNullCheck(name, "role id can not be null or zero length while finding role by name");
		Role role = getMongoTemplate().findOne(query(where(Role.nameField).is(name)), Role.class);
		
		if (role == null) {
			logger.debug("could not found role by name" + name);
			throw new ObjectNotFoundException(name, "",
					"could not found role by name" + name);
		}
		
		logger.debug(role + " found by name:"+name);
		return role;
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.RoleRepository#deleteRoleById(java.lang.String)
	 */
	@Override
	public void deleteRoleById(String id) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		Role role = findRoleById(id);
		getMongoTemplate().remove(role);
		logger.debug(role + " has been deleted.");

	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.RoleRepository#deleteRoleByName(java.lang.String)
	 */
	@Override
	public void deleteRoleByName(String name) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		Role role = findRoleByName(name);
		getMongoTemplate().remove(role);
		logger.debug(role + " has been deleted.");
		
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.RoleRepository#findAllRoles(int, int)
	 */
	@Override
	public List<Role> findAllRoles(int firstRow, int lastRow)
			throws ProviderException {
		
		List<Role> roles = getMongoTemplate().findAll(Role.class);
		return roles;
	}
	
	@Override
	public List<GroupRoles> findGroupRolesByGroupId(String groupId) throws ProviderException, InvalidArgumentException {
		
		ValidatorUtil.stringNullCheck(groupId, "group id can not be null.");
		Query query = new Query(Criteria.where(GroupRoles.groupIdField).is(groupId));
		List<GroupRoles> groupRolesList = getMongoTemplate().find(query, GroupRoles.class);
		return groupRolesList;
	}
	
	@Override
	public List<GroupRoles> findGroupRolesByRoleId(String roleId) throws ProviderException, InvalidArgumentException {
		
		ValidatorUtil.stringNullCheck(roleId, "role id can not be null.");
		Query query = new Query(Criteria.where(GroupRoles.roleIdField).is(roleId));
		List<GroupRoles> groupRolesList = getMongoTemplate().find(query, GroupRoles.class);
		return groupRolesList;
	}
	
	@Override
	public List<Role> findRolesByGroupId(String groupId) throws ProviderException, InvalidArgumentException, ObjectNotFoundException {
		
		List<GroupRoles> groupRolesList = findGroupRolesByGroupId(groupId);
		if(groupRolesList == null || groupRolesList.size() == 0) {
			throw new ObjectNotFoundException("could not find any role assigned to group:"+groupId,groupId);
		}
		return null;
	}

}
