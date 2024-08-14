package com.sh8121.javatutorial.javaprojectreactor.tutorial.n3_sink;

import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.LogUtil;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;

public class N1_SinkOne {

    public static void main(String[] args) {
        var sinkOne = Sinks.<String>one();
        var sinkOneMono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", EmitFailureHandler.FAIL_FAST);
        sinkOne.emitValue("Hi Reactor", EmitFailureHandler.FAIL_FAST);

        sinkOneMono.subscribe(str -> LogUtil.info("subscribed: {}", str));
        sinkOneMono.subscribe(str -> LogUtil.info("subscribed: {}", str));
    }
}
