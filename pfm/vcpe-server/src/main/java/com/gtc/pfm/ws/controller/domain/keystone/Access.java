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
public class Access implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174174190887392322L;
	private Token token;
	private ServiceCatalog serviceCatalog;
	
	/**
	 * default constructor
	 */
	public Access() {
		
	}

	/**
	 * @param token
	 * @param serviceCatalog
	 */
	public Access(Token token, ServiceCatalog serviceCatalog) {
		this.token = token;
		this.serviceCatalog = serviceCatalog;
	}

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(Token token) {
		this.token = token;
	}

	/**
	 * @return the serviceCatalog
	 */
	public ServiceCatalog getServiceCatalog() {
		return serviceCatalog;
	}

	/**
	 * @param serviceCatalog the serviceCatalog to set
	 */
	public void setServiceCatalog(ServiceCatalog serviceCatalog) {
		this.serviceCatalog = serviceCatalog;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serviceCatalog == null) ? 0 : serviceCatalog.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		Access other = (Access) obj;
		if (serviceCatalog == null) {
			if (other.serviceCatalog != null)
				return false;
		} else if (!serviceCatalog.equals(other.serviceCatalog))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
	
}
