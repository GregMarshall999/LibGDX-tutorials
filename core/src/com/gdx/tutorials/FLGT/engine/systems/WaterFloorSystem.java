package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.gdx.tutorials.FLGT.engine.components.BodyComponent;
import com.gdx.tutorials.FLGT.engine.components.WaterFloorComponent;

public class WaterFloorSystem extends IteratingSystem {
    private Entity player;
    private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

    public WaterFloorSystem(Entity player) {
        super(Family.all(WaterFloorComponent.class).get());
        this.player = player;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float currentyLevel = player.getComponent(BodyComponent.class).body.getPosition().y;
        Body bod = bm.get(entity).body;

        float speed = (currentyLevel / 1000);

        speed = speed>0.25f?0.25f:speed;

        if(bod.getPosition().y < currentyLevel - 50){
            bod.setTransform(bod.getPosition().x, currentyLevel - 50, bod.getAngle());
        }

        bod.setTransform(bod.getPosition().x, bod.getPosition().y+speed, bod.getAngle());
    }
}
