package com.sh8121.javatutorial.javageneric.v6_wildcard_extends_super;

public class RemoteController<Device> {

    private Device connectedDevice;

    public RemoteController(Device connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    public Device getConnectedDevice() {
        return connectedDevice;
    }
}