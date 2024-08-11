package com.sh8121.javatutorial.javaprojectreactor.tutorial.n1_basic;

import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.LogUtil;
import reactor.core.publisher.Mono;

public class N2_MonoBasic {

    public static void main(String[] args) {
        var monoPublisher1 = Mono.just("Hello");
        var monoMap1 = monoPublisher1.map(String::toUpperCase);
        monoMap1.subscribe(
                str -> LogUtil.info("subscribed: {}", str),
                err -> LogUtil.error("onError: {}", err.getMessage(), err),
                () -> LogUtil.info("onComplete")
        );

        System.out.println();

        var monoPublisher2 = Mono.<String>empty();
        monoPublisher2.subscribe(
                str -> LogUtil.info("subscribed: {}", str),
                null,
                () -> LogUtil.info("onComplete")
        );
    }
}
