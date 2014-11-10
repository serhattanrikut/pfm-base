/**
 * 
 */
package com.gtc.pfm.domain;

/**
 * VenueType qualifiers {@link Venue}(s)
 * 
 * @author stanriku
 *
 */
public class VenueType {

    public int type;
    
    public String name;
    
    /**
     * 
     */
    public VenueType() {
        
    }
    
    /**
     * 
     */
    public VenueType(VenueTypeEnum venueTypeEnum) {
        this.setName(venueTypeEnum.getName());
        this.setType(venueTypeEnum.getType());
    }
   
    /**
     * @param type
     * @param name
     */
    public VenueType(int type, String name) {
        super();
        this.type = type;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("VenueType [type=");
        builder.append(type);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + type;
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
        if (!(obj instanceof VenueType)) {
            return false;
        }
        VenueType other = (VenueType) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

}
