/*------------------------------------------------------------------------------
 *
 * File: AutoProvConfiguration.java
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
 * List of classes: AutoProvConfiguration
 *
 *----------------------------------------------------------------------------*/
package com.ericsson.v1.util;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * AdaptersConfiguration.
 * 
 * This class contains the methods to get the configuration properties from
 * properties file<br>
 * 
 * Revision History:<br>
 * 1.0 - Original release<br>
 * 
 * @author Iqbal Khan
 * 
 * @version 1.0, 2015/04/07
 */
public final class AdaptersConfiguration implements ConfigurationInterface {

	/**
	 * log4j logger.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdaptersConfiguration.class);

	/**
	 * configuration name constant.
	 */
	public static final String FACTORYNAME = "AdaptersConfiguration.xml";

	/**
	 * Error msgs.
	 */
	private static String errorMsg;

	/**
	 * Error Codes.
	 */
	public static final int ClassNotFoundExceptionErrorCode = 100;
	public static final int InstantiationExceptionErrorCode = 101;
	public static final int IllegalAccessExceptionErrorCode = 102;
	public static final int ConfigurationExceptionErrorCode = 103;

	/**
	 * default path constant.
	 */
	// public static final String DEFAULTPATH =
	// "/opt/ericsson/odp/adaptations/conf";
	public static final String DEFAULTPATH = "/opt/ericsson/sdg/ca/cr44/conf";

	/**
	 * default URL constant.
	 */
	public static final String DEFAULTCONFURL = DEFAULTPATH + "/" + FACTORYNAME;

	/**
	 * interval property.
	 */
	public static final String INTERVAL = "configuration.refresh.interval";

	/**
	 * config location environment variable constant.
	 */
	public static final String ADAPTERSCONFDIR = "ADAPTERS_CONF_DIR";

	/**
	 * xmlfile name constant.
	 */
	public static final String XMLCONFIGCLASS = "configuration.xmlconfig.class";

	/**
	 * xmlfile name constant.
	 */
	public static final String XMLFILE = "configuration.xmlfile.name";

	/**
	 * configuration directory constant.
	 */
	public static final String CONFIGDIR = "configuration.directory";

	public static final String CONFIGURE_LOGBACK = "configure.logback";

	/**
	 * singleton variable.
	 */
	private static volatile AdaptersConfiguration instance = null;

	/**
	 * config file url.
	 */
	private String configUrl = DEFAULTCONFURL;

	/**
	 * config file url.
	 */
	private String fileName = FACTORYNAME;

	/**
	 * config path.
	 */
	private String confDir = DEFAULTPATH;

	/**
	 * config variable.
	 */
	private Configuration config = null;

	/**
	 * config listener variable.
	 */
	private AdaptersConfigurationListener configListener = null;

	/**
	 * Constructor with parameter.
	 * 
	 * @param fileName
	 *            config file url
	 * @throws AdaptationsConfigurationException
	 */
	private AdaptersConfiguration(final String fileName)
			throws AdaptationsConfigurationException {
		init(fileName);
	}

	/**
	 * Constructor without parameter.
	 * 
	 * @throws AdaptationsConfigurationException
	 */
	private AdaptersConfiguration() throws AdaptationsConfigurationException {
		init(FACTORYNAME);
	}

