/**
 * 
 */
package com.gtc.pfm.ws.controller.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.gtc.pfm.ws.controller.domain.keystone.Auth;
import com.gtc.pfm.ws.controller.domain.keystone.PasswordCredentials;

/**
 * @author stanriku
 * 
 */
public class RestClient {
	
	private static Logger logger = Logger.getLogger(RestClient.class);
	
	public static final int IWE_INTERNAL_ERROR_STATUS_CODE = 5000;
	public static final String IWE_INTERNAL_ERROR_STATUS_TEXT = "IWE internal error";

	private RestTemplate restTemplate = null;
	private RestResponseHandler restResponseHandler = new RestResponseHandler();

	public RestClient() {
		restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(restResponseHandler);
	}
	
	public RestResult postForObject(String url, Object request, Map<String,String> headersMap) {
		HttpHeaders headers = createHeaders(headersMap);
		RestResult result = postForObject(url, request, headers);
		return result;
	}
	
	public RestResult postForObject(String url, Object request, String username, String password) {
		HttpHeaders headers = createHeaders(username, password);
		RestResult result = postForObject(url, request, headers);
		return result;
	}
	
	public RestResult postForObject(String url, Object request, String username, String password, Map<String,String> headersMap) {
		HttpHeaders headers = createHeaders(username,password,headersMap);
		RestResult result = postForObject(url, request, headers);
		return result;
	}
	
	public RestResult postForObject(String url, Object request, HttpHeaders headers) {
		logger.debug("invoking ["+RestClient.class.getName()+".postForObject(String url, Object request, HttpHeaders headers)] "
				+ "with parameters[url:"+url+",HttpHeaders: "+
				headers+", Request Object:"+request+"]");
		RestResult result = null;
		ResponseEntity<Object> response = null;
		HttpEntity<Auth> he = new HttpEntity<Auth>((Auth)request,headers);
		System.out.println(he.toString());
		try {
			response = restTemplate.exchange(new URI(url), HttpMethod.POST, he, Object.class);
			logger.debug("result:" + response.getBody());
			result = toRestResult(response);
		} catch (Exception e) {
			logger.error("an error occured in rest call",e);
			result = toRestResult(e);
		}
		
		return result;
	}
	
	
	public RestResult getForObject(String url) throws URISyntaxException{
		return getForObject(url, new HashMap<String, String>());
		
	}
	
	public RestResult getForObject(String url,String username, String password) throws URISyntaxException{
		HttpHeaders headers = createHeaders(username, password);
		return getForObject(url, headers);
		
	}
	
	public RestResult getForObject(String url,Map<String,String> headersMap) throws URISyntaxException{
		HttpHeaders headers = createHeaders(headersMap);
		return getForObject(url, headers);
		
	}
	
	public RestResult getForObject(String url,String username, String password, Map<String,String> headersMap) throws URISyntaxException{
		HttpHeaders headers = createHeaders(username, password,headersMap);
		return getForObject(url, headers);
		
	}
	
	
	public RestResult getForObject(String url,HttpHeaders headers) throws URISyntaxException {
		logger.debug("invoking ["+RestClient.class.getName()+".getForObject(String url,HttpHeaders headers)] with parameters[url:"+url+",HttpHeaders: "+
		headers+"]");
		RestResult restResult = null;
		ResponseEntity<Object> response = null;
		HttpEntity<String> he = new HttpEntity<String>(headers);
		try {
			response = restTemplate.exchange(new URI(url), HttpMethod.GET, he, Object.class);
			System.out.println(response.getBody());
			restResult = toRestResult(response);
			logger.debug("rest result:" + restResult);
		} catch (Exception e) {
			logger.error("an error occured in rest call",e);
			restResult = toRestResult(e);
		}
		
		return restResult;
	}
	
	public HttpHeaders createHeaders(final String username,
			final String password,final Map<String, String> headersMap) {
		final Set<String> keySet = headersMap.keySet();
		return new HttpHeaders() {
			
			private static final long serialVersionUID = 2013221835774198875L;

			{
				
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset
						.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization header", authHeader);
				for (String key : keySet) {
					String object = headersMap.get(key);
					set(key, object);
					logger.debug("adding header: key:"+key+", value:" + object);
				}
			}
		};
	}
	
