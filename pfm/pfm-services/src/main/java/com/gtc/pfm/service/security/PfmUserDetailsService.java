/**
 * 
 */
package com.gtc.pfm.service.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.logging.util.LoggingUtil;
import com.gtc.pfm.domain.User;
import com.gtc.pfm.service.user.UserService;

/**
 * 
 * implementation of spring security {@link UserDetailsService}.
 * The service uses {@link UserService} to do user related operations
 * 
 * @author stanriku
 *
 */
@Service
public class PfmUserDetailsService implements UserDetailsService {

    private static Log logger = LogFactory.getLog(PfmUserDetailsService.class);
    
    @Autowired
    private UserService userService;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        LoggingUtil.trace(logger, "loading user by name:"+username);
        User user = null;
        
		try {
			user = userService.findUserByName(username);
		} catch (ProviderException e) {
           throw new UsernameNotFoundException("an error occured while finding user by name:"+username,e);
        } catch (ObjectNotFoundException e) {
            throw new UsernameNotFoundException("user not found by name:"+username,e);
        }  catch (InvalidArgumentException e) {
        	throw new UsernameNotFoundException("user does not have required setup.. :"+username,e);
        }
       
        LoggingUtil.trace(logger, "loaded user with authoraties:"+user);
        return user;
    }

    /**
     * instantiates {@link SimpleGrantedAuthority} objects regarding given role names
     * 
     * @param user
     * @param roles
     * @return Set<GrantedAuthority>
     */
    public Set<GrantedAuthority> initializeGarantedAuthorities(String... roles){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if(roles != null) {
            for (String role : roles) {
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                authorities.add(grantedAuthority);
            }
        }
        return authorities;
    }
    
    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }    
}
