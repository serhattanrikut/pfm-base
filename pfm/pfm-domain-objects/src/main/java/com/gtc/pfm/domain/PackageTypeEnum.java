/**
 * 
 */
package com.gtc.pfm.domain;

/**
 * @author stanriku
 *
 */
public enum PackageTypeEnum {

    PRIVATE(1,"PRIVATE"), PUBLIC(2,"PUBLIC"), PROMOTION(3,"PROMOTION");
    
    int type;
    String name;
    
    private PackageTypeEnum(int type,String name) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Package Type Enum[name:]");
        sb.append(this.getName()).append(", type:").append(this.getType()).append("]");
        return sb.toString();
    }
    
}
