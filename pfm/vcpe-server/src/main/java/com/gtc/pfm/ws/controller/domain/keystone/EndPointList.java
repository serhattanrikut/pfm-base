/**
 * 
 */
package com.gtc.pfm.ws.controller.domain.keystone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author stanriku
 *
 */
public class EndPointList extends ArrayList<EndPoint> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441213623210558679L;

	/**
	 * 
	 */
	public EndPointList() {
		
	}

	/**
	 * @param initialCapacity
	 */
	public EndPointList(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * @param c
	 */
	public EndPointList(Collection<? extends EndPoint> c) {
		super(c);
	}

}
