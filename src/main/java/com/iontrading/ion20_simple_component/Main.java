/**
 * Copyright (c) 2014 ION Trading ltd.
 * All Rights reserved.
 * 
 * This software is proprietary and confidential to ION Trading ltd.
 * and is protected by copyright law as an unpublished work.
 * Unauthorized access and disclosure strictly forbidden.
 */
package com.iontrading.ion20_simple_component;

import com.iontrading.isf.applicationserver.spi.AS;
import com.iontrading.jmix.logging.LoggerEnvironment;
import com.iontrading.proguard.annotation.Keep;

/**
 * Application launcher.
 */
@Keep
public class Main {

    @Keep
    public static void main(String[] args) {
        // Use system properties to override application defaults for mkv
        // settings.
        System.setProperty("mkv.currency", "ANY");
        System.setProperty("mkv.source", "ION20_COMPONENT");

        // Use system properties to set the default application logfile name. If
        // this is not set on command line or mkv.jinit the default is the
        // component name (mkv.component).
        // System.setProperty("mkv.log_name", "ION20_COMPONENT");
        // System.setProperty("mkv.ext.log_name", "ION20_COMPONENT");

        // Configure jboss logging (hibernate 4+) to use slf4j
        System.setProperty("org.jboss.logging.provider", "slf4j");
        
        // Avoid duplicated "Thread" tokens in JMIX log traces when using the
        // ION 2.0 tracing service
        LoggerEnvironment.setCustomProperty("LOW.TraceThread", "false");

        AS.createLaunchConfiguration()
        // pass command line arguments to the application
            .withArgs(args)
            // Entry point module(s) for the application
            .withModules(ApplicationModule.class)
            // Configure ION Component properties
            .withComponentInfo("SimpleION20App", "Simple ION 2.0 Application", "0.0.1", "na")
            // Released components must provide the component clear (first
            // param) and encrypted key (second param). Empty means PUB license.
            // .withLicenseInfo("PUB", "")
            // starts the application
            .launch();
    }
}
