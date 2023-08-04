package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v0.Radio;
import com.sh8121.javatutorial.javageneric.model.v0.Tv;

public class AppRuntimeType {

    public static void main(String[] args) {
        Tv tv1 = new Tv("티비1");
        Radio radio1 = new Radio("라디오1");
        RemoteController<Tv> tvRemoteController1 = new RemoteController<Tv>(tv1);
        RemoteController<Radio> radioRemoteController1 = new RemoteController<Radio>(radio1);

        System.out.println(tvRemoteController1.getClass());
        System.out.println(radioRemoteController1.getClass());
    }

    static class RemoteController<Device> {

        private Device connectedDevice;

        public RemoteController(Device connectedDevice) {
            this.connectedDevice = connectedDevice;
        }

        public Device getConnectedDevice() {
            return connectedDevice;
        }
    }
}
