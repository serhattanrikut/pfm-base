package com.gtc.pfm.ws.controller.domain.keystone;

import java.util.ArrayList;
import java.util.Collection;

public class EndPointLinkList extends ArrayList<EndPointLink>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7007996243174900908L;

	/**
	 * default constructor
	 */
	public EndPointLinkList() {
		
	}

	/**
	 * @param c
	 */
	public EndPointLinkList(Collection<? extends EndPointLink> c) {
		super(c);
	}

	/**
	 * @param initialCapacity
	 */
	public EndPointLinkList(int initialCapacity) {
		super(initialCapacity);
	}

}
