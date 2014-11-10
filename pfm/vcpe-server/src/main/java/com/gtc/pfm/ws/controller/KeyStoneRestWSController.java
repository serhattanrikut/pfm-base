/**
 * 
 */
package com.gtc.pfm.ws.controller;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtc.pfm.ws.controller.domain.keystone.Access;
import com.gtc.pfm.ws.controller.domain.keystone.Auth;
import com.gtc.pfm.ws.controller.domain.keystone.EndPoint;
import com.gtc.pfm.ws.controller.domain.keystone.EndPointLinkList;
import com.gtc.pfm.ws.controller.domain.keystone.EndPointList;
import com.gtc.pfm.ws.controller.domain.keystone.PasswordCredentials;
import com.gtc.pfm.ws.controller.domain.keystone.ServiceCatalog;
import com.gtc.pfm.ws.controller.domain.keystone.Tenant;
import com.gtc.pfm.ws.controller.domain.keystone.Token;

/**
 * This class exposes business methods of KeyStone stack as restful web service
 * 
 * @author stanriku
 *
 */
@Controller
public class KeyStoneRestWSController {
	
	public KeyStoneRestWSController() {
		
	}

	private static Log logger = LogFactory.getLog(KeyStoneRestWSController.class);
	
	@RequestMapping(method=RequestMethod.POST, value="/v2.0/tokens",
            produces=MediaType.APPLICATION_JSON_VALUE,
            consumes=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Access tokens(@RequestBody(required=false) Auth auth) {
        System.out.println("received rest request: " + auth);
		logger.debug("received rest request: " + auth);
		
		Access access = createAccess(auth);
		System.out.println("returning  object: " + access.toString());
		return access;
    }
	
	@RequestMapping(value = "/v2.0/test", method = {RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.ALL_VALUE})
	public @ResponseBody Access test() {

		Auth auth =  new Auth("testTenant", new PasswordCredentials("netopmgr", "alcatel01"));
		System.out.println("received rest request: " + auth);
		logger.debug("received rest request: " + auth);

		Access access = createAccess(auth);
		return access;
	}
	
	private Access createAccess(Auth auth) {
		if(auth == null) {
			System.out.println("received NULL auth.returning null");
			logger.error("received NULL auth.returning null");
			return null;
		}
		
		Access access = new Access();
		Token token = generateToken(auth);
		access.setToken(token);
		ServiceCatalog serviceCatalog = generateServiceCatalog(auth);
		access.setServiceCatalog(serviceCatalog);
		return access;
	}
	
	private ServiceCatalog generateServiceCatalog(Auth auth) {
		ServiceCatalog serviceCatalog = new ServiceCatalog();
		serviceCatalog.setName("compute");
		serviceCatalog.setType("nova");
		serviceCatalog.setEndpoints_links(new EndPointLinkList());
		serviceCatalog.setEndpoints(generateEndPointList(auth));
		return serviceCatalog;
	}
	
	private EndPointList generateEndPointList(Auth auth) {
		EndPointList endPointList =  new EndPointList();
		endPointList.add(generateEndPoint(auth));
		return endPointList;
	}
	
	private EndPoint generateEndPoint(Auth auth) {
		EndPoint endPoint =  new EndPoint();
		endPoint.setAdminURL("http://10.254.51.1:8774/v2/17193ffad443442e9e5c1d0c62afca14");
		endPoint.setRegion("RegionOne");
		endPoint.setInternalURL("http://10.254.51.1:8774/v2/17193ffad443442e9e5c1d0c62afca14");
		endPoint.setId(generateId(auth));
		endPoint.setPublicURL("http://10.254.51.1:8774/v2/17193ffad443442e9e5c1d0c62afca14");
		return endPoint;
	}
	
	private Token generateToken(Auth auth) {
		Token token = new Token();
		token.setId(generateId(auth));
		token.setExpires(Calendar.getInstance().getTime().toString());
		token.setIssued_at(Calendar.getInstance().getTime().toString());
		Tenant tenant = generateTenant(auth);
		token.setTenant(tenant);
		return token;
	}
	
	private Tenant generateTenant(Auth auth) {
		Tenant tenant = new Tenant();
		tenant.setName(auth.getTenantName());
		tenant.setDescription("tenant description for " + auth.getTenantName());
		tenant.setEnabled(true);
		tenant.setId(generateId(auth));
		return tenant;
	}
	
	private String generateId(Auth auth) {
		StringBuilder id = new StringBuilder((auth.getTenantName()));
		id.append("-").append(auth.getPasswordCredentials().getUsername()).append("-").
		append(auth.getPasswordCredentials().getPassword());
		return id.toString();
	}
}
