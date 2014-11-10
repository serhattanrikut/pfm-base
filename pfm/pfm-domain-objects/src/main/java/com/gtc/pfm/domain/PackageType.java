/**
 * 
 */
package com.gtc.pfm.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @author stanriku
 *
 */
@Document
public class PackageType extends PfmMongoDomainObject implements PfmDomainObjectInterface{

    public static final String typeField = "type";
    public static final String nameFiled = "name";
    
    @Field(value=typeField)
    private int type;
    
    @Field(value=nameFiled)
    private String name;
    
    /**
     *  default constructor
     */
    public PackageType() {
        
    }
    
    /**
     * @param packageTypeEnum
     */
    public PackageType(PackageTypeEnum packageTypeEnum) {
        this.setType(packageTypeEnum.getType());
        this.setName(packageTypeEnum.getName());
    }
    
    /**
     * @param type
     * @param name
     */
    public PackageType(int type, String name) {
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
        builder.append("PackageType [type=");
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
        if (!(obj instanceof PackageType)) {
            return false;
        }
        PackageType other = (PackageType) obj;
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
