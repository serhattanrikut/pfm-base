/**
 * 
 */
package com.gtc.pfm.ws.controller;

import com.gtc.pfm.domain.Package;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author stanriku
 *
 */
public class PackageList extends ArrayList<Package> {

    /**
     * 
     */
    private static final long serialVersionUID = 318285072244278711L;

    /**
     * 
     */
    public PackageList() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public PackageList(int arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public PackageList(Collection<? extends Package> arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

}
