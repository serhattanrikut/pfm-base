/**
 * 
 */
package com.gtc.pfm.domain;

/**
 * default venue types
 * 
 * @author stanriku
 *
 */
public enum VenueTypeEnum {

    BAR(1,"bar"),
    NIGHT_CLUB(2,"night club"),
    RESTAURANT(3,"restaurant"),
    HOUSE(4,"house"),
    CITY_CENTER(5,"city center"),
    SQUARE(6,"square");
    
    
    public int type;

    public String name;
    
    private VenueTypeEnum(int type,String name) {
        this.setType(type);
        this.setName(name);
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("VenueType Enum[type=");
        builder.append(type);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }
}
