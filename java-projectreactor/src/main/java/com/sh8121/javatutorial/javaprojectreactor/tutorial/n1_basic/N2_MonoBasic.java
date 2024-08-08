package com.sh8121.javatutorial.javaprojectreactor.tutorial.n1_basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class N2_MonoBasic {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(N2_MonoBasic.class);

        var monoPublisher1 = Mono.just("Hello");
        var monoMap1 = monoPublisher1.map(String::toUpperCase);
        monoMap1.subscribe(
                str -> logger.info("subscribed: {}", str),
                err -> logger.error("onError: {}", err.getMessage(), err),
                () -> logger.info("onComplete")
        );

        System.out.println();

        var monoPublisher2 = Mono.<String>empty();
        monoPublisher2.subscribe(
                str -> logger.info("subscribed: {}", str),
                null,
                () -> logger.info("onComplete")
        );
    }
}
