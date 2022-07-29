/**
 * Copyright (c) 2014 ION Trading ltd.
 * All Rights reserved.
 * <p>
 * This software is proprietary and confidential to ION Trading ltd.
 * and is protected by copyright law as an unpublished work.
 * Unauthorized access and disclosure strictly forbidden.
 */
package com.iontrading.ion20_simple_component;

import javax.inject.Inject;

import com.iontrading.ion20_simple_component.functions.TradeFunctions;
import com.iontrading.isf.boot.spi.IService;
import com.iontrading.isf.commons.callback.Callback;
import com.iontrading.isf.dependency_manager.api.DependencyChangeEvent;
import com.iontrading.isf.dependency_manager.api.DependencyManager;
import com.iontrading.isf.dependency_manager.api.DependencyObserver;
import com.iontrading.isf.dependency_manager.api.DependencySnapshot;
import com.iontrading.isf.dependency_manager.providers.IonBusDependency;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.isf.trace.ITracerFactory;

import java.math.BigDecimal;

/**
 * Defines automatically starting services.
 */

/**
 * here we check whether the service is running or not. it acts as a client.
 */
public class ApplicationService implements IService {

    /**   */
    private static final String SERVICE_NAME = "ApplicationService";
    private final ITracer trace;
    private final TradeFunctions tradefunctions;
    @Inject
    DependencyManager dependencyManager;


    @Inject
    private ApplicationService(ITracerFactory logFactory, TradeFunctions tradefunctions) {
        this.trace = logFactory.createTracer(SERVICE_NAME);
        this.tradefunctions = tradefunctions;
    }

    public String getName() {
        return SERVICE_NAME;
    }

    public void start() throws Exception {
        trace.INFO().key("Service").action("Starting up").end();

        dependencyManager.create(observer).addInterest(new IonBusDependency("TradeService")).start();


    }

    public void shutdown() {
        trace.INFO().key("Service").action("Shutting down").end();
        dependencyManager.removeInterest(observer);

    }

    DependencyObserver observer = new DependencyObserver() {
        @Override
        public void onChange(DependencyChangeEvent snapshots) {
            // check if all deps are available
            boolean allAvailable = true;
            for (DependencySnapshot<?> dep : snapshots) {
                if (!dep.isAvailable()) {
                    allAvailable = false;
                    break;
                }
            }
            if (allAvailable) {
                // status is an application object implemented by the sample and it's not relevant for the tutorial
//                status.running();
                System.out.println("Status is running");
                tradefunctions.createTrade("Peter", BigDecimal.valueOf(256.09), 7.0).addCallback(new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Failure");
                    }
                });

//                tradefunctions.deleteTrade("139").addCallback(new Callback<String>() {
//                    @Override
//                    public void onSuccess(String s) {
//                        System.out.println(s);
//                    }
//
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        System.out.println("Failure");
//                    }
//                });

                tradefunctions.updateTrade("68", "Price:8512.54").addCallback(new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });

                tradefunctions.updateTrade("14", "Verb:Divya").addCallback(new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });

                tradefunctions.updateTrade("122", "Verb:Yami", "Qty:9.8", "Price:812.54").addCallback(new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });

            } else {
//                status.notRunning();
                System.out.println("Status is not running");
            }
        }
    };
}
