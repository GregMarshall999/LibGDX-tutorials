package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gdx.tutorials.FLGT.control.PCControls;
import com.gdx.tutorials.FLGT.engine.FLGTUtils;
import com.gdx.tutorials.FLGT.engine.components.BodyComponent;
import com.gdx.tutorials.FLGT.engine.components.PlayerComponent;
import com.gdx.tutorials.FLGT.engine.components.StateComponent;
import com.gdx.tutorials.FLGT.engine.effects.FLGTEffect;
import com.gdx.tutorials.FLGT.engine.factory.LevelFactory;

import static com.gdx.tutorials.FLGT.engine.components.constants.BulletOwner.PLAYER;
import static com.gdx.tutorials.FLGT.engine.components.constants.State.*;

public class PlayerControlSystem extends IteratingSystem {
    private LevelFactory lvlFactory;
    ComponentMapper<PlayerComponent> pm;
    ComponentMapper<BodyComponent> bodm;
    ComponentMapper<StateComponent> sm;
    PCControls controller;

    public PlayerControlSystem(PCControls keyCon, LevelFactory lvlf) {
        super(Family.all(PlayerComponent.class).get());
        controller = keyCon;
        lvlFactory = lvlf;
        pm = ComponentMapper.getFor(PlayerComponent.class);
        bodm = ComponentMapper.getFor(BodyComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent b2body = bodm.get(entity);
        StateComponent state = sm.get(entity);
        PlayerComponent player = pm.get(entity);

        System.out.println(state.get());
        player.camera.position.y = b2body.body.getPosition().y;


        if(b2body.body.getLinearVelocity().y > 0 && state.get() != STATE_FALLING.getId())
            state.set(STATE_FALLING);

        if(b2body.body.getLinearVelocity().y == 0){
            if(state.get() == STATE_FALLING.getId())
                state.set(STATE_NORMAL);

            if(b2body.body.getLinearVelocity().x != 0 && state.get() != STATE_MOVING.getId())
                state.set(STATE_MOVING);
        }

        if(player.onSpring){
            lvlFactory.makeParticleEffect(FLGTEffect.SMOKE, b2body.body.getPosition().x, b2body.body.getPosition().y);
            b2body.body.setTransform(b2body.body.getPosition().x, b2body.body.getPosition().y+ 10, b2body.body.getAngle());
            player.onSpring = false;
        }


        if(controller.left){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, -7f, 0.2f),b2body.body.getLinearVelocity().y);
        }
        if(controller.right){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 7f, 0.2f),b2body.body.getLinearVelocity().y);
        }

        if(!controller.left && ! controller.right){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 0, 0.1f),b2body.body.getLinearVelocity().y);
        }

        if(controller.up &&
                (state.get() == STATE_NORMAL.getId() || state.get() == STATE_MOVING.getId())){
            b2body.body.applyLinearImpulse(0, 12f * b2body.body.getMass() , b2body.body.getWorldCenter().x,b2body.body.getWorldCenter().y, true);
            state.set(STATE_JUMPING);
            player.onPlatform = false;
            player.onSpring = false;
        }

        if(controller.down){
            b2body.body.applyLinearImpulse(0, -5f, b2body.body.getWorldCenter().x,b2body.body.getWorldCenter().y, true);
        }

        if(player.timeSinceLastShot > 0){
            player.timeSinceLastShot -= deltaTime;
        }

        if(PCControls.mouse1Down){
            if(player.timeSinceLastShot <=0){
                Vector3 mousePos = new Vector3(PCControls.mouseLocation.x, PCControls.mouseLocation.y,0);
                player.camera.unproject(mousePos);

                Vector2 aim = FLGTUtils.aimTo(b2body.body.getPosition(), mousePos);
                aim.scl(7);
                lvlFactory.createBullet(b2body.body.getPosition().x,
                        b2body.body.getPosition().y,
                        aim.x,
                        aim.y,
                        PLAYER);
                player.timeSinceLastShot = player.shootDelay;
            }
        }
    }
}
