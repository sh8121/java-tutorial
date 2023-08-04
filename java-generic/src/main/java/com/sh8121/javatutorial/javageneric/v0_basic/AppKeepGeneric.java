package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v0.Radio;
import com.sh8121.javatutorial.javageneric.model.v0.Tv;
import java.lang.reflect.ParameterizedType;

public class AppKeepGeneric {

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

    static class RemoteController<Device> {

        private Device connectedDevice;

        public RemoteController(Device connectedDevice) {
            this.connectedDevice = connectedDevice;
        }

        public Device getConnectedDevice() {
            return connectedDevice;
        }
    }

    static class TvRemoteController extends RemoteController<Tv> {

        public TvRemoteController(Tv connectedDevice) {
            super(connectedDevice);
        }
    }

    static class RadioRemoteController {

        private RemoteController<Radio> remoteController;

        public RadioRemoteController(Radio connectedDevice) {
            remoteController = new RemoteController<>(connectedDevice);
        }

        public Radio getConnectedDevice() {
            return remoteController.getConnectedDevice();
        }
    }
}
