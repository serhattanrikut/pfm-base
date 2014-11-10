/**
 * 
 */
package com.gtc.pfm.repository.test.util;

import com.gtc.pfm.domain.Venue;
import com.gtc.pfm.domain.VenueReference;
import com.gtc.pfm.domain.VenueTypeEnum;

import org.bson.types.ObjectId;

import java.util.Calendar;
import java.util.Date;

/**
 * provides mock implementations and util methods in order to mock {@link Venue} 
 *
 * @author stanriku
 *
 */
public class VenueRepositoryTestUtils {

    public static Venue ANKARA ;
    public static Venue ISTANBUL;
    public static Venue IZMIR;
    public static Venue ANTALYA;
    public static Venue TAKSIM_SQUARE;
    public static Venue KADIKOY;
    public static Venue KADIKOY_MODA;
    public static Venue USKUDAR;
    public static Venue GOZTEPE;
    public static Venue ATASEHIR;
    public static Venue CADDEBOSTAN;
    public static Venue BAKIRKOY;
    public static Venue YESILKOY;
    
    static {
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
    }
    
    /**
     * 
     */
    public VenueRepositoryTestUtils() {
       
    }
    
    /**
     * creates in-memory mock {@link Venue} object
     * 
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param location
     * @return {@link Venue}
     */
    public Venue createVenueMock(String name, String address, int type, String cityName, String distance, double[] location) {
        
        Venue venue = new Venue(name, address, type, cityName, distance, location);
        
        return venue;
    }
    
    /**
     * creates in-memory mock {@link Venue} object
     * 
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param longitude
     * @param lattitude
     * @return {@link Venue}
     */
    public Venue createVenueMock(String name, String address, int type, String cityName, String distance, double longitude, double lattitude) {
        
        Venue venue = new Venue(name, address, type, cityName, distance, longitude, lattitude);
        
        return venue;
    }
    
    /**
     * creates in-memory mock {@link Venue} object
     * 
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param longitude
     * @param lattitude
     * @param created
     * @param updated
     * @return {@link Venue}
     */
    public Venue createVenueMock(String name, String address, int type, String cityName, String distance, double longitude, double lattitude,
            Date created, Date updated) {
        
        Venue venue = new Venue(name, address, type, cityName, distance, longitude, lattitude, created, updated);
        
        return venue;
    }
    
    /**
     * 
     * creates in-memory mock {@link Venue} object with {@link ObjectId}
     * 
     * @param id
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param longitude
     * @param lattitude
     * @param created
     * @param updated
     * @return {@link Venue}
     */
    public Venue createVenueMock(String id,String name, String address, int type, String cityName, String distance, double longitude, double lattitude,
            Date created, Date updated) {
        
        Venue venue = new Venue(name, address, type, cityName, distance, longitude, lattitude, created, updated);
        venue.setId(id);
        
        return venue;
    }
    
    /**
     * creates in-memory mock {@link Venue} object
     * 
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param location
     * @param created
     * @param updated
     * @return  {@link Venue}
     */
    public Venue createVenueMock(String name, String address, int type, String cityName, String distance, double[] location,
            Date created, Date updated) {
        
        Venue venue = new Venue(name, address, type, cityName, distance, location, created,updated);
        
        return venue;
    }
    
    /**
     * creates in-memory mock {@link Venue} object
     * 
     * @param id
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param location
     * @param created
     * @param updated
     * @return {@link Venue}
     */
    public Venue createVenueMock(String id, String name, String address, int type, String cityName, String distance, double[] location,
            Date created, Date updated) {
        
        Venue venue = new Venue(name, address, type, cityName, distance, location, created,updated);
        venue.setId(id);
        
        return venue;
    }
    
    /**
     * creates in-memory mock {@link VenueReference}
     * 
     * @param id
     * @param name
     * @param type
     * @param countryName
     * @param cityName
     * @param location
     * @return {@link VenueReference}
     */
    public VenueReference createVenueReference(String id, String name, int type, String countryName, String cityName, double[] location) {
        
        VenueReference venueRef = new VenueReference(id, name, type, countryName, cityName, location);
        
        return venueRef;
        
    }
    
    /**
     * creates in-memory mock {@link VenueReference}
     * 
     * @param id
     * @param name
     * @param type
     * @param countryName
     * @param cityName
     * @param longitude
     * @param lattitude
     * @return {@link VenueReference}
     */
    public VenueReference createVenueReference(String id, String name, int type, String countryName, String cityName, double longitude, double lattitude) {
        
        VenueReference venueRef = new VenueReference(id, name, type, countryName, cityName, longitude,lattitude);
        
        return venueRef;
        
    }
    
    /**
     * 
     * creates in-memory mock {@link VenueReference}
     * 
     * @param id
     * @param venue
     * @return {@link VenueReference}
     */
    public VenueReference createVenueReference(String id, Venue venue) {
        
        VenueReference venueRef = new VenueReference(id, venue.getName(), venue.getType(), venue.getCountryName(), venue.getCityName(), venue.getLongitude(),venue.getLatitude());
        
        return venueRef;
        
    }

}
