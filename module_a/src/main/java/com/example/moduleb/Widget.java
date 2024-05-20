package com.example.moduleb;

public class Widget {

    private final String name;

    public Widget(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("Can't kill me");
        return name;
    }
}
