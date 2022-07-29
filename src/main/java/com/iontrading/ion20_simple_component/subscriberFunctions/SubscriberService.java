package com.iontrading.ion20_simple_component.subscriberFunctions;

import com.google.inject.Inject;
import com.iontrading.isf.boot.spi.IService;
import com.iontrading.talk.ionbus.subscriber.spi.Subscriber;
import com.iontrading.talk.ionbus.subscriber.spi.Subscription;
import com.iontrading.talk.ionbus.subscriber.spi.SubscriptionDataEvent;
import com.iontrading.talk.ionbus.subscriber.spi.SubscriptionListenerAdapter;

/**
 * Created by divya.gupta on 02-08-2018.
 */
public class SubscriberService implements IService {

    @Inject
    Subscriber subscriber;
    private static final String SERVICE_NAME = "SubscriberService";

    @Override
    public String getName() {
        return SERVICE_NAME;
    }

    @Override
    public void start() throws Exception {

        Subscription subscription = subscriber.subscribe("TradeService", PublisherTrade.class, "TRADE")
                .withListener(new SubscriptionListenerAdapter<PublisherTrade>() {
                    @Override
                    public void onDownloadEnd() {
                        System.out.println("on download end");
                    }

                    @Override
                    public void onAdd(SubscriptionDataEvent<PublisherTrade> event) {
                        System.out.println(event.getRecordName());
                        System.out.println(event.get());
                    }

                    @Override
                    public void onDelete(SubscriptionDataEvent<PublisherTrade> event) {
                        System.out.println(event.getRecordName());
                        System.out.println(event.get());
                    }

                    @Override
                    public void onDataUnavailable() {
                        System.out.println("data unavailable");
                    }

                }).start();

        System.out.println(subscription.getId());
    }

    @Override
    public void shutdown() {

    }
}
