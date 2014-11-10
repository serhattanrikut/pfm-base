/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.aa;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.commons.validator.ValidatorUtil;
import com.gtc.pfm.domain.aa.Group;
import com.gtc.pfm.domain.aa.GroupRoles;
import com.gtc.pfm.domain.aa.Role;
import com.gtc.pfm.persistence.repository.aa.GroupRepository;
import com.gtc.pfm.persistence.repository.aa.RoleRepository;
import com.gtc.pfm.persistence.repository.mongo.PfmMongoRepositoryBase;
import com.mongodb.WriteResult;

/**
 * @author stanriku
 *
 */
@Repository
public class GroupRepositoryImpl extends PfmMongoRepositoryBase implements GroupRepository {

	private static Log  logger = LogFactory.getLog(GroupRepositoryImpl.class);
	
	@Autowired
	private RoleRepository roleRepository;
	   
	/**
	 * 
	 */
	public GroupRepositoryImpl() {
		
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.PfmRepositoryInft#deleteAll(java.lang.Class)
	 */
	@Override
	public <T> void deleteAll(Class<T> entityClass) throws ProviderException {

		getMongoTemplate().dropCollection(Group.class);

	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.GroupRepository#createGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public Group createGroup(String name, String description)
			throws ProviderException, ObjectExistsException,
			InvalidArgumentException {
		Group group = new Group(name, description);
		getMongoTemplate().insert(group);
		logger.debug("created group:"+group);
		List<Role> roles = group.getRoles();
		for (Role role : roles) {
			if(role.getId() == null && role.getId().length() == 0) {
				try {
					roleRepository.findRoleByName(role.getName());
				} catch (ObjectNotFoundException e) {
					logger.error("could not find role by name:"+role.getName());
					throw new ProviderException(e);
				}
			}
			Update update = new Update().set(GroupRoles.groupIdField, group.getId()).set(GroupRoles.roleIdField, role.getId());
			Query query = new Query(Criteria.where(GroupRoles.groupIdField).is(group.getId()).and(GroupRoles.roleIdField).is(role.getId()));
			WriteResult upsert = getMongoTemplate().upsert(query, update, GroupRoles.class);
			logger.debug("created group_roles with id:"+upsert.getUpsertedId());
		}
	
		return group;
	}
	
	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.GroupRepository#createGroup(Group group)
	 */
	@Override
	public Group createGroup(Group group)
			throws ProviderException, ObjectExistsException,
			InvalidArgumentException {
		getMongoTemplate().insert(group);
		logger.debug("created group:"+group);
		List<Role> roles = group.getRoles();
		for (Role role : roles) {
			Update update = new Update().set(GroupRoles.groupIdField, group.getId()).set(GroupRoles.roleIdField, role.getId());
			Query query = new Query(Criteria.where(GroupRoles.groupIdField).is(group.getId()).and(GroupRoles.roleIdField).is(role.getId()));
			WriteResult upsert = getMongoTemplate().upsert(query, update, GroupRoles.class);
			logger.debug("created group_roles with id:"+upsert.getUpsertedId());
		}
	
		return group;
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.GroupRepository#findGroupById(java.lang.String)
	 */
	@Override
	public Group findGroupById(String id) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		ValidatorUtil.stringNullCheck(id, "group id can not be null or zero length while finding group by id");
		Group group = getMongoTemplate().findById(id, Group.class);
		
		if (group == null) {
			logger.debug("could not found group by id" + id);
			throw new ObjectNotFoundException(id, "",
					"could not found group by id" + id);
		}
		List<Role> roles = roleRepository.findRolesByGroupId(id);
		group.setRoles(roles);
		return group;
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.GroupRepository#findGroupByName(java.lang.String)
	 */
	@Override
	public Group findGroupByName(String name) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		ValidatorUtil.stringNullCheck(name, "group name can not be null or zero length while finding group by name");
		Group group = getMongoTemplate().findOne(query(where(Group.nameField).is(name)), Group.class);
        
        if(group == null){
            LoggingUtil.debug(logger, "could not find group by name:"+name);
            throw new ObjectNotFoundException(null,name,"could not find group by name:"+name);
        }
        
        return group;
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.GroupRepository#deleteGroupById(java.lang.String)
	 */
	@Override
	public void deleteGroupById(String id) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		ValidatorUtil.stringNullCheck(id, "group id can not be null or zero length while deleting group by id");
		Group group = findGroupById(id);
		getMongoTemplate().remove(group);
		logger.debug(group + " has been deleted");

	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.GroupRepository#deleteGroupByName(java.lang.String)
	 */
	@Override
	public void deleteGroupByName(String name) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		ValidatorUtil.stringNullCheck(name, "group name can not be null or zero length while deleting group by name");
		Group group = findGroupByName(name);
		getMongoTemplate().remove(group);
		logger.debug(group + " has been deleted");

	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.persistence.repository.aa.GroupRepository#findAllGroups(int, int)
	 */
	@Override
	public List<Group> findAllGroups(int firstRow, int lastRow)
			throws ProviderException {
		
		List<Group> group = getMongoTemplate().findAll(Group.class);
		return group;
	}

}
