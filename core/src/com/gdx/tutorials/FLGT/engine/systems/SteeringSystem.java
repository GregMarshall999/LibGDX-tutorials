package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.GdxAI;
import com.gdx.tutorials.FLGT.engine.components.Mapper;
import com.gdx.tutorials.FLGT.engine.components.SteeringComponent;

public class SteeringSystem extends IteratingSystem {
    public SteeringSystem() {
        super(Family.all(SteeringComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        GdxAI.getTimepiece().update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SteeringComponent steer = Mapper.sCom.get(entity);
        steer.update(deltaTime);
    }
}