/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.Location;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.PackageTypeEnum;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.persistence.repository.package_.PackageRepository;
import com.gtc.pfm.repository.test.util.PackageRepositoryTestUtils;
import com.gtc.pfm.repository.test.util.UserRepositoryTestUtil;
import com.gtc.pfm.repository.test.util.VenueRepositoryTestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.test.context.ContextConfiguration;
import java.util.Calendar;
import java.util.List;

/**
 * 
 * @author stanriku
 */
@ContextConfiguration(locations = {"/applicationContext-test.xml"})
public class PackageRepositoryTest extends MongoTestBase{

     public static UserRepositoryTestUtil userRepositoryTestUtil = new UserRepositoryTestUtil();
     public static VenueRepositoryTestUtils venueRepositoryTestUtils  = new VenueRepositoryTestUtils();
     public static PackageRepositoryTestUtils packageRepositoryTestUtils = new PackageRepositoryTestUtils();
    
   
     
     
     @Autowired 
     private PackageRepository repository;

     /**
      * 
      * @throws Exception
      */
     @BeforeClass
     public static void beforeCalss() throws Exception {
         MongoTestBase.beforeCalss();
         
     }

     /**
      * 
      * @throws Exception
      */
     @AfterClass
     public static void afterClass() throws Exception {
         MongoTestBase.afterClass();
     }
     
     
     /**
      * 
      */
     @Before 
     public void setUp() {

         mongoTemplate.dropCollection("package");
         mongoTemplate.createCollection("package");

     }
     
     @After 
     public void tearDown() throws ProviderException {
         repository.deleteAll(Package.class);
     }

     @Test
     public void testCreatePackage() throws Exception {
          
         Package _package =packageRepositoryTestUtils.createPackageMock("test", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Antalya, PackageTypeEnum.PUBLIC.getType(), false);
         repository.createPackage(_package);
         
         Assert.assertNotNull(_package.getId());
     }
     
     
     @Test
     public void testOpenPackage() throws Exception {
         
         Package _package =packageRepositoryTestUtils.createPackageMock("test", Calendar.getInstance().getTime(), 
                 PackageRepositoryTestUtils.owner, PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Antalya, PackageTypeEnum.PUBLIC.getType(), false);
         repository.createPackage(_package);
         
         Assert.assertNotNull(_package.getId());
         
         repository.openPackage(_package.getId(), PackageRepositoryTestUtils.recipient.getId());
         
         _package = repository.findPackageById(_package.getId());
         
         Assert.assertNotNull(_package);
         
         Assert.assertThat(_package.isOpen(), is(true));
         
     }
     
     @Test
     public void testFindAvailablePackagesByUserIdAndLocation() throws Exception {
         
         Package _package = packageRepositoryTestUtils.createPackageMock("test", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Antalya, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package);
         
         Assert.assertNotNull(_package.getId());
         
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Antalya.getLongitude(),PackageRepositoryTestUtils.venueRef_Antalya.getLatitude(),10);
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findAvailablePackagesByUserIdAndLocation(PackageRepositoryTestUtils.owner.getId(), location, rowFilter);
         
         Assert.assertNotNull(packages);
         
         Assert.assertThat(packages.size(),is(1));
         
         _package = packages.get(0);
         
