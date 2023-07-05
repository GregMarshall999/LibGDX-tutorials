package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PlayerComponent implements Component, Poolable {
    public OrthographicCamera camera;

    public boolean isDead = false;
    public boolean onPlatform = false;
    public boolean onSpring = false;

    @Override
    public void reset() {

    }
}
