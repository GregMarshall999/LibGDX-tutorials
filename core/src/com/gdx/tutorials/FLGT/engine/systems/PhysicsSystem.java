package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.gdx.tutorials.FLGT.engine.components.BodyComponent;
import com.gdx.tutorials.FLGT.engine.components.TransformComponent;

public class PhysicsSystem extends IteratingSystem {
    private static final float MAX_STEP_TIME = 1/45f;
    private static float accumulator = 0f;

    private World world;
    private Array<Entity> bodiesQueue;

    private ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);

    public PhysicsSystem(World world) {
        super(Family.all(BodyComponent.class, TransformComponent.class).get());
        this.world = world;
        bodiesQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;

        if(accumulator >= MAX_STEP_TIME) {
            world.step(MAX_STEP_TIME, 6, 2);
            accumulator -= MAX_STEP_TIME;

            TransformComponent transformComponent;
            BodyComponent bodyComponent;
            Vector2 position;
            for(Entity entity : bodiesQueue) {
                transformComponent = transformMapper.get(entity);
                bodyComponent = bodyMapper.get(entity);
                position = bodyComponent.body.getPosition();

                transformComponent.position.x = position.x;
                transformComponent.position.y = position.y;
                transformComponent.rotation = bodyComponent.body.getAngle() * MathUtils.radiansToDegrees;
            }
        }
        bodiesQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodiesQueue.add(entity);
    }
}
