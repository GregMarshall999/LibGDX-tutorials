package com.gdx.tutorials.FLGT.display;

public enum FLGTScreen {
    MENU(0),
    PREFERENCE(1),
    APPLICATION(2),
    ENDGAME(3);

    private final int id;

    FLGTScreen(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
