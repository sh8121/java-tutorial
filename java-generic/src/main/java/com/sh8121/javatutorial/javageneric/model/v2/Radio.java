package com.sh8121.javatutorial.javageneric.model.v2;

public class Radio extends Electronics {

    private String name;

    public Radio(String manufacturer, String name) {
        super(manufacturer);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
