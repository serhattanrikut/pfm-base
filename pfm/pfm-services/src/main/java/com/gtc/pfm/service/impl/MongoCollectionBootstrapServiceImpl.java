/**
 * 
 */
package com.gtc.pfm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.stereotype.Service;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.commons.security.PfmAuthorities;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.PackageType;
import com.gtc.pfm.domain.PackageTypeEnum;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.domain.User;
import com.gtc.pfm.domain.Venue;
import com.gtc.pfm.domain.VenueType;
import com.gtc.pfm.domain.VenueTypeEnum;
import com.gtc.pfm.domain.aa.Group;
import com.gtc.pfm.domain.aa.Role;
import com.gtc.pfm.persistence.repository.mongo.PfmMongoRepositoryBase;
import com.gtc.pfm.service.MongoCollectionBootstrapService;
import com.gtc.pfm.service.aa.GroupService;
import com.gtc.pfm.service.aa.RoleService;
import com.gtc.pfm.service.package_.PackageService;
import com.gtc.pfm.service.user.UserService;
import com.gtc.pfm.service.venue.VenueService;


/**
 * 
 * initializes mongodb collections
 * 
 * @author stanriku
 *
 */
@Service(value="MongoCollectionBootstrapService")
public class MongoCollectionBootstrapServiceImpl extends PfmMongoRepositoryBase implements MongoCollectionBootstrapService{

    private static Log logger = LogFactory.getLog(MongoCollectionBootstrapServiceImpl.class);
    
    public static  List<Class> entitiyList;
    
    public static Venue ANKARA ;
    public static Venue ISTANBUL;
    public static Venue IZMIR;
    public static Venue ANTALYA;
    public static Venue TAKSIM_SQUARE;
    public static Venue KADIKOY;
    public static Venue KADIKOY_MODA;
    public static Venue MODA_ISKELESI;
    public static Venue SARACOGLU;
    public static Venue KADIKOY_LISESI;
    public static Venue USKUDAR;
    public static Venue GOZTEPE;
    public static Venue ATASEHIR;
    public static Venue CADDEBOSTAN;
    public static Venue BAKIRKOY;
    public static Venue YESILKOY;
    public static Venue ANTWERP_HOME;
    
    public static User serhat;
    public static User vedat;
    
