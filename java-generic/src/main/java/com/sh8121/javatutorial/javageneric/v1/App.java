package com.sh8121.javatutorial.javageneric.v1;

import com.sh8121.javatutorial.javageneric.model.Radio;
import com.sh8121.javatutorial.javageneric.model.Tv;

public class App {

    public static void main(String[] args) {
        Tv tv1 = new Tv("티비1");
        Radio radio1 = new Radio("라디오1");
        RemoteController<Tv> tvRemoteController1 = new RemoteController<Tv>(tv1);
        RemoteController<Radio> radioRemoteController1 = new RemoteController<Radio>(radio1);

        //region 정상 사용
        Tv connectedTv = tvRemoteController1.getConnectedDevice();
        System.out.println(connectedTv.getTitle());

        Radio connectedRadio = radioRemoteController1.getConnectedDevice();
        System.out.println(connectedRadio.getName());
        //endregion

        //region 잘못 사용, 컴파일 에러
//        Radio connectedRadio = tvRemoteController1.getConnectedDevice();
//        System.out.println(connectedRadio.getName());
//
//        Tv connectedTv = radioRemoteController1.getConnectedDevice();
//        System.out.println(connectedTv.getTitle());
        //endregion
    }
}
