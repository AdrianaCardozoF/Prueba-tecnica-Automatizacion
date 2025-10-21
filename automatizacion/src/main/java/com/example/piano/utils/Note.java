package com.example.piano.utils;

public enum Note {
    DO("do"), RE("re"), MI("mi"), FA("fa"), SOL("sol"), LA("la"), SI("si");

    private final String name;

    Note(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}