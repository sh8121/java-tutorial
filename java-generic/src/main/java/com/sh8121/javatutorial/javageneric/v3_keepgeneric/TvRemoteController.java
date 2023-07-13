package com.sh8121.javatutorial.javageneric.v3_keepgeneric;

import com.sh8121.javatutorial.javageneric.model.v0.Tv;

public class TvRemoteController extends RemoteController<Tv> {

    public TvRemoteController(Tv connectedDevice) {
        super(connectedDevice);
    }
}
