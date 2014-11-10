/**
 * 
 */
package com.gtc.pfm.service.aa;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.aa.Group;
import com.gtc.pfm.persistence.repository.aa.GroupRepository;

/**
 * @author stanriku
 *
 */
@Service("GroupService")
public class GroupServiceImpl implements GroupService {

	private static final Log logger = LogFactory.getLog(GroupServiceImpl.class);

	@Autowired
	private GroupRepository groupRepository;

	/**
	 * 
	 */
	public GroupServiceImpl() {

	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.service.aa.GroupService#createGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public Group createGroup(String name, String description)
			throws ProviderException, ObjectExistsException,
			InvalidArgumentException {
		
		Group group = this.groupRepository.createGroup(name, description);
		return group;
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.service.aa.GroupService#createGroup(com.gtc.pfm.domain.aa.Group)
	 */
	@Override
	public Group createGroup(Group group) throws ProviderException,
			ObjectExistsException, InvalidArgumentException {
		
		group = this.groupRepository.createGroup(group);
		return group;
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.service.aa.GroupService#findGroupById(java.lang.String)
	 */
	@Override
	public Group findGroupById(String id) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		return this.groupRepository.findGroupById(id);
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.service.aa.GroupService#findGroupByName(java.lang.String)
	 */
	@Override
	public Group findGroupByName(String name) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		return this.groupRepository.findGroupByName(name);
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.service.aa.GroupService#deleteGroupById(java.lang.String)
	 */
	@Override
	public void deleteGroupById(String id) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		this.groupRepository.deleteGroupById(id);
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.service.aa.GroupService#deleteGroupByName(java.lang.String)
	 */
	@Override
	public void deleteGroupByName(String name) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		this.groupRepository.deleteGroupByName(name);
	}

	/* (non-Javadoc)
	 * @see com.gtc.pfm.service.aa.GroupService#findAllGroups(int, int)
	 */
	@Override
	public List<Group> findAllGroups(int firstRow, int lastRow)
			throws ProviderException {
		
		return this.groupRepository.findAllGroups(firstRow, lastRow);
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
