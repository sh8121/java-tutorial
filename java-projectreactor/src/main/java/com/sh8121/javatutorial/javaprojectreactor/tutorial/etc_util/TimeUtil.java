package com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util;

public class TimeUtil {

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
