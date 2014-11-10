/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.test;

import com.gtc.repository.mongo.MongoRepositoryTestBase;
import com.gtc.repository.mongo.util.EmbeddedMongoDBUtil;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author stanriku
 *
 */
public abstract class MongoTestBase extends MongoRepositoryTestBase{

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void beforeCalss() throws Exception {
        EmbeddedMongoDBUtil.getInstance().startDBInstanceDefault("pfm_test", false);
    }

    /**
     * 
     * @throws Exception
     */
    @AfterClass
    public static void afterClass() throws Exception {
        EmbeddedMongoDBUtil.getInstance().stopDBInstance();
    }

}
