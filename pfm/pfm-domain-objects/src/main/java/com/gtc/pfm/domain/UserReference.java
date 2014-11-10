/**
 * 
 */
package com.gtc.pfm.domain;

import org.springframework.data.mongodb.core.mapping.Field;


/**
 * A {@link UserReference} class represents an embedable {@link User} object with limited properties 
 * that can be used in other main domain objects like {@link Package}
 * 
 * @author stanriku
 *
 */
public class UserReference implements PfmDomainObjectInterface{

    public static final String usernameField = User.usernameField;
    
    /**
     * id property as reference to actual {@link User}
     */
    public String id;
    
    @Field(value=usernameField)
    public String username;
    
    /**
     *  default constructor
     */
    public UserReference() {
       
    }
    

    /**
     * @param id
     * @param username
     */
    public UserReference(String id, String username) {
        super();
        this.id = id;
        this.username = username;
    }



    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setName(String username) {
        this.username = username;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserReference [id=");
        builder.append(id);
        builder.append(", username=");
        builder.append(username);
        builder.append("]");
        return builder.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserReference)) {
            return false;
        }
        UserReference other = (UserReference) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
