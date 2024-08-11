package com.sh8121.javatutorial.javaprojectreactor.tutorial.n2_scheduler;

import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.LogUtil;
import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class N3_PublishOnSubscribeOnWithSmallScheduler {

    public static void main(String[] args) {
        var newBoundedElasticScheduler = Schedulers.newBoundedElastic(2, 2, "NewBoundedElasticScheduler", 1, true);

        var fluxPublisher1 = Flux.range(1, 10)
                .subscribeOn(newBoundedElasticScheduler)
                .doOnNext(num -> {
                    TimeUtil.sleep(1000L);
                    LogUtil.info("range onNext {}", num);
                })
                .publishOn(newBoundedElasticScheduler)
                .filter(num -> num % 2 == 0)
                .doOnNext(num -> {
                    TimeUtil.sleep(1000L);
                    LogUtil.info("filter onNext {}", num);
                })
                .publishOn(newBoundedElasticScheduler)
                .map(num -> num * 2)
                .doOnNext(num -> {
                    TimeUtil.sleep(1000L);
                    LogUtil.info("map onNext {}", num);
                });

        fluxPublisher1.subscribe(num -> LogUtil.info("subscribed {}", num));

        TimeUtil.sleep(17000L);
    }
}
