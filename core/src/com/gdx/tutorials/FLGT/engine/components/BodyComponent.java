package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BodyComponent implements Component, Poolable {
    public Body body;
    public boolean isDead = false;

    @Override
    public void reset() {
        isDead = false;
    }
}
