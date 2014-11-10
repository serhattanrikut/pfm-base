package com.gtc.pfm.ws.controller.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class RestApiService {
	public static final Logger logger = LogManager.getLogger(RestApiService.class);
	
	public static final String ISO_URL_ENCODER = "ISO-8859-1";
	
	public static final String serviceName = "RestApiService";	
	public String getServiceName() {
		return serviceName;
	}		

	
	private String hostname;
	private String port;
	private String login;
	private String password;	
	private Client restClient;		
	
	private boolean isLoggingEnabled = true;
		
	
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public Client getRestClient() {
		return restClient;
	}

	public RestApiService(String hostname, String port, String login, String password) {
		this.hostname = hostname;
		this.port = port;
		this.login = login;
		this.password = password;
				
		this.restClient = Client.create();		
		this.restClient.addFilter(new HTTPBasicAuthFilter(this.login, this.password)); 
	}
		
	
	public static void main(String[] args) {
		
	}
}
