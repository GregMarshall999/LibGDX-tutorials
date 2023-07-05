package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.gdx.tutorials.FLGT.engine.components.BodyComponent;
import com.gdx.tutorials.FLGT.engine.components.PlayerComponent;
import com.gdx.tutorials.FLGT.engine.components.StateComponent;
import com.gdx.tutorials.FLGT.control.PCControls;

public class PlayerControlSystem extends IteratingSystem {
    ComponentMapper<PlayerComponent> playerMapper;
    ComponentMapper<BodyComponent> bodyMapper;
    ComponentMapper<StateComponent> stateMapper;
    PCControls controller;

    public PlayerControlSystem(PCControls controller) {
        super(Family.all(PlayerComponent.class).get());
        this.controller = controller;

        playerMapper = ComponentMapper.getFor(PlayerComponent.class);
        bodyMapper = ComponentMapper.getFor(BodyComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent body = bodyMapper.get(entity);
        StateComponent state = stateMapper.get(entity);

        if(body.body.getLinearVelocity().y > 0)
            state.set(StateComponent.STATE_FALLING);

        if(body.body.getLinearVelocity().y == 0) {
            if(state.get() == StateComponent.STATE_FALLING)
                state.set(StateComponent.STATE_NORMAL);
            if(body.body.getLinearVelocity().y < 0)
                state.set(StateComponent.STATE_MOVING);
        }

        if(PCControls.left)
            body.body.setLinearVelocity(MathUtils.lerp(body.body.getLinearVelocity().x, -5f, 0.2f), body.body.getLinearVelocity().y);

        if(PCControls.right)
            body.body.setLinearVelocity(MathUtils.lerp(body.body.getLinearVelocity().x, 5f, 0.2f), body.body.getLinearVelocity().y);

        if(!PCControls.left && !PCControls.right)
            body.body.setLinearVelocity(MathUtils.lerp(body.body.getLinearVelocity().x, 0, 0.1f), body.body.getLinearVelocity().y);

        if(PCControls.up && (state.get() == StateComponent.STATE_NORMAL || state.get() == StateComponent.STATE_MOVING)) {
            body.body.applyLinearImpulse(0, 75f, body.body.getWorldCenter().x, body.body.getWorldCenter().y, true);
            state.set(StateComponent.STATE_JUMPING);
        }
    }
}
