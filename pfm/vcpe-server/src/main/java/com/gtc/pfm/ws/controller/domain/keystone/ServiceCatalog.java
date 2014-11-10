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
public class ServiceCatalog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7774046659255657739L;
	private String name;
	private String type;
	private EndPointList endpoints;
	private EndPointLinkList endpoints_links;

	/**
	 * 
	 */
	public ServiceCatalog() {
		
	}

	/**
	 * @param name
	 * @param type
	 * @param endpoints
	 * @param endpoints_links
	 */
	public ServiceCatalog(String name, String type, EndPointList endpoints,
			EndPointLinkList endpoints_links) {
		this.name = name;
		this.type = type;
		this.endpoints = endpoints;
		this.endpoints_links = endpoints_links;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the endpoints
	 */
	public EndPointList getEndpoints() {
		return endpoints;
	}

	/**
	 * @param endpoints the endpoints to set
	 */
	public void setEndpoints(EndPointList endpoints) {
		this.endpoints = endpoints;
	}

	/**
	 * @return the endpoints_links
	 */
	public EndPointLinkList getEndpoints_links() {
		return endpoints_links;
	}

	/**
	 * @param endpoints_links the endpoints_links to set
	 */
	public void setEndpoints_links(EndPointLinkList endpoints_links) {
		this.endpoints_links = endpoints_links;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ServiceCatalog other = (ServiceCatalog) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
