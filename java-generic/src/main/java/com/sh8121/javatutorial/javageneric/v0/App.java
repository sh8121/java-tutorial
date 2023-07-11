package com.sh8121.javatutorial.javageneric.v0;

import com.sh8121.javatutorial.javageneric.model.Radio;
import com.sh8121.javatutorial.javageneric.model.Tv;

public class App {

    public static void main(String[] args) {
        Tv tv1 = new Tv("티비1");
        Radio radio1 = new Radio("라디오1");
        RemoteController tvRemoteController1 = new RemoteController(tv1);
        RemoteController radioRemoteController1 = new RemoteController(radio1);

        //region 정상 사용
        Object connectedDevice1 = tvRemoteController1.getConnectedDevice();
        Tv connectedTv = (Tv) connectedDevice1;
        System.out.println(connectedTv.getTitle());

        Object connectedDevice2 = radioRemoteController1.getConnectedDevice();
        Radio connectedRadio = (Radio) connectedDevice2;
        System.out.println(connectedRadio.getName());
        //endregion

        //region 잘못 사용
//        Object connectedDevice1 = tvRemoteController1.getConnectedDevice();
//        Radio connectedRadio = (Radio)connectedDevice1;
//        System.out.println(connectedRadio.getName());
//
//        Object connectedDevice2 = radioRemoteController1.getConnectedDevice();
//        Tv connectedTv = (Tv)connectedDevice2;
//        System.out.println(connectedTv.getTitle());
        //endregion
    }
}
