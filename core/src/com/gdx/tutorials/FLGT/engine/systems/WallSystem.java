package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.gdx.tutorials.FLGT.engine.components.BodyComponent;
import com.gdx.tutorials.FLGT.engine.components.WallComponent;

public class WallSystem extends IteratingSystem {
    private Entity player;
    private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

    public WallSystem(Entity player) {
        super(Family.all(WallComponent.class).get());
        this.player = player;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float currentyLevel = player.getComponent(BodyComponent.class).body.getPosition().y;
        Body bod = bm.get(entity).body;
        bod.setTransform(bod.getPosition().x, currentyLevel, bod.getAngle());
    }
}
