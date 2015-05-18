/*------------------------------------------------------------------------------
 *
 * File: ConfigurationInterface.java
 *
 *******************************************************************************
 * COPYRIGHT Ericsson 2011
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *
 * List of classes: ConfigurationInterface
 *
 *----------------------------------------------------------------------------*/
package com.ericsson.v1.util;

/**
 * ConfigurationInterface.
 * 
 * All configuration classes that support auto-reload shall implement this
 * interface<br>
 * 
 * Revision History:<br>
 * 1.0 - Original release<br>
 * 
 * @author Iqbal Khan
 * 
 * @version 1.0, 2015/04/07
 */
public interface ConfigurationInterface {
	
    /**
     * method called to reload configuration.
     * @throws AdaptationsConfigurationException 
     */
    void init() throws AdaptationsConfigurationException;
}
