package com.gdx.tutorials.FLGT.engine.components.constants;

public enum State {
    STATE_NORMAL(0),
    STATE_JUMPING(1),
    STATE_FALLING(2),
    STATE_MOVING(3),
    STATE_HIT(4);

    private final int id;

    State(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
