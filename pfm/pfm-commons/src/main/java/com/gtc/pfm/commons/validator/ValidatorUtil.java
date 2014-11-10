/**
 * 
 */
package com.gtc.pfm.commons.validator;

import com.gtc.exceptions.InvalidArgumentException;

/**
 * @author stanriku
 *
 */
public class ValidatorUtil {

	public static void stringNullCheck(String parameter, String message) throws InvalidArgumentException {
		if(parameter == null || parameter.length() == 0) {
			throw new InvalidArgumentException(message, parameter);
		}
	}

}
