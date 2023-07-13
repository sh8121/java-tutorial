package com.sh8121.javatutorial.javageneric.v5_wildcard;

public class RemoteController<Device> {

    private Device connectedDevice;

    public RemoteController(Device connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    public Device getConnectedDevice() {
        return connectedDevice;
    }
}
