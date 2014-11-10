/**
 * 
 */
package com.gtc.pfm.domain;

/**
 * Presents a location with longitude, latitude and distance
 * 
 * @author stanriku
 *
 */
public class Location {

    private double longitude;
    
    private double latitude;
    
    private double distance;
   
    /**
     * default constructor
     */
    public Location() {
        
    }

    /**
     * @param longitude
     * @param latitude
     */
    public Location(double longitude, double latitude) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * @param longitude
     * @param latitude
     * @param distance
     */
    public Location(double longitude, double latitude,  double distance) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * returns location as double array
     * 
     * @return double[]
     */
    public double[] getLocation() {
        return new double[]{this.longitude,this.latitude};
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (!(obj instanceof Location)) {
            return false;
        }
        Location other = (Location) obj;
        if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance)) {
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
        builder.append("Location [longitude=");
        builder.append(longitude);
        builder.append(", latitude=");
        builder.append(latitude);
        builder.append(", distance=");
        builder.append(distance);
        builder.append("]");
        return builder.toString();
    }
    
}
