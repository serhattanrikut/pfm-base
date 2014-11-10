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
public class EndPointLink implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -917361405307642564L;
	private String link;
	
	/**
	 * default constructor
	 */
	public EndPointLink() {
		
	}

	/**
	 * @param link
	 */
	public EndPointLink(String link) {
		this.link = link;
	}
	
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
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
		EndPointLink other = (EndPointLink) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		return true;
	}

}
