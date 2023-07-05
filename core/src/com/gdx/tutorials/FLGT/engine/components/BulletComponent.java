package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.gdx.tutorials.FLGT.engine.components.constants.BulletOwner;

import static com.gdx.tutorials.FLGT.engine.components.constants.BulletOwner.NONE;

public class BulletComponent implements Component, Poolable {
    public Entity particleEffect;
    public float xVel = 0;
    public float yVel = 0;
    public boolean isDead = false;
    public BulletOwner owner = NONE;

    @Override
    public void reset() {
        owner = NONE;
        xVel = 0;
        yVel = 0;
        isDead = false;
        particleEffect = null;
    }
}
