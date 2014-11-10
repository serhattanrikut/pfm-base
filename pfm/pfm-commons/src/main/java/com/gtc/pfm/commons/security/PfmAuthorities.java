/**
 * 
 */
package com.gtc.pfm.commons.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author stanriku
 *
 */
public enum PfmAuthorities implements GrantedAuthority {
	ANANYMOUS, USER, ADMINISTRATOR;

	@Override
	public String getAuthority() {
		return name();
	}

}
