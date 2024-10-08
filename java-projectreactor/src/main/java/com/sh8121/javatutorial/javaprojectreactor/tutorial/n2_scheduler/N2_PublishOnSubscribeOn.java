package com.sh8121.javatutorial.javaprojectreactor.tutorial.n2_scheduler;

import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.LogUtil;
import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class N2_PublishOnSubscribeOn {

    public static void main(String[] args) {
        var fluxPublisher1 = Flux.range(1, 10)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(num -> {
                    TimeUtil.sleep(1000L);
                    LogUtil.info("range onNext {}", num);
                })
                .publishOn(Schedulers.boundedElastic())
                .filter(num -> num % 2 == 0)
                .doOnNext(num -> {
                    TimeUtil.sleep(1000L);
                    LogUtil.info("filter onNext {}", num);
                })
                .publishOn(Schedulers.boundedElastic())
                .map(num -> num * 2)
                .doOnNext(num -> {
                    TimeUtil.sleep(1000L);
                    LogUtil.info("map onNext {}", num);
                });

        fluxPublisher1.subscribe(num -> LogUtil.info("subscribed1 {}", num));
//        fluxPublisher1.subscribe(num -> LogUtil.info("subscribed2 {}", num));

        TimeUtil.sleep(13000L);
    }
}
