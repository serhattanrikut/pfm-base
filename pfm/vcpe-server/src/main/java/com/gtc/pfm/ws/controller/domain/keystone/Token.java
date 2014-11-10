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
@Document
public class Token implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 697645559359800416L;
	private String id;
	private String issued_at;
	private String expires;
	private Tenant tenant;
	
	/**
	 * default constructor
	 */
	public Token() {
		
	}

	/**
	 * @param id
	 * @param issued_at
	 * @param expires
	 * @param tenant
	 */
	public Token(String id, String issued_at, String expires, Tenant tenant) {
		this.id = id;
		this.issued_at = issued_at;
		this.expires = expires;
		this.tenant = tenant;
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
	 * @return the issued_at
	 */
	public String getIssued_at() {
		return issued_at;
	}

	/**
	 * @param issued_at the issued_at to set
	 */
	public void setIssued_at(String issued_at) {
		this.issued_at = issued_at;
	}

	/**
	 * @return the expires
	 */
	public String getExpires() {
		return expires;
	}

	/**
	 * @param expires the expires to set
	 */
	public void setExpires(String expires) {
		this.expires = expires;
	}

	/**
	 * @return the tenant
	 */
	public Tenant getTenant() {
		return tenant;
	}

	/**
	 * @param tenant the tenant to set
	 */
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expires == null) ? 0 : expires.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((issued_at == null) ? 0 : issued_at.hashCode());
		result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());
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
		Token other = (Token) obj;
		if (expires == null) {
			if (other.expires != null)
				return false;
		} else if (!expires.equals(other.expires))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (issued_at == null) {
			if (other.issued_at != null)
				return false;
		} else if (!issued_at.equals(other.issued_at))
			return false;
		if (tenant == null) {
			if (other.tenant != null)
				return false;
		} else if (!tenant.equals(other.tenant))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Token [id=" + id + ", issued_at=" + issued_at + ", expires="
				+ expires + ", tenant=" + tenant + "]";
	}
}