	/**
	 * Get an instance of AdaptersConfiguration.
	 * 
	 * @return Returns singleton instance of AdaptersConfiguration
	 * @throws AdaptationsConfigurationException
	 */
	public static synchronized AdaptersConfiguration getInstance() {
		if (instance == null) {
			try {
				instance = new AdaptersConfiguration();
				String logback = instance.getConfiguration().getString(CONFIGURE_LOGBACK);
				if (logback != null && logback.equalsIgnoreCase("true")) {
					try {
						String filePath = instance.confDir + "/" + "logback.xml";
						DOMConfigurator.configure(filePath);
						LOGGER.info("logback configured");
					} catch (Exception e) {
						LOGGER.debug("unable to configure logback");
					}
				}
			} catch (AdaptationsConfigurationException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return instance;
	}

	/**
	 * Get an instance of AdaptersConfiguration.
	 * 
	 * @param fileName
	 *            , config file url
	 * @return Returns singleton instance of AdaptersConfiguration
	 * @throws AdaptationsConfigurationException
	 */
	public static synchronized AdaptersConfiguration getInstance(
			final String fileName) throws AdaptationsConfigurationException {
		if (instance == null) {
			instance = new AdaptersConfiguration(fileName);
		} else {
			instance.setFileName(fileName);
		}

		return instance;
	}

	/**
	 * Initialize configuration.
	 * 
	 * @throws AdaptationsConfigurationException
	 * 
	 */
	public void init() throws AdaptationsConfigurationException {
		init(fileName);
	}


    /**
     * Initialize configuration.
     * 
     * @param fileName, config file url
     * @throws AdaptationsConfigurationException 
     */
    private void init(final String fileName) throws AdaptationsConfigurationException {
        LOGGER.info("Initializing configuration...");
        final String sysConfDir = System.getProperty(ADAPTERSCONFDIR);

        if (sysConfDir != null) {
            confDir = sysConfDir;
        }

        configUrl = confDir + "/" + fileName;
        this.fileName = fileName;

        if (configListener != null) {
            configListener.stopListening();
            configListener = null;
        }

        final DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
        builder.setFileName(configUrl);

        try {
            config = builder.getConfiguration();
            if (sysConfDir != null) {
                config.setProperty(CONFIGDIR,sysConfDir);
            }        
        } catch (ConfigurationException e) {

            errorMsg = "Could not get configuration from factory " + e;
            LOGGER.error(errorMsg);
			throw new AdaptationsConfigurationException(
					ConfigurationExceptionErrorCode, errorMsg);
		}

		// start config listener
		if (config != null) {
			String listenerFlag = config
					.getString("configuration.listener.start");
			if (listenerFlag != null
					&& (listenerFlag.compareToIgnoreCase("true") == 0)) {
				configListener = new AdaptersConfigurationListener();
				final long interval = config.getLong(INTERVAL);
				configListener.setInterval(interval);
				addXmlFilesToConfigListener(configListener);
				configListener.startListening();
			}
		}

		LOGGER.info("Finished configuration initialization");
	}

	/**
	 * add XML configuration file to listener.
	 * 
	 * @param configListener
	 *            , file change listener
	 * @throws AdaptationsConfigurationException
	 */
	private void addXmlFilesToConfigListener(
			final AdaptersConfigurationListener configListener)
			throws AdaptationsConfigurationException {
		LOGGER.debug("Adding XML files to listener");
		final String[] xmlFiles = config.getStringArray(XMLFILE);
		final String[] xmlConfigClasses = config.getStringArray(XMLCONFIGCLASS);
		if (xmlFiles != xmlConfigClasses
				&& xmlFiles.length == xmlConfigClasses.length) {
			for (int index = 0; index < xmlFiles.length; index++) {
				try {
					addXmlFileToConfigListener(configListener, xmlFiles[index],
							xmlConfigClasses[index]);
				} catch (ClassNotFoundException e) {
					errorMsg = "Could not load class "
							+ xmlConfigClasses[index] + ". Exception:" + e;
					LOGGER.error(errorMsg);
					throw new AdaptationsConfigurationException(
							ClassNotFoundExceptionErrorCode, errorMsg);
				} catch (InstantiationException e) {
					errorMsg = "Could not load class "
							+ xmlConfigClasses[index] + ". Exception:" + e;
					LOGGER.error(errorMsg);
					throw new AdaptationsConfigurationException(
							InstantiationExceptionErrorCode, errorMsg);
				} catch (IllegalAccessException e) {
					errorMsg = "Could not load class "
							+ xmlConfigClasses[index] + ". Exception:" + e;
					LOGGER.error(errorMsg);
					throw new AdaptationsConfigurationException(
							IllegalAccessExceptionErrorCode, errorMsg);
				}
			}
		} else {
			if (xmlFiles == null || xmlFiles.length == 0) {
				LOGGER.warn("XML files are not configured for auto-reload");
			} else {
				LOGGER.error("XML files are not properly configured."
						+ XMLCONFIGCLASS + " and " + XMLFILE
						+ " properties should match.");
			}
		}
	}

	/**
	 * add XML configuration file to listener.
	 * 
	 * @param configListener
	 *            , file change listener
	 * @param xmlFile
	 *            XML file name
	 * @param xmlConfigClass
	 *            XML class name
	 * 
	 * @throws ClassNotFoundException
	 *             , InstantiationException, IllegalAccessException
	 */
	private void addXmlFileToConfigListener(
			final AdaptersConfigurationListener configListener,
			final String xmlFile, final String xmlConfigClass)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		LOGGER.debug("XML file:" + xmlFile + " configuration class is :"
				+ xmlConfigClass);
		final Class<?> classDefinition = Class.forName(xmlConfigClass);
		LOGGER.debug("Configuration class loaded :" + xmlConfigClass);
		ConfigurationInterface xmlConfig = null;
		if (classDefinition.equals(AdaptersConfiguration.class)) {
			LOGGER.debug("Self reference:" + xmlConfigClass);
			xmlConfig = this;
		} else {
			xmlConfig = (ConfigurationInterface) classDefinition.newInstance();
			LOGGER.debug("Created an instance of " + xmlConfigClass);
		}

		final File file = new File(confDir + "/" + xmlFile);
		LOGGER.debug("Adding XML file to listener:" + xmlFile);
		configListener.monitorFile(file, xmlConfig);
	}

	/**
	 * returns configuration.
	 * 
	 * @return Configuration
	 */
	public Configuration getConfiguration() {
		return config;
	}

	/**
	 * returns configuration PATH.
	 * 
	 * @return configuration path
	 */
	public String getPath() {
		return confDir;
	}

	/**
	 * set file system listening interval.
	 * 
	 * @param interval
	 *            , number of milliseconds
	 */
	public void setInterval(final long interval) {
		if (configListener != null) {
			configListener.setInterval(interval);
		}
	}

	/**
	 * returns configuration listener.
	 * 
	 * @return config listener
	 */
	public AdaptersConfigurationListener getListner() {
		if (configListener == null) {
			configListener = new AdaptersConfigurationListener();
			configListener.startListening();
		}
		return configListener;
	}

	/**
	 * set configuration file name.
	 * 
	 * @param fileName
	 *            xml config file name
	 * @throws AdaptationsConfigurationException
	 */
	public void setFileName(final String fileName)
			throws AdaptationsConfigurationException {
		final String newUrl = confDir + "/" + fileName;
		if (!configUrl.equals(newUrl)) {
			init(fileName);
		}
	}
}
