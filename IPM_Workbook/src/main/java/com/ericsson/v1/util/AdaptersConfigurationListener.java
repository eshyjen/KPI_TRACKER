/*------------------------------------------------------------------------------
 *
 * File: AdaptersConfigurationListener.java
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
 * List of classes: AdaptersConfigurationInterface
 *
 *----------------------------------------------------------------------------*/
package com.ericsson.v1.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jci.monitor.FilesystemAlterationListener;
import org.apache.commons.jci.monitor.FilesystemAlterationMonitor;
import org.apache.commons.jci.monitor.FilesystemAlterationObserver;
import org.apache.log4j.Logger;

/**
 * AdaptersConfigurationListener.
 * 
 * This class monitors properties files changes and reloads modified
 * configuration<br>
 * 
 * Revision History:<br>
 * 1.0 - Original release<br>
 * 
 * @author Iqbal Khan
 * 
 * @version 1.0, 2015/04/07
 */
public class AdaptersConfigurationListener implements
        FilesystemAlterationListener {
    /**
     * log4j logger.
     */
    private static final Logger LOGGER = Logger
            .getLogger(AdaptersConfigurationListener.class);
    /**
     * file change monitoring interval.
     */
    private long interval = 0L;
    /**
     * xml file to configuration object hash map.
     */
    private Map<String, ConfigurationInterface> xml2Object;
    /**
     * file system monitor.
     */
    private FilesystemAlterationMonitor fileMonitor;

    /**
     * Constructor without parameters.
     * 
     * @param fileName
     */
    AdaptersConfigurationListener() {
        LOGGER.debug("AdaptersConfigurationListener instance created");
        fileMonitor = new FilesystemAlterationMonitor();
        xml2Object = new HashMap<String, ConfigurationInterface>();
    }

    /**
     * start the listener.
     * 
     */
    public void startListening() {
        LOGGER.debug("Start listening ...");
        fileMonitor.setInterval(interval);
        fileMonitor.start();
        LOGGER.debug("Listener started");
    }

    /**
     * start the listener.
     * 
     */
    public void stopListening() {
        LOGGER.debug("Stop listening ...");
        fileMonitor.stop();
        LOGGER.debug("Listener stoped");
    }

    /**
     * Set listening interval in milliseconds.
     * 
     * @param value
     *            listening interval in milliseconds
     */
    public void setInterval(final long value) {
        interval = value;
        fileMonitor.setInterval(interval);
    }

    /**
     * Add file to file monitor.
     * 
     * @param file
     *            xml file to monitor
     * @param xmlConf
     *            xml configuration instance
     */
    public void monitorFile(final File file,
            final ConfigurationInterface xmlConf) {
        LOGGER.debug("Monitoring file for change " + file.getAbsolutePath());
        xml2Object.put(file.getName(), xmlConf);
        fileMonitor.addListener(file, this);
    }

    /**
     * onDirectoryChange.
     * 
     * @param arg0
     *            changed directory
     */
    @Override
    public void onDirectoryChange(final File arg0) {
        LOGGER.debug("Directory changed:" + arg0.getName());
    }

    /**
     * onDirectoryCreate.
     * 
     * @param arg0
     *            created directory
     */
    @Override
    public void onDirectoryCreate(final File arg0) {
        LOGGER.debug("Directory created:" + arg0.getName());
    }

    /**
     * onDirectoryDelete.
     * 
     * @param arg0
     *            deleted directory
     */
    @Override
    public void onDirectoryDelete(final File arg0) {
        LOGGER.debug("Directory deleted:" + arg0.getName());
    }

    /**
     * onFileChange event reload configuration.
     * 
     * @param file
     *            changed file
     */
    @Override
    public void onFileChange(final File file) {
        LOGGER.debug("File changed:" + file.getName());
        final ConfigurationInterface xmlConfg = xml2Object.get(file.getName());
        if (xmlConfg != null) {
            try {
				xmlConfg.init();
			} catch (AdaptationsConfigurationException e) {
				LOGGER.error(e.getMessage());
			}
            LOGGER.debug("File reloaded:" + file.getName());
        }
    }

    /**
     * onFileCreate.
     * 
     * @param arg0
     *            created file
     */
    @Override
    public void onFileCreate(final File arg0) {
        LOGGER.debug("File created:" + arg0.getName());
    }

    /**
     * onFileDelete.
     * 
     * @param arg0
     *            created file
     */
    @Override
    public void onFileDelete(final File arg0) {
        LOGGER.debug("File deleted:" + arg0.getName());
    }

    /**
     * onStart.
     * 
     * @param arg0
     *            file system observer
     */
    @Override
    public void onStart(final FilesystemAlterationObserver arg0) {
        LOGGER.debug("Listener started. Observer class:" + arg0.getClass());
    }

    /**
     * onStop.
     * 
     * @param arg0
     *            file system observer
     */
    @Override
    public void onStop(final FilesystemAlterationObserver arg0) {
        LOGGER.debug("Listener stoped. Observer class:" + arg0.getClass());
    }
}
