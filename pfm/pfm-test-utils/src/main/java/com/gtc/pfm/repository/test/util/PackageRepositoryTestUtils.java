/**
 * 
 */
package com.gtc.pfm.repository.test.util;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.UserReference;
import com.gtc.pfm.domain.VenueReference;

import org.bson.types.ObjectId;

import java.util.Date;


/**
 * 
 * provides mock implementations and util methods in order to mock {@link com.gtc.pfm.domain.Package}  
 * 
 * @author stanriku
 *
 */
public class PackageRepositoryTestUtils {

    public static UserReference owner = null;
    public static UserReference recipient = null;
    public static UserReference owner_1 = null;
    public static UserReference recipient_1 = null;
    public static VenueReference venueRef_Antalya = null;
    public static VenueReference venueRef_Ankara = null;
    public static VenueReference venueRef_Istanbul = null;
    public static VenueReference venueRef_Izmir = null;
    public static VenueReference venueRef_Atasehir = null;
    public static VenueReference venueRef_Bakirkoy = null;
    public static VenueReference venueRef_Caddebostan = null;
    public static VenueReference venueRef_Goztepe = null;
    public static VenueReference venueRef_Kadikoy = null;
    public static VenueReference venueRef_KadikoyModa = null;
    public static VenueReference venueRef_TaksimSquare = null;
    public static VenueReference venueRef_Uskudar = null;
    public static VenueReference venueRef_Yesilkoy = null;
    
    protected static UserRepositoryTestUtil userRepositoryTestUtil = new UserRepositoryTestUtil();
    protected static VenueRepositoryTestUtils venueRepositoryTestUtils = new VenueRepositoryTestUtils();
    /**
     * 
     */
    public PackageRepositoryTestUtils() {
        owner = userRepositoryTestUtil.createUserReferenceMock(ObjectId.get().toString(), "test_owner");
        recipient = userRepositoryTestUtil.createUserReferenceMock(ObjectId.get().toString(),"test_recipient");
        owner_1 = userRepositoryTestUtil.createUserReferenceMock(ObjectId.get().toString(), "test_owner_1");
        recipient_1 = userRepositoryTestUtil.createUserReferenceMock(ObjectId.get().toString(),"test_recipient_1");
        venueRef_Ankara = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.ANKARA );
        venueRef_Antalya = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.ANTALYA );
        venueRef_Istanbul = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.ISTANBUL );
        venueRef_Izmir = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.IZMIR );
        venueRef_Atasehir = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.ATASEHIR );
        venueRef_Bakirkoy = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.BAKIRKOY );
        venueRef_Caddebostan = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.CADDEBOSTAN );
        venueRef_Goztepe = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.GOZTEPE );
        venueRef_Kadikoy = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.KADIKOY );
        venueRef_KadikoyModa = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.KADIKOY_MODA );
        venueRef_TaksimSquare = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.TAKSIM_SQUARE );
        venueRef_Uskudar = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.USKUDAR );
        venueRef_Yesilkoy = venueRepositoryTestUtils.createVenueReference(ObjectId.get().toString(), VenueRepositoryTestUtils.YESILKOY );
    }

    /**
     * creates mock in-memory {@link Package}
     * 
     * @param name
     * @param expired
     * @param owner
     * @param recipient
     * @param venueRef
     * @param type
     * @param open
     * @return {@link Package}
     */
    public Package createPackageMock(String name, Date expired, UserReference owner, UserReference recipient, VenueReference venueRef,
            int type, boolean open ) {
        
        Package package_ = new Package(name, expired, owner, recipient, venueRef, type, open);
        
        return package_;
    }
    
    /**
     * creates mock in-memory {@link Package}
     * 
     * @param name
     * @param expired
     * @param owner
     * @param recipient
     * @param venueRef
     * @param type
     * @param open
     * @param created
     * @param updated
     * @return {@link Package}
     */
    public Package createPackageMock(String name, Date expired, UserReference owner, UserReference recipient, VenueReference venueRef,
            int type, boolean open, Date created, Date updated ) {
        
        Package package_ = new Package(name, expired, owner, recipient, venueRef, type, open, created, updated);
        
        return package_;
    }
    
    
}
