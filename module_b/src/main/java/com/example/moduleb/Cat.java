package com.example.moduleb;

public class Cat {
    private final String name;
    private final int cuteness;
    private final int viciousness;

    public Cat(String name, int cuteness, int viciousness) {
        this.name = name;
        this.cuteness = cuteness;
        this.viciousness = viciousness;
    }

    public String name() {
        return name;
    }

    public int cuteness() {
        return cuteness;
    }

    public int viciousness() {
        return viciousness;
    }
}
