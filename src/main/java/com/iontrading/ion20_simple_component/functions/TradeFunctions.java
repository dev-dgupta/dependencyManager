package com.iontrading.ion20_simple_component.functions;

import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.talk.api.annotation.PrimitiveType;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;

import java.math.BigDecimal;

/**
 * Created by divya.gupta on 01-08-2018.
 */
public interface TradeFunctions {

    @TalkFunction(name = "createTrade")
    AsyncResult<String> createTrade(@TalkParam(name = "verb", description = "Trader gateway account") String verb,
                                    @TalkParam(name = "Price", typeHint = PrimitiveType.REAL) BigDecimal price,
                                    @TalkParam(name = "QtyTot") Double qty);

    @TalkFunction(name = "updateTrade")
    AsyncResult<String> updateTrade(@TalkParam(name = "id") String id,
                                    @TalkParam(name = "verb", description = "Trader gateway accoutn", nullable = true) String verb,
                                    @TalkParam(name = "Price", typeHint = PrimitiveType.REAL, nullable = true) BigDecimal price,
                                    @TalkParam(name = "QtyTot", nullable = true) Double qty);

    @TalkFunction(name = "updateTradeWithFieldValues")
    AsyncResult<String> updateTrade(@TalkParam(name = "id") String id, @TalkParam(name = "fieldValues") String... fieldValues);

    @TalkFunction(name = "deleteTrade")
    AsyncResult<String> deleteTrade(@TalkParam(name = "id") String id);
}
