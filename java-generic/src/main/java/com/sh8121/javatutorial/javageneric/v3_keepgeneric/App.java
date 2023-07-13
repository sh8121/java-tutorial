package com.sh8121.javatutorial.javageneric.v3_keepgeneric;

import com.sh8121.javatutorial.javageneric.model.v0.Radio;
import com.sh8121.javatutorial.javageneric.model.v0.Tv;
import java.lang.reflect.ParameterizedType;

public class App {

    public static void main(String[] args) throws NoSuchFieldException {
        Tv tv = new Tv("티비");
        Radio radio = new Radio("라디오");
        TvRemoteController tvRemoteController = new TvRemoteController(tv);
        RadioRemoteController radioRemoteController = new RadioRemoteController(radio);

        ParameterizedType remoteControllerTvType = (ParameterizedType) tvRemoteController.getClass().getGenericSuperclass();
        System.out.println(remoteControllerTvType.getActualTypeArguments()[0]);

        ParameterizedType remoteControllerRadioType = (ParameterizedType) radioRemoteController.getClass()
            .getDeclaredField("remoteController").getGenericType();
        System.out.println(remoteControllerRadioType.getActualTypeArguments()[0]);
    }
}
