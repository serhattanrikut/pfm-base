/**
 * 
 */
package com.gtc.pfm.domain.aa;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

import com.gtc.pfm.domain.PfmMongoDomainObject;

/**
 * @author stanriku
 *
 */
@Document
public class Role extends PfmMongoDomainObject implements GrantedAuthority{

	private static final long serialVersionUID = -828419329520430205L;
	public static final String nameField = "name"; 
    public static final String descriptionField = "desc";
    
    @Field(value=nameField)
    String name;
    
    @Field(value=descriptionField)
    String description;
    
	/**
	 * 
	 */
	public Role() {
		
	}

	/**
	 * @param name
	 * @param description
	 */
	public Role(String name, String description) {
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
		Role other = (Role) obj;
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
		return "Role [name=" + name + ", description=" + description + "]";
	}

	@Override
	public String getAuthority() {
		return this.name;
	}
	
}
