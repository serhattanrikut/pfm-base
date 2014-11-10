/**
 * 
 */
package com.gtc.pfm.ws.controller.client;

/**
 * represents a restful WS  call result. 
 * 
 * The result might be an error or success state
 * 
 * @author stanriku
 *
 */
public class RestResult {

	private int statusCode;
	private String statusText;
	private boolean success;
	private Object result;
	
	/**
	 * default constructor
	 */
	public RestResult() {
		
	}

	/**
	 * 
	 * @param statusCode
	 * @param success
	 * @param result
	 */
	public RestResult(int statusCode, String statusText, boolean success, Object result) {
		super();
		this.statusCode = statusCode;
		this.statusText = statusText;
		this.success = success;
		this.result = result;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RestResult [statusCode=" + statusCode + ", statusText="
				+ statusText + ", success=" + success + ", result=" + result
				+ "]";
	}
	
	
}
