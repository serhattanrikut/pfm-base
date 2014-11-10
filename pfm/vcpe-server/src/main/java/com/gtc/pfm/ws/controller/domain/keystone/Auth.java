/**
 * 
 */
package com.gtc.pfm.ws.controller.domain.keystone;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author stanriku
 *
 */
//@Document
public class Auth implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2173996804544648050L;
	private String tenantName;
	private PasswordCredentials passwordCredentials;

	/**
	 * 
	 */
	public Auth() {
		
	}

	/**
	 * @param tenantName
	 * @param passwordCredentials
	 */
	public Auth(String tenantName, PasswordCredentials passwordCredentials) {
		this.tenantName = tenantName;
		this.passwordCredentials = passwordCredentials;
	}

	/**
	 * @return the tenantName
	 */
	public String getTenantName() {
		return tenantName;
	}

	/**
	 * @param tenantName the tenantName to set
	 */
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	/**
	 * @return the passwordCredentials
	 */
	public PasswordCredentials getPasswordCredentials() {
		return passwordCredentials;
	}

	/**
	 * @param passwordCredentials the passwordCredentials to set
	 */
	public void setPasswordCredentials(PasswordCredentials passwordCredentials) {
		this.passwordCredentials = passwordCredentials;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((passwordCredentials == null) ? 0 : passwordCredentials
						.hashCode());
		result = prime * result
				+ ((tenantName == null) ? 0 : tenantName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auth other = (Auth) obj;
		if (passwordCredentials == null) {
			if (other.passwordCredentials != null)
				return false;
		} else if (!passwordCredentials.equals(other.passwordCredentials))
			return false;
		if (tenantName == null) {
			if (other.tenantName != null)
				return false;
		} else if (!tenantName.equals(other.tenantName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Auth [tenantName=" + tenantName + ", passwordCredentials="
				+ passwordCredentials + "]";
	}
}
