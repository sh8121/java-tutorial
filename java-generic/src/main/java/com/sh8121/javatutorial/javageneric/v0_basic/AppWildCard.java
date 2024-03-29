package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v1.Radio;
import com.sh8121.javatutorial.javageneric.model.v1.Tv;

public class AppWildCard {

    public static void main(String[] args) {
        Tv tv = new Tv("티비");
        Radio radio = new Radio("라디오");

        RemoteController<?> tvRemoteController = new RemoteController<Tv>(tv);
        RemoteController<?> radioRemoteController = new RemoteController<Radio>(radio);
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
