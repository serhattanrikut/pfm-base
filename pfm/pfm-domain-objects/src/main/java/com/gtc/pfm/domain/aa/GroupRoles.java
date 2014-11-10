/**
 * 
 */
package com.gtc.pfm.domain.aa;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.gtc.pfm.domain.PfmMongoDomainObject;

/**
 * Represents joins between {@link Group} and {@link Role} object. A group may have different roles
 * do define complete set of authorities 
 * 
 * @author stanriku
 *
 */
@Document
public class GroupRoles extends PfmMongoDomainObject{

	public static final String groupIdField = "grpId";
	public static final String roleIdField = "rlId";
	
	@Field(value=groupIdField)
	private String groupId;
	
	@Field(value=roleIdField)
	private String roleId;
	
	/**
	 * default contractor
	 */
	public GroupRoles() {
		
	}
	
	/**
	 * @param groupId
	 * @param roleId
	 */
	public GroupRoles(String groupId, String roleId) {
		this.groupId = groupId;
		this.roleId = roleId;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupRoles other = (GroupRoles) obj;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupRoles [groupId=" + groupId + ", roleId=" + roleId + "]";
	}

}
