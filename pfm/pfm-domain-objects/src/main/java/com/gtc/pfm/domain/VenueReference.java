/**
 * 
 */
package com.gtc.pfm.domain;

import org.springframework.context.annotation.PropertySource;
import org.springframework.data.annotation.Transient;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

/**
 *  * A {@link VenueReference} class represents an embedable {@link Venues} object with limited properties 
 * that can be used in other main domain objects like {@link Package}
 * 
 * @author stanriku
 *
 */
public class VenueReference implements PfmDomainObjectInterface {

    public static final String nameField = Venue.nameField;
    public static final String typeField = Venue.typeField;
    public static final String countryNameField = Venue.countryNameField;
    public static final String cityNameField = Venue.cityNameField;
    public static final String locationField = Venue.locationField;
    
    private String id;
    
    @Field(value=nameField)
    private String name;
    
    @Field(value=typeField)
    private int type;
    
    @Field(value=countryNameField)
    private String countryName;
    
    @Field(value=cityNameField)
    private String cityName;
    
    @Field(value=locationField)
    private double[] location;
    
    /**
     * 
     */
    public VenueReference() {
        
    }

    
    
    /**
     * @param id
     * @param name
     * @param type
     * @param countryName
     * @param cityName
     * @param location
     */
    public VenueReference(String id, String name, int type, String countryName,
            String cityName, double[] location) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.countryName = countryName;
        this.cityName = cityName;
        this.location = location;
    }
    
    /**
     * @param id
     * @param name
     * @param type
     * @param countryName
     * @param cityName
     * @param longitude
     * @param lattidute
     */
    public VenueReference(String id, String name, int type, String countryName,
            String cityName, double longitude, double lattidute) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.countryName = countryName;
        this.cityName = cityName;
        location = new double[]{longitude,lattidute};
    }



    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the latitude
     */
    public double getLatitude() {
        return (location == null || location.length <2) ?  0 : location[1];
    }


    /**
     * @return the longitude
     */
    
    public double getLongitude() {
        return (location == null || location.length <2) ?  0 : location[0];
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof VenueReference)) {
            return false;
        }
        VenueReference other = (VenueReference) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("VenueReference [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", type=");
        builder.append(type);
        builder.append(", countryName=");
        builder.append(countryName);
        builder.append(", cityName=");
        builder.append(cityName);
        builder.append(", location=");
        builder.append(Arrays.toString(location));
        builder.append("]");
        return builder.toString();
    }
    
}
