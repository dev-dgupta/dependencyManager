package com.iontrading.ion20_simple_component.subscriberFunctions;

import com.iontrading.talk.api.annotation.Identifier;
import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

import java.math.BigDecimal;

/**
 * Created by divya.gupta on 01-08-2018.
 */
@TalkType(name = "Trade")
public class PublisherTrade {

    @Identifier
    @TalkProperty
    private long id;

    @TalkProperty(name = "qty")
    private int quantity;

    @TalkProperty
    private BigDecimal price;

    // name attribute in case of nested bean is used as a "prefix" for
    // generating the full field id.
    // For example the "name" field of Trader class will be mapped as
    // "trader_name"
    @TalkProperty(name = "trader_")
    private Trader trader;

    public PublisherTrade(long id, Trader trader, int quantity, BigDecimal price) {
        this.id = id;
        this.trader = trader;
        this.quantity = quantity;
        this.price = price;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}
