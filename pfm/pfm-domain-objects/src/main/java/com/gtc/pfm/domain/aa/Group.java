/**
 * 
 */
package com.gtc.pfm.domain.aa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.gtc.pfm.domain.PfmMongoDomainObject;

/**
 * @author stanriku
 *
 */
@Document
public class Group extends PfmMongoDomainObject {

	public static final String nameField = "name"; 
    public static final String descriptionField = "desc";
    
    @Field(value=nameField)
    String name;
    
    @Field(value=descriptionField)
    String description;
    
    @Transient
    List<Role> roles;
    
	/**
	 * 
	 */
	public Group() {
		super();
	}

	/**
	 * @param name
	 * @param description
	 */
	public Group(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		if(this.roles == null){
			roles = new ArrayList<Role>();
		}
		roles.add(role);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Group other = (Group) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Group [name=" + name + ", description=" + description + "]";
	}
	
}
