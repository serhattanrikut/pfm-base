/**
 * 
 */
package com.gtc.pfm.ws.controller.domain.nova;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author stanriku
 *
 */
@Document
public class OSAction {

	@Field(value="os-start")
	private String os_start;
	
	@Field(value="os-stop")
	private String os_stopt;
	
	/**
	 * 
	 */
	public OSAction() {
		
	}

	/**
	 * @param os_start
	 * @param os_stopt
	 */
	public OSAction(String os_start, String os_stopt) {
		this.os_start = os_start;
		this.os_stopt = os_stopt;
	}

	/**
	 * @return the os_start
	 */
	public String getOs_start() {
		return os_start;
	}

	/**
	 * @param os_start the os_start to set
	 */
	public void setOs_start(String os_start) {
		this.os_start = os_start;
	}

	/**
	 * @return the os_stopt
	 */
	public String getOs_stopt() {
		return os_stopt;
	}

	/**
	 * @param os_stopt the os_stopt to set
	 */
	public void setOs_stopt(String os_stopt) {
		this.os_stopt = os_stopt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((os_start == null) ? 0 : os_start.hashCode());
		result = prime * result
				+ ((os_stopt == null) ? 0 : os_stopt.hashCode());
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
		OSAction other = (OSAction) obj;
		if (os_start == null) {
			if (other.os_start != null)
				return false;
		} else if (!os_start.equals(other.os_start))
			return false;
		if (os_stopt == null) {
			if (other.os_stopt != null)
				return false;
		} else if (!os_stopt.equals(other.os_stopt))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OSAction [os_start=" + os_start + ", os_stopt=" + os_stopt
				+ "]";
	}
	
}
