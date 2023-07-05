package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.gdx.tutorials.FLGT.engine.components.BodyComponent;

public class WaterFloorSystem extends IteratingSystem {
    private Entity player;
    private ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);

    public WaterFloorSystem(Entity player) {
        super(Family.all(BodyComponent.class).get());
        this.player = player;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float currentLevel = player.getComponent(BodyComponent.class).body.getPosition().y;
        Body body = bodyMapper.get(entity).body;
        float speed = (currentLevel/300);

        speed = speed > 1 ? 1 : speed;
        body.setTransform(body.getPosition().x, body.getPosition().y + speed, body.getAngle());
    }
}
