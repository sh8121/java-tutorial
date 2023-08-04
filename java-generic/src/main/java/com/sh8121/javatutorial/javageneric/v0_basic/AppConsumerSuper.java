package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v1.Electronics;
import com.sh8121.javatutorial.javageneric.model.v1.Radio;
import com.sh8121.javatutorial.javageneric.model.v1.Tv;
import java.util.ArrayList;
import java.util.List;

public class AppConsumerSuper {

    public static void main(String[] args) {
        List<? super Electronics> list = new ArrayList<Electronics>();
        list.add(new Tv("티비"));
        list.add(new Radio("라디오"));
        list.add(new Electronics());

//        for(Electronics e : list) { //compile error
//            System.out.println(e);
//        }
    }
}
