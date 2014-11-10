/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo;

import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.persistence.repository.PfmRepositoryInft;
import com.mongodb.DBCollection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author stanriku
 *
 */
@Service(value="PfmMongoRepositoryBase")
public abstract class PfmMongoRepositoryBase implements PfmRepositoryInft{

    private static final Log logger = LogFactory.getLog(PfmMongoRepositoryBase.class);
    
    @Autowired
    public MongoTemplate mongoTemplate;
    
    /**
     * default constructor
     */
    public PfmMongoRepositoryBase() {
        
    }

    /**
     * @return the mongoTemplate
     */
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * @param mongoTemplate the mongoTemplate to set
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    
    public <T> DBCollection  createObjectCollection(Class<T> entityClass) {
        
        if(!this.mongoTemplate.collectionExists(entityClass)){
            
            logger.info(entityClass + " collection deos not exists in mongo db.creating a new collection.");
            
            DBCollection collection =  this.mongoTemplate.createCollection(entityClass);
            
            logger.info(entityClass + " collection has been created.");
            
            return collection;
        }
        
        return null;
    }
    
    public <T> void dropObjectCollection(Class<T> entityClass) {
        
        if(!this.mongoTemplate.collectionExists(entityClass)){
            this.mongoTemplate.dropCollection(entityClass);
        }
    }

    public  void initCollections(List<Class> entityClassList) {
        
        if(entityClassList != null) {
            for (Class clazz : entityClassList) {
                createObjectCollection(clazz);
            }
        }
        
    }
    
    public  void dropCollections(List<Class> entityClassList) {
        
        if(entityClassList != null) {
            for (Class clazz : entityClassList) {
                dropObjectCollection(clazz);
            }
        }
        
    }

    public <T> void deleteAll(Class<T> entityClass) throws ProviderException {
        
        this.dropObjectCollection(entityClass);
        this.createObjectCollection(entityClass);
        
    }
}
