package com.gtc.pfm.ws.controller.domain.keystone;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author stanriku
 *
 */
@Document
public class EndPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7454786530065834775L;
	private String id;
	private String adminURL;
	private String internalURL;
	private String publicURL;
	private String region;
	
	/**
	 * default constructor 
	 */
	public EndPoint() {
		
	}

	/**
	 * @param id
	 * @param adminURL
	 * @param internalURL
	 * @param publicURL
	 * @param region
	 */
	public EndPoint(String id, String adminURL, String internalURL,
			String publicURL, String region) {
		this.id = id;
		this.adminURL = adminURL;
		this.internalURL = internalURL;
		this.publicURL = publicURL;
		this.region = region;
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
	 * @return the adminURL
	 */
	public String getAdminURL() {
		return adminURL;
	}

	/**
	 * @param adminURL the adminURL to set
	 */
	public void setAdminURL(String adminURL) {
		this.adminURL = adminURL;
	}

	/**
	 * @return the internalURL
	 */
	public String getInternalURL() {
		return internalURL;
	}

	/**
	 * @param internalURL the internalURL to set
	 */
	public void setInternalURL(String internalURL) {
		this.internalURL = internalURL;
	}

	/**
	 * @return the publicURL
	 */
	public String getPublicURL() {
		return publicURL;
	}

	/**
	 * @param publicURL the publicURL to set
	 */
	public void setPublicURL(String publicURL) {
		this.publicURL = publicURL;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((adminURL == null) ? 0 : adminURL.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((internalURL == null) ? 0 : internalURL.hashCode());
		result = prime * result
				+ ((publicURL == null) ? 0 : publicURL.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		EndPoint other = (EndPoint) obj;
		if (adminURL == null) {
			if (other.adminURL != null)
				return false;
		} else if (!adminURL.equals(other.adminURL))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (internalURL == null) {
			if (other.internalURL != null)
				return false;
		} else if (!internalURL.equals(other.internalURL))
			return false;
		if (publicURL == null) {
			if (other.publicURL != null)
				return false;
		} else if (!publicURL.equals(other.publicURL))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}
	
}
