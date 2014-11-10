/**
 * 
 */
package com.gtc.repository.mongo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.ITempNaming;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * manages embedded mongodb instance
 * 
 * @author stanriku
 *
 */
public class EmbeddedMongoDBUtil {

	private static final class BasicExecutableNaming implements ITempNaming {
        @Override
        public String nameFor(String prefix, String postfix) {
            return Command.MongoD.commandName();
        }
    }
	
    private static final Log logger = LogFactory.getLog(EmbeddedMongoDBUtil.class);
    
    public static String LOCALHOST = "127.0.0.1";
    public static int DEFAULT_PORT = 27017;
    private String host; 
    private String dbName = "pfm-test";
    private int port = 27028;
    private boolean running = false;
    
    private MongodProcess mongoProcess;
    private Mongo mongo;
    private MongodExecutable mongoExecutable;
    
    private static EmbeddedMongoDBUtil instance = null;
   
    /**
     * private constructor
     */
    private EmbeddedMongoDBUtil() {
        
    }

    public static EmbeddedMongoDBUtil getInstance() {
       
        if(instance == null) {
            synchronized (EmbeddedMongoDBUtil.class) {
                if(instance == null)
                    instance = new EmbeddedMongoDBUtil();
            }
        }
        
        return instance;
    }

    /**
     * if silent is false and another db instance is running, throws an error
     * 
     * @param host
     * @param dbName
     * @param port
     * @throws Exception 
     */
    public synchronized void startDBInstance(String host,String dbName, int port, boolean silent) throws Exception {
        
        if(running && !silent) {
            throw new Exception("another db instance is running : "+this.getDbInstanceInfo());
        }
        
        
        this.setHost(host);
        this.setDbName(dbName);
        this.setPort(port);
        
        MongodStarter starter = MongodStarter.getDefaultInstance();
        
        IMongodConfig mongodConfig = new MongodConfigBuilder()
        .version(Version.Main.PRODUCTION)
        .net(new Net(port, Network.localhostIsIPv6()))
        .build();
        
        mongoExecutable = starter.prepare(mongodConfig);
         
        mongoProcess = mongoExecutable.start();
        
        mongo = new MongoClient(host, port);
        DB db = mongo.getDB(this.getDbName());
        
        running = true;
    }
    
    public void isDBIsntanceRunning(Mongo mongo) throws Exception {
        
        if(mongo != null) {
            
            while(mongoProcess.isProcessRunning()){
                
                logger.debug("db instance is still reacable..waiting for shutdown..");
                
                Thread.sleep(1000);
                
            }
            
        }
        
    }
    
    public synchronized void stopDBInstance() throws Exception{
        
        if(running){
            
            mongo.close();
            mongoProcess.stop();
            mongoExecutable.stop();
            
            
           while(mongoProcess.isProcessRunning() && mongoExecutable.getFile() != null && mongoExecutable.getFile().executable().exists()){
               logger.debug("db process is still running..waiting for shutdown..");
               System.out.println("db process is still running..waiting for shutdown..");
               Thread.sleep(1000);
           }
           
           running = false;
            
        }
    }
    
    
    /**
     * 
     * @param dbName
     * @param port
     * @throws Exception 
     */
    public void startDBInstanceOnLocalhost(String dbName, int port, boolean silent) throws Exception {
        this.startDBInstance(LOCALHOST, dbName, port, silent);
    }
    
    /**
     * 
     * @param dbName
     * @throws Exception 
     */
    public void startDBInstanceDefault(String dbName, boolean silent) throws Exception {
        this.startDBInstance(LOCALHOST, dbName, DEFAULT_PORT, silent);
    }


    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }


    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }


    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }


    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    /**
     * 
     * @return String
     */
    public String getDbInstanceInfo() {
        StringBuilder sb = new StringBuilder("mongodb info:[ db name:");
        sb.append(this.getDbName()).append(", host:").append(this.getPort()).append(", port:");
        sb.append(this.getPort()).append(", running:").append(this.isRunning());
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        
        EmbeddedMongoDBUtil.getInstance().startDBInstanceDefault("pfm_test", true);
        System.out.println("running..");
    }
    
}
