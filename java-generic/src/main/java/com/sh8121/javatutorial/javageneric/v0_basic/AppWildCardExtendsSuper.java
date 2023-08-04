package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v1.Electronics;
import com.sh8121.javatutorial.javageneric.model.v1.Radio;
import com.sh8121.javatutorial.javageneric.model.v1.Tv;

public class AppWildCardExtendsSuper {

    public static void main(String[] args) {
        Tv tv = new Tv("티비");
        Radio radio = new Radio("라디오");

        RemoteController<? extends Electronics> tvRemoteController = new RemoteController<Tv>(tv);
        RemoteController<? extends Electronics> radioRemoteController = new RemoteController<Radio>(radio);

        Electronics electronics = new Electronics();
        RemoteController<? super Tv> remoteController = new RemoteController<Electronics>(electronics);
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
