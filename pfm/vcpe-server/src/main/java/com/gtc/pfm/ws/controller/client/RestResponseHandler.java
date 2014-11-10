/**
 * 
 */
package com.gtc.pfm.ws.controller.client;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * @author stanriku
 *
 */
public class RestResponseHandler implements ResponseErrorHandler {
	
	private static Logger logger = Logger.getLogger(RestResponseHandler.class);

	/* (non-Javadoc)
	 * @see org.springframework.web.client.ResponseErrorHandler#handleError(org.springframework.http.client.ClientHttpResponse)
	 */
	public void handleError(ClientHttpResponse response) throws IOException {
		System.out.println("RestResponseHandler:Response error: status code:" + response.getStatusCode() + ", status text:" +response.getStatusText());
		logger.error("An error occurred during a rest call: status code:"+response.getStatusCode()+", status text:"+response.getStatusText());
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.client.ResponseErrorHandler#hasError(org.springframework.http.client.ClientHttpResponse)
	 */
	public boolean hasError(ClientHttpResponse arg0) throws IOException {
		return RestUtil.isError(arg0.getStatusCode());
	}
	

}