	/**
	 * creates http headers for authentication
	 * @param username
	 * @param password
	 * @return {@link HttpHeaders}
	 */
	public HttpHeaders createHeaders(final String username,
			final String password) {
		return new HttpHeaders() {
			
			private static final long serialVersionUID = 4990333076507677082L;

			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset
						.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	/**
	 * creates {@link HttpHeaders} for given {@link Map<String, String>}
	 * @param headersMap
	 * @return HttpHeaders
	 */
	public HttpHeaders createHeaders(final Map<String, String> headersMap) {
		final Set<String> keySet = headersMap.keySet();
		return new HttpHeaders() {
			
			private static final long serialVersionUID = -6995744763650634602L;

			{
				for (String key : keySet) {
					String object = headersMap.get(key);
					set(key, object);
					logger.debug("adding header: key:"+key+", value:" + object);
				}
			}
		};
	}
	
	/**
	 * converts actual restful ws call result to JSON String representation 
	 * of {@link RestResult} object
	 * 
	 * @param response
	 * @return JSON representation of {@link RestResult} object as String
	 */
	public String toJSONRestResultString(ResponseEntity<Object> response) {
		RestResult restResult = toRestResult(response);
		String json = toJSONString(restResult);
		return json;
	}
	
	/**
	 * converts {@link RestResult} object to JSON String
	 * 
	 * @param restResult
	 * @return JSON representation of {@link RestResult} object as String
	 */
	public String toJSONString(Object restResult) {
		Gson gson = new Gson();
		String json = gson.toJson(restResult);
		return json;
	}
	
	public RestResult toRestResult(Exception e) {
		RestResult restResult = new RestResult();
		restResult.setStatusCode(IWE_INTERNAL_ERROR_STATUS_CODE);
		restResult.setStatusText(IWE_INTERNAL_ERROR_STATUS_TEXT);
		restResult.setResult(e.getMessage());
		restResult.setSuccess(false);
		return restResult;
	}  
	
	/**
	 * instantiates {@link RestResult} from actual restful ws call result 
	 * 
	 * @param response
	 * @return {@link RestResult}
	 */
	public RestResult toRestResult(ResponseEntity<Object> response) {
		RestResult restResult = new RestResult();
		restResult.setStatusCode(response.getStatusCode().value());
		restResult.setStatusText(response.getStatusCode().name());
		restResult.setResult(response.getBody());
		restResult.setSuccess(!RestUtil.isError(response.getStatusCode()));
		return restResult;
	} 
	
	// ---- getter and setter
	

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}


	public RestResponseHandler getRestResponseHandler() {
		return restResponseHandler;
	}

	
	// ---- main method
	
	public static void main(String[] args) {
		
		String data = "{\"id\": \"19\",\"name\": \"vCPE_checkHealth\",\"data\": {\"$detail\":\"true\",\"$state\":\"3\",\"$description\":\"Virtual CPE check health:{\"sceProperty\":{\"name\":\"vCPE_checkHealth\",\"value\":\"Virtual CPE is up and Runnning\",\"server\":null},\"status\":\"SUCCESS\",\"description\":\"Property successfully retrieved\"}\"},\"children\": [{\"id\": \"17\",\"name\": \"vCPE_instantiate\",\"data\": {\"$detail\":\"true\",\"$state\":\"1\",\"$description\":\"vCPE_instantiate\"},\"children\": [{\"id\": \"18\",\"name\": \"vCPE_getIPAddress\",\"data\": {\"$detail\":\"true\",\"$state\":\"2\",\"$description\":\"vCPE_getIPAddress\"},\"children\": []\\}]\\}]\\}";
		System.out.println(data);
		Gson gson = new Gson();
		String fromJson = gson.toJson(data,
				String.class);
		System.out.println(fromJson);
		try {
			RestClient restClient = new RestClient();
			RestResult restResult = restClient.getForObject("http://127.0.0.1:8080/vcpe-server/restt/v2.0/test?name=ManagementServer.URL","netopmgr","password");
			System.out.println("Get:" + restResult);
			Map<String, String> headerMap = new HashMap<String,String>();
			headerMap.put("WWW-Authenticate", "false");
			headerMap.put("Content-Type", "application/json");
			Auth a = new Auth("serhat_tenant", new PasswordCredentials("a", "b"));
			//RestResult restResult = restClient.getForObject("http://172.31.110.226:7005/vcpe-server/rest/v2.0/test",headerMap);
			restResult = restClient.postForObject("http://127.0.0.1:8080/vcpe-server/rest/v2.0/tokens",a,headerMap);
			System.out.println("Post:" + restResult);
		} catch (RestClientException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAuth() {
		String auth = "{ \"auth\": {"
						+ " \"tenantName\": \"serhat\","
						+ " \"passwordCredentials\": {"
								+ " \"username\": \"serhat\","
								+ " \"password\": \"abcde\""
								+ "}"
					+ "}"
				+ "}";
		return auth;
	}
 
}
