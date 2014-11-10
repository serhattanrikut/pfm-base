/**
 * 
 */
package com.gtc.pfm.domain;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.Date;

/**
 * Venue represents a physical place in the earth. Possible samples are bars, restaurant, houses etcs..      
 * 
 * @author stanriku
 *
 */
@Document
public class Venue extends PfmMongoDomainObject implements VenueInterface{

    public static final String nameField = "name";
    public static final String addressField = "addr";
    public static final String typeField = "t";
    public static final String countryNameField = "coN";
    public static final String cityNameField = "ciN";
    public static final String locationField = "l";
    
    @Field(value=nameField)
    public String name;
    
    @Field(value=addressField)
    public String address;
    
    @Field(value=typeField)
    public int type;
    
    @Field(value=countryNameField)
    public String countryName;
    
    @Field(value=cityNameField)
    private String cityName;

    @Transient
    private String distance;
    
    @Field(value=locationField)
    private double[] location;
    
    /**
     * default constructor
     * 
     */
    public Venue() {
       
    }
    
    /**
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param location
     */
    public Venue(String name, String address, int type, String cityName, String distance,
            double[] location) {
        super();
        this.name = name;
        this.address = address;
        this.type = type;
        this.cityName = cityName;
        this.distance = distance;
        this.location = location;
    }
    
    /**
     * 
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param location
     * @param created
     * @param updated
     */
    public Venue(String name, String address, int type, String cityName, String distance,
            double[] location, Date created, Date updated) {
        super();
        this.name = name;
        this.address = address;
        this.type = type;
        this.cityName = cityName;
        this.distance = distance;
        this.location = location;
        this.created = created;
        this.updated = updated;
    }

    /**
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param longitude
     * @param lattidute
     */
    public Venue(String name, String address, int type, String cityName, String distance, double longitude, double lattidute) {
        super();
        this.name = name;
        this.address = address;
        this.type = type;
        this.cityName = cityName;
        this.distance = distance;
        location = new double[]{longitude,lattidute};
    }
    
    /**
     * 
     * @param name
     * @param address
     * @param type
     * @param cityName
     * @param distance
     * @param longitude
     * @param lattidute
     * @param created
     * @param updated
     */
    public Venue(String name, String address, int type, String cityName, String distance, double longitude, double lattidute,
            Date created, Date updated) {
        super();
        this.name = name;
        this.address = address;
        this.type = type;
        this.cityName = cityName;
        this.distance = distance;
        location = new double[]{longitude,lattidute};
        this.created = created;
        this.updated = updated;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the location
     */
    public double[] getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(double[] location) {
        this.location = location;
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return location == null || location.length <2 ?  0 : location[1];
    }


    /**
     * @return the longitude
     */
    public double getLongitude() {
        return location == null || location.length <2 ?  0 : location[0];
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 
     * @return {@link VenueReference}
     */
    public VenueReference exposeVenueReference() {
        
        VenueReference venueRef = new VenueReference(this.getId(), this.getName(), this.getType(), 
                this.getCityName(), this.getCityName(), this.getLocation());
        
        return venueRef;
        
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Venue [name=");
        builder.append(name);
        builder.append(", address=");
        builder.append(address);
        builder.append(", type=");
        builder.append(type);
        builder.append(", countryName=");
        builder.append(countryName);
        builder.append(", cityName=");
        builder.append(cityName);
        builder.append(", distance=");
        builder.append(distance);
        builder.append(", location=");
        builder.append(Arrays.toString(location));
        builder.append("]");
        return builder.toString();
    }
    
    
    
}
