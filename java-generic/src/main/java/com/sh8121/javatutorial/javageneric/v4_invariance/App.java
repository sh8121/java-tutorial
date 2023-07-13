package com.sh8121.javatutorial.javageneric.v4_invariance;

import com.sh8121.javatutorial.javageneric.model.v1.Radio;
import com.sh8121.javatutorial.javageneric.model.v1.Tv;

public class App {

    public static void main(String[] args) {
        Tv tv = new Tv("티비");
        Radio radio = new Radio("라디오");

//        RemoteController<Electronics> tvRemoteController = new RemoteController<Tv>(tv); //compile error
//        RemoteController<Electronics> radioRemoteController = new RemoteController<Radio>(radio); //compile error
    }
}
