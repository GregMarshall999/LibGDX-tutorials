package com.gdx.tutorials.FLGT.engine.effects;

public enum FLGTEffect {
    SMOKE(0),
    WATER(1),
    FIRE(2);

    private final int id;

    FLGTEffect(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
