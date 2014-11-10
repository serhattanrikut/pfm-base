/**
 * Copyright 2009 Joe LaPenna
 */

package com.gtc.pfm.domain;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gtc.pfm.domain.aa.Group;


/**
 * User class holding all required properties including roles.
 * 
 * @author stanriku
 */
@Document
public class User extends PfmMongoDomainObject implements UserDetails{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3272752179805265701L;

	public static final String usernameField = "un";
    
    private String email;
    private String facebook;
    private String twitter;
    private String firstname;
    private String lastname;
    
    @Field(value=usernameField)
    private String username;
    
    private String password;
    private int followerCount;
    private int friendCount;
    private String gender;
    private String hometown;
    private String address;
    private String phone;
    private String photo;
    private int tipCount;
    
    @Transient
    private Group group;
    private String groupId;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    /**
     * default constructor
     */
    public User() {
        
    }
    
    /**
     * @param email
     * @param facebook
     * @param twitter
     * @param firstname
     * @param lastname
     * @param username
     * @param password
     * @param followerCount
     * @param friendCount
     * @param gender
     * @param hometown
     * @param address
     * @param phone
     * @param photo
     * @param tipCount
     */
    public User(String email, String facebook, String twitter, String firstname, String lastname,
            String username, String password, int followerCount, int friendCount, String gender,
            String hometown, String address, String phone, String photo, int tipCount, Group group) {
        super();
        this.email = email;
        this.facebook = facebook;
        this.twitter = twitter;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.followerCount = followerCount;
        this.friendCount = friendCount;
        this.gender = gender;
        this.hometown = hometown;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.tipCount = tipCount;
        this.group = group;
        this.groupId = group.getId();
    }
    
    public User(String email, String facebook, String twitter, String firstname, String lastname,
            String username, String password, int followerCount, int friendCount, String gender,
            String hometown,String address, String phone, String photo, int tipCount, Group group, Date created, Date updated) {
        super();
        this.email = email;
        this.facebook = facebook;
        this.twitter = twitter;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.followerCount = followerCount;
        this.friendCount = friendCount;
        this.gender = gender;
        this.hometown = hometown;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.tipCount = tipCount;
        this.created = created;
        this.updated = updated;
        this.group = group;
        this.groupId = group.getId();
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return the twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter the twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the followerCount
     */
    public int getFollowerCount() {
        return followerCount;
    }

    /**
     * @param followerCount the followerCount to set
     */
    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    /**
     * @return the friendCount
     */
    public int getFriendCount() {
        return friendCount;
    }

    /**
     * @param friendCount the friendCount to set
     */
    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * @param hometown the hometown to set
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the tipCount
     */
    public int getTipCount() {
        return tipCount;
    }

    /**
     * @param tipCount the tipCount to set
     */
    public void setTipCount(int tipCount) {
        this.tipCount = tipCount;
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
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
     * 
     * creates {@link UserReference} instance generated from the current {@link User} object
     * 
     * @return {@link UserReference}
     */
    public UserReference exposeUserRef() {
        
        UserReference userRef = new UserReference(this.getId(), this.getUsername());
        return userRef;
        
    }


	/**
	 * @return the accountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.group.getRoles();
	}


	/**
	 * @param accountNonExpired the accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @param accountNonLocked the accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param credentialsNonExpired the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
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
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
