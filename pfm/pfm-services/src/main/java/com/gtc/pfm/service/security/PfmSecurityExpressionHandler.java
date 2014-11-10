/**
 * 
 */
package com.gtc.pfm.service.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * @author stanriku
 *
 */
public class PfmSecurityExpressionHandler implements PermissionEvaluator {

    /**
     * 
     */
    public PfmSecurityExpressionHandler() {
        
    }

    @Override
    public boolean hasPermission(Authentication arg0, Object arg1, Object arg2) {
        
        return true;
    }

    @Override
    public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
        
        return true;
    }

}
