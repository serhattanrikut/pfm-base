/**
 * 
 */
package com.gtc.exceptions;

import java.util.Arrays;

/**
 * @author stanriku
 *
 */
public class InvalidArgumentException extends Exception {

	private Object[] arguments;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5688257948571090323L;

	/**
	 * 
	 */
	public InvalidArgumentException() {
		
	}

	/**
	 * 
	 * @param message
	 * @param argument
	 */
	public InvalidArgumentException(String message, Object argument) {
		super(message);
		this.arguments = new Object[] {argument};
	}
	
	/**
	 * 
	 * @param message
	 * @param arguments
	 */
	public InvalidArgumentException(String message, Object... arguments) {
		super(message);
		this.arguments = arguments;
	}

	/**
	 * 
	 * @param cause
	 * @param argument
	 */
	public InvalidArgumentException(Throwable cause, Object argument) {
		super(cause);
		this.arguments = new Object[] {argument};
	}
	
	/**
	 * 
	 * @param cause
	 * @param arguments
	 */
	public InvalidArgumentException(Throwable cause, Object... arguments) {
		super(cause);
		this.arguments = arguments;
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param argument
	 */
	public InvalidArgumentException(String message, Throwable cause, Object argument) {
		super(message, cause);
		this.arguments = new Object[] {argument};
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param arguments
	 */
	public InvalidArgumentException(String message, Throwable cause, Object... arguments) {
		super(message, cause);
		this.arguments = arguments;
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 * @param argument
	 */
	public InvalidArgumentException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace, Object argument) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.arguments = new Object[] {argument};
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 * @param arguments
	 */
	public InvalidArgumentException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace, Object... arguments) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.arguments = arguments;
	}

	/**
	 * @return the arguments
	 */
	public Object[] getArguments() {
		return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvalidArgumentException [arguments="
				+ Arrays.toString(arguments) + "] : " + super.toString();
	}
	
}
