package com.sh8121.javatutorial.javageneric.v0;

public class RemoteController {

    private Object connectedDevice;

    public RemoteController(Object connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    public Object getConnectedDevice() {
        return connectedDevice;
    }
}