    static {
        
        entitiyList = new ArrayList<Class>();
        entitiyList.add(User.class);
        entitiyList.add(Venue.class);
        entitiyList.add(VenueType.class);
        entitiyList.add(Package.class);
        entitiyList.add(PackageType.class);
        
        ANKARA = new Venue("Ankara", "center ankara", VenueTypeEnum.CITY_CENTER.getType(), "Ankara", null, 39.92077,32.85411, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        ANTALYA = new Venue("Antalya", "center antalya", VenueTypeEnum.CITY_CENTER.getType(), "Ankara", null, 36.88414,30.70563, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        IZMIR = new Venue("Izmir", "center izmir", VenueTypeEnum.CITY_CENTER.getType(), "Ankara", null, 38.41885,27.12872, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        ISTANBUL = new Venue("Istanbul", "center istanbul", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 41.00527,28.97696, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        TAKSIM_SQUARE = new Venue("Taksim Square", "Gumussuyu Mh., Beyoglu, Istanbul, Istanbul, Turkey, 34050", VenueTypeEnum.SQUARE.getType(), "Istanbul", null, 41.036565,28.986909, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        KADIKOY = new Venue("Kadikoy", "center kadikoy", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.980141,29.08227, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        KADIKOY_MODA = new Venue("Moda", "center kadikoy moda", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.985205,29.034049, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        ATASEHIR = new Venue("Atasehir", "center atasehir", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.992613,29.127244, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        GOZTEPE = new Venue("Goztepe", "center goztepe", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.992356,29.073494, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        USKUDAR = new Venue("Uskudar", "center uskudar", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 41.025858,29.015682, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        CADDEBOSTAN = new Venue("Cadde Bostan", "center cadde bostan", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.966106,29.069902, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        BAKIRKOY = new Venue("Bakirkoy", "center bakirkoy", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.968155,28.8228, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        YESILKOY = new Venue("Yesilkoy", "center yesilkoy", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.962636,28.824574, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        SARACOGLU = new Venue("Sukru Srarcoglu Stadyumu", "center kadikoy", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.98757607,29.03729439, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        KADIKOY_LISESI = new Venue("Kadikoy Lisesi", "center kadikoy", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.98323503,29.02171612, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        MODA_ISKELESI = new Venue("Moda Iskelesi", "center kadikoy", VenueTypeEnum.CITY_CENTER.getType(), "Istanbul", null, 40.97980087,29.02467728, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
        
        ANTWERP_HOME = new Venue("Anterwp Home", "center antwerp", VenueTypeEnum.CITY_CENTER.getType(), "Antwerp", null,  51.26683 ,4.3792323, 
                Calendar.getInstance().getTime(),Calendar.getInstance().getTime());     
        
    }
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VenueService venueService;
    
    @Autowired
    private PackageService packageService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private GroupService groupService;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    /**
     * default constructor
     */
    public MongoCollectionBootstrapServiceImpl() {
        logger.info("-- instantiating MongoCollectionBootstrapServiceImpl");
    }

    @Override
    @PostConstruct
    public void init() throws ProviderException {
        logger.info("initializing mongo..");
        initCollections();
        initIndexes();
        populateUsers();
        populateVenues();
        populatePackages();
        logger.info("initialized mongo..");
    }
    
    @Override
    @PreDestroy
    public void destroy() throws ProviderException {
        logger.info("destroying mongo..");
        super.dropCollections(entitiyList);
        logger.info("destroyed mongo..");
    }
    
    @Override
    public void initCollections() throws ProviderException {
       
        logger.info("initializing mongo collections..");
       
        super.initCollections(entitiyList);
        
        logger.info("done!...initialized mongo collections..");
        
    }
    
    @Override
    public void initIndexes() throws ProviderException {
        
        logger.info("initializing mongo collections..");
        
        GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
        
        getMongoTemplate().getCollection("package").createIndex(gi.getIndexKeys());
        
        logger.info("initialized mongo collections..");
    }
    
    /**
     * insert initial users
     */
    public void populateUsers() {
        
        try {
            
            List<User> allUsers = userService.findAllUsers(0, 20);
            if(allUsers != null && allUsers.size() > 0)
                return;
            
            Role userRole = new Role(PfmAuthorities.USER.getAuthority(),"registered user role");
            try {
            	userRole = roleService.findRoleByName(userRole.getName());
			} catch (ObjectNotFoundException e) {
				roleService.createRole(userRole);
			}
           
            Group userGroup = new Group(PfmAuthorities.USER.getAuthority()+"_regular","registered user group");
            userGroup.addRole(userRole);
            
            try {
            	userGroup = groupService.findGroupByName(userGroup.getName());
			} catch (ObjectNotFoundException e) {
				groupService.createGroup(userGroup);
			}
            
            userGroup = groupService.findGroupByName(userGroup.getName());
            
            serhat = new User("serhattanrikut@gmail.com", "serhattanrikut", "serhattanrikut",
                    "serhat", "tanrikut", "serhat", "serhat", 5, 5, "M", KADIKOY.getName(), KADIKOY.address,  "+905306035113", "", 5, userGroup, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            serhat = createUserIfNotExists(serhat);
            
            vedat = new User("vedkoo@gmail.com", "vedatozkan", "vedatozkan", "vedat", "ozkan",
                    "vedat", "vedat", 5, 5, "M", KADIKOY.getName(), KADIKOY.address,  "+905306035114", "", 5,userGroup);
            vedat.setCreated(Calendar.getInstance().getTime());
            vedat.setUpdated(Calendar.getInstance().getTime());
            vedat = createUserIfNotExists(vedat);
          
            
        } catch (Exception e) {
            logger.error("an error occured while populating default users",e);
        }
        
       
    }
    
    public User createUserIfNotExists(User user) throws ProviderException, ObjectNotFoundException, InvalidArgumentException, ObjectExistsException {
    	try {
    		user = userService.findUserByName(user.getUsername());
		} catch (ObjectNotFoundException e) {
			 userService.creaetUser(user);
			 logger.info("user created:"+user);
		}
        
    	user = userService.findUserByName(user.getUsername());
    	return user;
    }

    public List<String> provideRoles() {
        List<String> roles = new ArrayList<String>();
        roles.add("user");
        return roles;
    }
    
    public void populateVenues() {
        try {
            
            List<Venue> venues = this.venueService.findAllVenues(new RowFilter());
            if(venues != null && venues.size() > 0)
                return;
            
            this.venueService.createVenue(ISTANBUL);
            this.venueService.createVenue(ANKARA);
            this.venueService.createVenue(IZMIR);
            this.venueService.createVenue(ANTALYA);
            this.venueService.createVenue(TAKSIM_SQUARE);
            this.venueService.createVenue(KADIKOY);
            this.venueService.createVenue(KADIKOY_MODA);
            this.venueService.createVenue(USKUDAR);
            this.venueService.createVenue(GOZTEPE);
            this.venueService.createVenue(ATASEHIR);
            this.venueService.createVenue(CADDEBOSTAN);
            this.venueService.createVenue(BAKIRKOY);
            this.venueService.createVenue(YESILKOY);
            this.venueService.createVenue(ANTWERP_HOME);
            
            
        } catch (Exception e) {
            logger.error("an error occured while populating default venues",e);
        }
    }
    
    public void populatePackages() {
        try {
            
        	
            List<Package> packages = this.packageService.findAllPackages(new RowFilter());
            if(packages != null && packages.size() > 0)
                return;
            Package package_1 = new Package("free vodka", new Date(System.currentTimeMillis()+10000000), serhat.exposeUserRef(), vedat.exposeUserRef(), KADIKOY_MODA.exposeVenueReference(), PackageTypeEnum.PRIVATE.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_2 = new Package("free girl", new Date(System.currentTimeMillis()+10000000), serhat.exposeUserRef(), vedat.exposeUserRef(), KADIKOY.exposeVenueReference(), PackageTypeEnum.PUBLIC.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_3 = new Package("free beer", new Date(System.currentTimeMillis()+10000000), serhat.exposeUserRef(), vedat.exposeUserRef(), USKUDAR.exposeVenueReference(), PackageTypeEnum.PROMOTION.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_4 = new Package("free tequila", new Date(System.currentTimeMillis()+10000000), serhat.exposeUserRef(), vedat.exposeUserRef(), GOZTEPE.exposeVenueReference(), PackageTypeEnum.PUBLIC.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_5 = new Package("free burger", new Date(System.currentTimeMillis()+10000000), vedat.exposeUserRef(), serhat.exposeUserRef(), ISTANBUL.exposeVenueReference(), PackageTypeEnum.PROMOTION.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_6 = new Package("free mcdonald", new Date(System.currentTimeMillis()+10000000), vedat.exposeUserRef(), serhat.exposeUserRef(), TAKSIM_SQUARE.exposeVenueReference(), PackageTypeEnum.PROMOTION.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_7 = new Package("free kebap", new Date(System.currentTimeMillis()+10000000), vedat.exposeUserRef(), serhat.exposeUserRef(), BAKIRKOY.exposeVenueReference(), PackageTypeEnum.PROMOTION.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_8 = new Package("free water", new Date(System.currentTimeMillis()+10000000), vedat.exposeUserRef(), serhat.exposeUserRef(), YESILKOY.exposeVenueReference(), PackageTypeEnum.PRIVATE.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_9 = new Package("Russian Girl", new Date(System.currentTimeMillis()+10000000), vedat.exposeUserRef(), serhat.exposeUserRef(), CADDEBOSTAN.exposeVenueReference(), PackageTypeEnum.PRIVATE.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_10 = new Package("Dinner", new Date(System.currentTimeMillis()+10000000), vedat.exposeUserRef(), serhat.exposeUserRef(), ATASEHIR.exposeVenueReference(), PackageTypeEnum.PUBLIC.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_11 = new Package("Lunch", new Date(System.currentTimeMillis()+10000000), serhat.exposeUserRef(), vedat.exposeUserRef(), SARACOGLU.exposeVenueReference(), PackageTypeEnum.PUBLIC.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_12 = new Package("Book", new Date(System.currentTimeMillis()+10000000), serhat.exposeUserRef(), vedat.exposeUserRef(), KADIKOY_LISESI.exposeVenueReference(), PackageTypeEnum.PUBLIC.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_13 = new Package("Dinner", new Date(System.currentTimeMillis()+10000000), serhat.exposeUserRef(), vedat.exposeUserRef(), MODA_ISKELESI.exposeVenueReference(), PackageTypeEnum.PUBLIC.getType(), false, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_14 = new Package("Dinner", new Date(System.currentTimeMillis()+10000000), vedat.exposeUserRef(), serhat.exposeUserRef(), MODA_ISKELESI.exposeVenueReference(), PackageTypeEnum.PRIVATE.getType(), true, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            Package package_15 = new Package("Dinner", new Date(System.currentTimeMillis()+10000000), serhat.exposeUserRef(), serhat.exposeUserRef(), ANTWERP_HOME.exposeVenueReference(), PackageTypeEnum.PRIVATE.getType(), true, 
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
            
            this.packageService.createPackage(package_1);
            this.packageService.createPackage(package_2);
            this.packageService.createPackage(package_3);
            this.packageService.createPackage(package_4);
            this.packageService.createPackage(package_5);
            this.packageService.createPackage(package_6);
            this.packageService.createPackage(package_7);
            this.packageService.createPackage(package_8);
            this.packageService.createPackage(package_9);
            this.packageService.createPackage(package_10);
            this.packageService.createPackage(package_11);
            this.packageService.createPackage(package_12);
            this.packageService.createPackage(package_13);
            this.packageService.createPackage(package_14);
            this.packageService.createPackage(package_15);
            
        } catch (Exception e) {
            logger.error("an error occured while populating default upackagessers",e);
        }
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

    /**
     * @return the venueService
     */
    public VenueService getVenueService() {
        return venueService;
    }

    /**
     * @param venueService the venueService to set
     */
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    /**
     * @return the packageService
     */
    public PackageService getPackageService() {
        return packageService;
    }

    /**
     * @param packageService the packageService to set
     */
    public void setPackageService(PackageService packageService) {
        this.packageService = packageService;
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

	/**
	 * @return the roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * @return the groupService
	 */
	public GroupService getGroupService() {
		return groupService;
	}

	/**
	 * @param groupService the groupService to set
	 */
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

}