         Assert.assertThat(_package.getVenueRef().getName(), is(PackageRepositoryTestUtils.venueRef_Antalya.getName()));
     }
     
     @Test
     public void testFindAllPackagesByUserIdAndLocation() throws Exception {
         
         populatePackages();
         
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         RowFilter rowFilter = new RowFilter(1,5);
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findAllPackagesByUserIdAndLocation(PackageRepositoryTestUtils.owner.getId(), location, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
         
     }
     
     @Test
     public void testFindOpenPackagesByUserIdAndLocation() throws Exception {
      
         populatePackages();
         
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findOpenPackagesByUserIdAndLocation(PackageRepositoryTestUtils.owner.getId(), location, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
     }
     
     @Test
     public void testFindPackagesByUserIdAndLocationAndPackageStatus() throws Exception {
         
         populatePackages();
         
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findPackagesByUserIdAndLocationAndPackageStatus(PackageRepositoryTestUtils.owner.getId(), location, false, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
     }
     
     @Test
     public void testFindAvailablePackagesByRecepientIdAndLocation() throws Exception {
         
         populatePackages();
         
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findAvailablePackagesByRecepientIdAndLocation(PackageRepositoryTestUtils.recipient.getId(), location, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
         
         
     }
     
     @Test
     public void testFindAllPackagesByRecepientIdAndLocation() throws Exception{
         
         populatePackages();
         
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findAllPackagesByRecepientIdAndLocation(PackageRepositoryTestUtils.recipient.getId(), location, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
         
         
     }
     
     @Test
     public void testFindOpenPackagesByRecepientIdAndLocation() throws Exception {
         
         populatePackages();
         
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findOpenPackagesByRecepientIdAndLocation(PackageRepositoryTestUtils.recipient.getId(), location, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
         
     }
     
     @Test
     public void testFindPackagesByRecepientIdAndLocationAndPackageStatus() throws Exception {
      
         populatePackages();
         
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findPackagesByRecepientIdAndLocationAndPackageStatus(PackageRepositoryTestUtils.recipient.getId(), location, false, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
     }
     
     @Test
     public void testFindAvailablePackagesByVenueId() throws Exception {
         populatePackages();
         
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findAvailablePackagesByVenueId(PackageRepositoryTestUtils.venueRef_Kadikoy.getId(), rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
     }
     
     @Test
     public void testFindOpenPackagesByVenueId() throws Exception {
         
         populatePackages();
         
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findOpenPackagesByVenueId(PackageRepositoryTestUtils.venueRef_Kadikoy.getId(), rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
     }
     
     @Test
     public void testFindPackagesByVenueIdIdAndPackageStatus() throws Exception {
         populatePackages();
         
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findPackagesByVenueIdIdAndPackageStatus(PackageRepositoryTestUtils.venueRef_Kadikoy.getId(), false, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));
     }
     
     @Test
     public void testFindAllPackagesByVenueId() throws Exception {
         
         populatePackages();
         
         RowFilter rowFilter = new RowFilter();
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findAllPackagesByVenueId(PackageRepositoryTestUtils.venueRef_Kadikoy.getId(), rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));    
     }
     
     @Test
     public void testfindPackagesByLocationAndPackageStatus() throws Exception {
         
         populatePackages();
         
         RowFilter rowFilter = new RowFilter();
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findPackagesByLocationAndPackageStatus(location, false, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));    
     }
     
     @Test
     public void testfindPackagesByLocation() throws Exception{
         
         
         populatePackages();
         
         RowFilter rowFilter = new RowFilter();
         Location location = new Location(PackageRepositoryTestUtils.venueRef_Kadikoy.getLongitude(),PackageRepositoryTestUtils.venueRef_Kadikoy.getLatitude(),1);
         
         GeospatialIndex gi = new GeospatialIndex(Package.venueRef_location_Field);
         
         getMongoTemplate().getCollection("package").ensureIndex(gi.getIndexKeys());
         
         List<Package> packages = repository.findPackagesByLocation(location, rowFilter);
         
         Assert.assertThat(packages, notNullValue());
         
         Assert.assertThat(packages.size(), not(0));   
     }
     
     public void populatePackages() throws Exception{
         // create packages for first user and first recipient
         Package _package_1 = packageRepositoryTestUtils.createPackageMock("_package_1", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Antalya, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_1);
         
         Package _package_2 = packageRepositoryTestUtils.createPackageMock("_package_2", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Istanbul, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_2);
         
         Package _package_3 = packageRepositoryTestUtils.createPackageMock("_package_3", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Izmir, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_3);
         
         Package _package_4 = packageRepositoryTestUtils.createPackageMock("_package_4", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Ankara, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_4);
         
         Package _package_5 = packageRepositoryTestUtils.createPackageMock("_package_5", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Atasehir, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_5);
         
         Package _package_6 = packageRepositoryTestUtils.createPackageMock("_package_6", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Bakirkoy, PackageTypeEnum.PROMOTION.getType(), false);
         
         repository.createPackage(_package_6);
         
         Package _package_7 = packageRepositoryTestUtils.createPackageMock("_package_7", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Caddebostan, PackageTypeEnum.PROMOTION.getType(), false);
         
         repository.createPackage(_package_7);
         
         Package _package_8 = packageRepositoryTestUtils.createPackageMock("_package_8", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Goztepe, PackageTypeEnum.PRIVATE.getType(), false);
         
         repository.createPackage(_package_8);
         
         Package _package_9 = packageRepositoryTestUtils.createPackageMock("_package_9", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PRIVATE.getType(), false);
         
         repository.createPackage(_package_9);
         
         Package _package_10 = packageRepositoryTestUtils.createPackageMock("_package_10", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_10);
         
         Package _package_11 = packageRepositoryTestUtils.createPackageMock("_package_11", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_KadikoyModa, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_11);
         
         Package _package_12 = packageRepositoryTestUtils.createPackageMock("_package_12", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_TaksimSquare, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_12);
         
         Package _package_13 = packageRepositoryTestUtils.createPackageMock("_package_13", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Uskudar, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_13);
         
         Package _package_14 = packageRepositoryTestUtils.createPackageMock("_package_14", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Yesilkoy, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_14);
         
         Package _package_15 = packageRepositoryTestUtils.createPackageMock("_package_15", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_KadikoyModa, PackageTypeEnum.PROMOTION.getType(), false);
         
         repository.createPackage(_package_15);
         
         // create packages for second user and second recipient
         
         Package _package_16 = packageRepositoryTestUtils.createPackageMock("_package_16", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Antalya, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_16);
         
         Package _package_17 = packageRepositoryTestUtils.createPackageMock("_package_17", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Istanbul, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_17);
         
         Package _package_18 = packageRepositoryTestUtils.createPackageMock("_package_18", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Izmir, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_18);
         
         Package _package_19 = packageRepositoryTestUtils.createPackageMock("_package_19", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Ankara, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_19);
         
         Package _package_20 = packageRepositoryTestUtils.createPackageMock("_package_20", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Atasehir, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_20);
         
         Package _package_21 = packageRepositoryTestUtils.createPackageMock("_package_21", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Bakirkoy, PackageTypeEnum.PROMOTION.getType(), false);
         
         repository.createPackage(_package_21);
         
         Package _package_22 = packageRepositoryTestUtils.createPackageMock("_package_22", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Caddebostan, PackageTypeEnum.PROMOTION.getType(), false);
         
         repository.createPackage(_package_22);
         
         Package _package_23 = packageRepositoryTestUtils.createPackageMock("_package_23", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Goztepe, PackageTypeEnum.PRIVATE.getType(), false);
         
         repository.createPackage(_package_23);
         
         Package _package_24 = packageRepositoryTestUtils.createPackageMock("_package_24", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PRIVATE.getType(), false);
         
         repository.createPackage(_package_24);
         
         Package _package_25 = packageRepositoryTestUtils.createPackageMock("_package_25", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_25);
         
         Package _package_26 = packageRepositoryTestUtils.createPackageMock("_package_26", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_KadikoyModa, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_26);
         
         Package _package_27 = packageRepositoryTestUtils.createPackageMock("_package_27", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_TaksimSquare, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_27);
         
         Package _package_28 = packageRepositoryTestUtils.createPackageMock("_package_28", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Uskudar, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_28);
         
         Package _package_29 = packageRepositoryTestUtils.createPackageMock("_package_29", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Yesilkoy, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_29);
         
         Package _package_30 = packageRepositoryTestUtils.createPackageMock("_package_30", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_KadikoyModa, PackageTypeEnum.PROMOTION.getType(), false);
         
         repository.createPackage(_package_30);
         
         // add extra packages for both of users for same location KADIKOY
         
         Package _package_31 = packageRepositoryTestUtils.createPackageMock("_package_31", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PRIVATE.getType(), true);
         
         repository.createPackage(_package_31);
         
         Package _package_32 = packageRepositoryTestUtils.createPackageMock("_package_32", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PROMOTION.getType(), false);
         
         repository.createPackage(_package_32);
         
         Package _package_33 = packageRepositoryTestUtils.createPackageMock("_package_33", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner_1, 
                 PackageRepositoryTestUtils.recipient_1, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PUBLIC.getType(), true);
         
         repository.createPackage(_package_33);
         
         Package _package_34 = packageRepositoryTestUtils.createPackageMock("_package_34", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PRIVATE.getType(), false);
         
         repository.createPackage(_package_34);
         
         Package _package_35 = packageRepositoryTestUtils.createPackageMock("_package_35", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PROMOTION.getType(), true);
         
         repository.createPackage(_package_35);
         
         Package _package_36 = packageRepositoryTestUtils.createPackageMock("_package_36", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_36);
         
         Package _package_37 = packageRepositoryTestUtils.createPackageMock("_package_37", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PUBLIC.getType(), true);
         
         repository.createPackage(_package_37);
         
         Package _package_38 = packageRepositoryTestUtils.createPackageMock("_package_38", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PUBLIC.getType(), false);
         
         repository.createPackage(_package_38);
         
         Package _package_39 = packageRepositoryTestUtils.createPackageMock("_package_39", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PUBLIC.getType(), true);
         
         repository.createPackage(_package_39);
         
         Package _package_40 = packageRepositoryTestUtils.createPackageMock("_package_40", Calendar.getInstance().getTime(), PackageRepositoryTestUtils.owner, 
                 PackageRepositoryTestUtils.recipient, PackageRepositoryTestUtils.venueRef_Kadikoy, PackageTypeEnum.PUBLIC.getType(), true);
         
         repository.createPackage(_package_40);
         
         
         
     }

    /**
     * @return the repository
     */
    public PackageRepository getRepository() {
        return repository;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(PackageRepository repository) {
        this.repository = repository;
    }
    
}