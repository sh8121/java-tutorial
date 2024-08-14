package com.sh8121.javatutorial.javaprojectreactor.tutorial.n3_sink;

import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.LogUtil;
import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.TimeUtil;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.scheduler.Schedulers;

public class N2_SinkMany {

    public static void main(String[] args) {
        var sinkMany1 = Sinks.many().unicast().<Integer>onBackpressureBuffer();
        var sinkManyFlux1 = sinkMany1.asFlux().publishOn(Schedulers.parallel());

        sinkMany1.emitNext(1, EmitFailureHandler.FAIL_FAST);
        sinkMany1.emitNext(2, EmitFailureHandler.FAIL_FAST);

        sinkManyFlux1.subscribe(num -> LogUtil.info("subscribed: {}", num));

        TimeUtil.sleep(5000L);

        sinkMany1.emitNext(3, EmitFailureHandler.FAIL_FAST);
        sinkMany1.emitNext(4, EmitFailureHandler.FAIL_FAST);

        TimeUtil.sleep(1000L);
        System.out.println();

        var sinkMany2 = Sinks.many().multicast().<Integer>onBackpressureBuffer();
        var sinkManyFlux2 = sinkMany2.asFlux().publishOn(Schedulers.parallel());

        sinkMany2.emitNext(1, EmitFailureHandler.FAIL_FAST);
        sinkMany2.emitNext(2, EmitFailureHandler.FAIL_FAST);

        sinkManyFlux2.subscribe(num -> LogUtil.info("subscribed1: {}", num));

        TimeUtil.sleep(5000L);

        sinkManyFlux2.subscribe(num -> LogUtil.info("subscribed2: {}", num));

        sinkMany2.emitNext(3, EmitFailureHandler.FAIL_FAST);

        TimeUtil.sleep(2000L);

        sinkMany2.emitNext(4, EmitFailureHandler.FAIL_FAST);

        TimeUtil.sleep(1000L);
        System.out.println();

        var sinkMany3 = Sinks.many().replay().<Integer>limit(2);
        var sinkManyFlux3 = sinkMany3.asFlux();

        sinkMany3.emitNext(1, EmitFailureHandler.FAIL_FAST);
        sinkMany3.emitNext(2, EmitFailureHandler.FAIL_FAST);
        sinkMany3.emitNext(3, EmitFailureHandler.FAIL_FAST);

        sinkManyFlux3.subscribe(num -> LogUtil.info("subscribed1: {}", num));

        sinkMany3.emitNext(4, EmitFailureHandler.FAIL_FAST);

        sinkManyFlux3.subscribe(num -> LogUtil.info("subscribed2: {}", num));
    }
}
