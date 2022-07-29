package com.iontrading.ion20_simple_component.subscriberFunctions;

import com.iontrading.talk.api.annotation.Identifier;
import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

/**
 * Created by divya.gupta on 01-08-2018.
 */
@TalkType
public class Trader {

    @Identifier
    @TalkProperty
    private final long id;

    @TalkProperty
    private final String name;

    public Trader(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @TalkProperty
    public String getName() {
        return this.name;
    }
}
