/**
 * 
 */
package com.gtc.pfm.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author stanriku
 *
 */
@Document
public class City extends PfmMongoDomainObject{

    public static final String nameField = "name"; 
    public static final String countryField = "coN";
    
    @Field(value=nameField)
    String name;
    
    @Field(value=countryField)
    String countryName;
    
    /**
     * 
     */
    public City() {
       
    }

    /**
     * @param name
     * @param countryName
     */
    public City(String name, String countryName) {
        super();
        this.name = name;
        this.countryName = countryName;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("City [name=");
        builder.append(name);
        builder.append(", countryName=");
        builder.append(countryName);
        builder.append(", id=");
        builder.append(id);
        builder.append(", created=");
        builder.append(created);
        builder.append(", updated=");
        builder.append(updated);
        builder.append("]");
        return builder.toString();
    }
    
}
