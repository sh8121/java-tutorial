package com.sh8121.javatutorial.javageneric.v0_withoutgeneric;

public class RemoteController {

    private Object connectedDevice;

    public RemoteController(Object connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    public Object getConnectedDevice() {
        return connectedDevice;
    }
}
