package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.gdx.tutorials.FLGT.engine.components.constants.EnemyType;

import static com.gdx.tutorials.FLGT.engine.components.constants.EnemyType.DROPLET;

public class EnemyComponent implements Component, Poolable {
    public boolean isDead = false;
    public float xPosCenter = -1;
    public boolean isGoingLeft = false;
    public float shootDelay = 1.5f;
    public float timeSinceLastShot = 0f;
    public EnemyType enemyType = DROPLET;

    @Override
    public void reset() {
        shootDelay = 0.5f;
        timeSinceLastShot = 0f;
        enemyType = DROPLET;
        isDead = false;
        xPosCenter = -1;
        isGoingLeft = false;
    }
}
