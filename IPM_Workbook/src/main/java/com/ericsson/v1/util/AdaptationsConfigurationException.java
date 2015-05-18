
/*------------------------------------------------------------------------------
*
* File: AdaptationsConfigurationException.java
*
*******************************************************************************
* COPYRIGHT Ericsson 2012
*
* The copyright to the computer program(s) herein is the property of
* Ericsson Inc. The programs may be used and/or copied only with written
* permission from Ericsson Inc. or in accordance with the terms and
* conditions stipulated in the agreement/contract under which the
* program(s) have been supplied.
*******************************************************************************
*
* List of classes: AdaptationsConfigurationException
*
*----------------------------------------------------------------------------*/
package com.ericsson.v1.util;

public class AdaptationsConfigurationException extends Exception{
	private final int code;
	private static final long serialVersionUID = 5982419603454946508L;

	/**
	 * Constructor of AdaptationsConfigurationException.
	 * 
	 * @param code
	 *            code of the exception
	 * @param message
	 *            message of the exception
	 * 
	 */
	AdaptationsConfigurationException(final int code, final String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Method getCode Returns the code of exception of type integer
	 * 
	 * @return code
	 * 
	 */
	public int getCode() {
		return code;
	}

}
