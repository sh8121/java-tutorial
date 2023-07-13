package com.sh8121.javatutorial.javageneric.v3_keepgeneric;

import com.sh8121.javatutorial.javageneric.model.v0.Radio;

public class RadioRemoteController {

    private RemoteController<Radio> remoteController;

    public RadioRemoteController(Radio connectedDevice) {
        remoteController = new RemoteController<>(connectedDevice);
    }

    public Radio getConnectedDevice() {
        return remoteController.getConnectedDevice();
    }
}
