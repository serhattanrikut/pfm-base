/**
 * 
 */
package com.gtc.pfm.ws.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gtc.pfm.ws.controller.domain.nova.OSAction;

/**
 * @author stanriku
 *
 */
@Controller
public class NovaRestWSController {

	private static Log logger = LogFactory.getLog(NovaRestWSController.class);
	
	/**
	 * 
	 */
	public NovaRestWSController() {
		
	}

	@RequestMapping(value="/v2/{tenantId}/servers/{vmId}/action", method=RequestMethod.POST,
            produces=MediaType.ALL_VALUE,
            consumes=MediaType.ALL_VALUE)
    public @ResponseBody @ResponseStatus(value=HttpStatus.ACCEPTED) void action(@PathVariable String tenantId, @PathVariable String vmId, 
    		@RequestBody(required=false) OSAction osAction, 
    		@RequestHeader(required=true,value="X-Auth-Project-Id") Object projectId,
    		@RequestHeader(required=true,value="X-Auth-Token") Object token) {
        System.out.println("tenantId:"+tenantId + ", vmId:"+vmId + ", projectId:"+projectId + ", token:"+token);
		logger.debug("received rest request: " + osAction);
		
    }
	

}
