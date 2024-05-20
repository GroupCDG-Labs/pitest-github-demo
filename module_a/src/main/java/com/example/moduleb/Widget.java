package com.example.moduleb;

public class Widget {

    private final String name;

    public Widget(String name) {
        System.out.println("Can't kill me");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
