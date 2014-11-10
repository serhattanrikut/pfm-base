/**
 * 
 */
package com.gtc.pfm.domain;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Package represents an item to be dropped in {@link Venue}(s) as surprise or promotion.
 * Several package types exist in order to identify actions to be taken against when a package is opened  
 * 
 * @author stanriku
 *
 */
@Document
public class Package extends PfmMongoDomainObject{

    public final static String nameField = "name";
    public final static String expiredField = "exp";
    public final static String ownerField = "own";
    public final static String recipientField = "rcpt";
    public final static String venueRefField = "vr";
    public final static String typeField = "t";
    public final static String openField = "o";
    
    public final static String owner_id_Filed = ownerField + "." + idField;
    public final static String owner_username_Filed = ownerField + "." + UserReference.usernameField;
    public final static String recipient_id_Filed = recipientField + "." + idField;
    public final static String recipient_username_Filed = recipientField + "." + UserReference.usernameField;
    public final static String venueRef_id_Field = venueRefField + "." + idField;
    public final static String venueRef_location_Field = venueRefField + "." + VenueReference.locationField;
    public final static String venueRef_type_Field = venueRefField + "." + VenueReference.typeField;
    public final static String venueRef_countryName_Field = venueRefField + "." + VenueReference.countryNameField;
    public final static String venueRef_cityName_Field = venueRefField + "." + VenueReference.cityNameField;
    
    @Field(value=nameField)
    String name;
    
    @Field(value=expiredField)
    Date expired;
    
    @Field(value=ownerField)
    UserReference owner;
    
    @Field(value=recipientField)
    UserReference recipient;
    
    @Field(value=venueRefField)
    VenueReference venueRef;
    
    @Field(value=typeField)
    int type;
    
    @Field(value=openField)
    boolean open;
      
    @Transient
    double distance;
    
    /**
     * default constructor
     * 
     */
    public Package() {
       
    }
    
    /**
     * @param name
     * @param expired
     * @param owner
     * @param recipient
     * @param venueRef
     * @param type
     * @param open
     */
    public Package(String name, Date expired, UserReference owner, UserReference recipient,
            VenueReference venueRef, int type, boolean open) {
        super();
        this.name = name;
        this.expired = expired;
        this.owner = owner;
        this.recipient = recipient;
        this.venueRef = venueRef;
        this.type = type;
        this.open = open;
    }

    /**
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
     */
    public Package(String name, Date expired, UserReference owner, UserReference recipient,
            VenueReference venueRef, int type, boolean open, Date created, Date updated) {
        super();
        this.name = name;
        this.expired = expired;
        this.owner = owner;
        this.recipient = recipient;
        this.venueRef = venueRef;
        this.type = type;
        this.open = open;
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
     * @return the expired
     */
    public Date getExpired() {
        return expired;
    }

    /**
     * @param expired the expired to set
     */
    public void setExpired(Date expired) {
        this.expired = expired;
    }

    /**
     * @return the owner
     */
    public UserReference getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(UserReference owner) {
        this.owner = owner;
    }

    /**
     * @return the recipient
     */
    public UserReference getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(UserReference recipient) {
        this.recipient = recipient;
    }

    /**
     * @return the open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    
    /**
     * @return the venueRef
     */
    public VenueReference getVenueRef() {
        return venueRef;
    }

    /**
     * @param venueRef the venueRef to set
     */
    public void setVenueRef(VenueReference venueRef) {
        this.venueRef = venueRef;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Package [name=");
        builder.append(name);
        builder.append(", expired=");
        builder.append(expired);
        builder.append(", owner=");
        builder.append(owner);
        builder.append(", recipient=");
        builder.append(recipient);
        builder.append(", venueReference=");
        builder.append(venueRef);
        builder.append(", type=");
        builder.append(type);
        builder.append(", open=");
        builder.append(open);
        builder.append(", id=");
        builder.append(id);
        builder.append(", created=");
        builder.append(created);
        builder.append(", updated=");
        builder.append(updated);
        builder.append("]");
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
}
