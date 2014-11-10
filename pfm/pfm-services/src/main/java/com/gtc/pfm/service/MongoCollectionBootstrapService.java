/**
 * 
 */
package com.gtc.pfm.service;

import com.gtc.exceptions.ProviderException;

/**
 * @author stanriku
 *
 */
public interface MongoCollectionBootstrapService {

    public void init() throws ProviderException;
    
    public void initCollections() throws ProviderException;
    
    public void initIndexes() throws ProviderException;
    
    public void destroy() throws ProviderException;
    
}
