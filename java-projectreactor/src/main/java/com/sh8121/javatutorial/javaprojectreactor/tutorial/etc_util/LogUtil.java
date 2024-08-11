package com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger("CommonLogger");

    public static void info(String formattedMsg, Object... args) {
        logger.info(formattedMsg, args);
    }

    public static void error(String formattedMsg, Object... args) {
        logger.error(formattedMsg, args);
    }
}
