/**
 * 
 */
package com.gtc.pfm.service.aa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.aa.Role;
import com.gtc.pfm.persistence.repository.aa.RoleRepository;

/**
 * @author stanriku
 *
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role createRole(String name, String description)
			throws ProviderException, ObjectExistsException,
			InvalidArgumentException {
		
		Role role = this.roleRepository.createRole(name, description);
		return role;
	}

	@Override
	public String createRole(Role role) throws ProviderException,
			ObjectExistsException, InvalidArgumentException {
		
		String id = this.roleRepository.createRole(role);
		return id;
	}

	@Override
	public Role findRoleById(String id) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		return this.roleRepository.findRoleById(id);
	}

	@Override
	public Role findRoleByName(String name) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		return this.roleRepository.findRoleByName(name);
	}

	@Override
	public void deleteRoleById(String id) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		this.roleRepository.deleteRoleById(id);
	}

	@Override
	public void deleteRoleByName(String name) throws ProviderException,
			ObjectNotFoundException, InvalidArgumentException {
		
		this.roleRepository.deleteRoleByName(name);
	}

	@Override
	public List<Role> findAllRoles(int firstRow, int lastRow)
			throws ProviderException {
		
		return this.roleRepository.findAllRoles(firstRow, lastRow);
	}
	


}
