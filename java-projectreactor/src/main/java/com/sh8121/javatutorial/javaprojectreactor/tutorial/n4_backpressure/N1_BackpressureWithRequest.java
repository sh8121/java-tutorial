package com.sh8121.javatutorial.javaprojectreactor.tutorial.n4_backpressure;

import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.LogUtil;
import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.TimeUtil;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class N1_BackpressureWithRequest {

    public static void main(String[] args) {
        var fluxPublisher1 = Flux.range(1, 10)
                .doOnRequest(value -> LogUtil.info("onRequest: {}", value))
                .publishOn(Schedulers.parallel())
                .subscribeOn(Schedulers.parallel());

        fluxPublisher1.subscribe(new BaseSubscriber<>() {
            private int count = 0;

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                LogUtil.info("onSubscribe");
                request(2);
            }

            @Override
            protected void hookOnNext(Integer value) {
                LogUtil.info("onNext: {}", value);
                count++;
                if (count == 2) {
                    count = 0;
                    TimeUtil.sleep(1000L);
                    request(2);
                }
            }

            @Override
            protected void hookOnComplete() {
                LogUtil.info("onComplete");
            }
        });

        TimeUtil.sleep(7000L);
    }
}
