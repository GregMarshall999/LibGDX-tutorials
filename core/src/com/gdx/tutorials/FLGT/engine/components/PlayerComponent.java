package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PlayerComponent implements Component, Poolable {
    public OrthographicCamera camera = null;
    public boolean onPlatform = false;
    public boolean onSpring = false;
    public boolean isDead = false;
    public float shootDelay = 0.5f;
    public float timeSinceLastShot = 0f;

    @Override
    public void reset() {
        camera = null;
        onPlatform = false;
        onSpring = false;
        isDead = false;
        shootDelay = 0.5f;
        timeSinceLastShot = 0f;
    }
}
