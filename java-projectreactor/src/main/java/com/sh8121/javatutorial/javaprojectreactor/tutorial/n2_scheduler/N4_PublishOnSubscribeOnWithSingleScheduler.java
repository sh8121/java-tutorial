package com.sh8121.javatutorial.javaprojectreactor.tutorial.n2_scheduler;

import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.LogUtil;
import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class N4_PublishOnSubscribeOnWithSingleScheduler {

    public static void main(String[] args) {
        var fluxPublisher1 = Flux.range(1, 10)
                .subscribeOn(Schedulers.single())
                .doOnNext(num -> LogUtil.info("range onNext {}", num))
                .publishOn(Schedulers.single())
                .filter(num -> num > 5)
                .doOnNext(num -> LogUtil.info("filter onNext {}", num))
                .publishOn(Schedulers.single())
                .map(num -> num * 2)
                .doOnNext(num -> LogUtil.info("map onNext {}", num))
                .publishOn(Schedulers.single());

        fluxPublisher1.subscribe(num -> LogUtil.info("subscribed1 {}", num));
        fluxPublisher1.subscribe(num -> LogUtil.info("subscribed2 {}", num));

        TimeUtil.sleep(1000L);
    }
}
