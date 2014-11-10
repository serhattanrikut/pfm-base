/**
 * 
 */
package com.gtc.pfm.persistence.repository;

import com.gtc.exceptions.ProviderException;

/**
 * This interface identifies common methods for all repositories
 * 
 * @author stanriku
 *
 */
public interface PfmRepositoryInft {

    public <T> void deleteAll(Class<T> entityClass) throws ProviderException;
    
}
