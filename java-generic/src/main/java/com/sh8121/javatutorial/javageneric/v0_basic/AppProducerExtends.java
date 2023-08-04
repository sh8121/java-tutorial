package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v1.Electronics;
import com.sh8121.javatutorial.javageneric.model.v1.Tv;
import java.util.ArrayList;
import java.util.List;

public class AppProducerExtends {

    public static void main(String[] args) {
        List<Tv> tvs = new ArrayList<>();
        tvs.add(new Tv("티비1"));
        tvs.add(new Tv("티비2"));
        tvs.add(new Tv("티비3"));

        List<? extends Electronics> electronics = tvs;
        for (Electronics e : electronics) {
            System.out.println(e);
        }

//        electronics.add(new Tv("티비4")); //compile error
    }
}
