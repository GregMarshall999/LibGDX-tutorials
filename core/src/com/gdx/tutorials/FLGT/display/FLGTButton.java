package com.gdx.tutorials.FLGT.display;

public enum FLGTButton {
    NEW_GAME("New Game"),
    PREFERENCES("Preferences"),
    EXIT("Exit");

    private final String name;

    FLGTButton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
