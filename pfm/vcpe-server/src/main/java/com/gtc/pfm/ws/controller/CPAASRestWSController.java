/**
 * 
 */
package com.gtc.pfm.ws.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author stanriku
 *
 */
@Controller
public class CPAASRestWSController {

	private static Log logger = LogFactory.getLog(CPAASRestWSController.class); 
	/**
	 * 
	 */
	public CPAASRestWSController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/cpaas", method={RequestMethod.POST,RequestMethod.GET},
            produces=MediaType.ALL_VALUE,
            consumes=MediaType.ALL_VALUE)
    public @ResponseBody @ResponseStatus(value=HttpStatus.OK) void bindNat(@RequestParam("vcpe") String vcpe,@RequestParam("iip") String iip) {
        System.out.println(CPAASRestWSController.class.getName() + ".bindNat(...) : vcpe:"+vcpe + ", vmId:"+iip);
		logger.debug("received"+"vcpe:"+vcpe + ", vmId:"+iip);
		
    }

}
