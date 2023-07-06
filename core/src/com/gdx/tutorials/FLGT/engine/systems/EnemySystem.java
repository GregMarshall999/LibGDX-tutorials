package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.gdx.tutorials.FLGT.engine.FLGTUtils;
import com.gdx.tutorials.FLGT.engine.ai.SteeringPresets;
import com.gdx.tutorials.FLGT.engine.components.BodyComponent;
import com.gdx.tutorials.FLGT.engine.components.EnemyComponent;
import com.gdx.tutorials.FLGT.engine.components.Mapper;
import com.gdx.tutorials.FLGT.engine.components.SteeringComponent;
import com.gdx.tutorials.FLGT.engine.factory.LevelFactory;

import static com.gdx.tutorials.FLGT.engine.components.constants.BulletOwner.ENEMY;
import static com.gdx.tutorials.FLGT.engine.components.constants.EnemyType.*;
import static com.gdx.tutorials.FLGT.engine.components.constants.SteeringState.*;

public class EnemySystem extends IteratingSystem {
    private ComponentMapper<EnemyComponent> em;
    private ComponentMapper<BodyComponent> bodm;
    private LevelFactory levelFactory;
    private Entity player;

    public EnemySystem(LevelFactory lvlf){
        super(Family.all(EnemyComponent.class).get());
        em = ComponentMapper.getFor(EnemyComponent.class);
        bodm = ComponentMapper.getFor(BodyComponent.class);
        levelFactory = lvlf;
        player = levelFactory.player;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        EnemyComponent enemyCom = em.get(entity);
        BodyComponent bodyCom = bodm.get(entity);

        if(enemyCom.enemyType == DROPLET){
            float distFromOrig = Math.abs(enemyCom.xPosCenter - bodyCom.body.getPosition().x);

            enemyCom.isGoingLeft = (distFromOrig > 1)? !enemyCom.isGoingLeft:enemyCom.isGoingLeft;

            float speed = enemyCom.isGoingLeft?-0.01f:0.01f;

            bodyCom.body.setTransform(bodyCom.body.getPosition().x + speed,
                    bodyCom.body.getPosition().y,
                    bodyCom.body.getAngle());
        }else if(enemyCom.enemyType == CLOUD){
            BodyComponent b2Player = Mapper.bodyCom.get(player);
            BodyComponent b2Enemy = Mapper.bodyCom.get(entity);

            float distance = b2Player.body.getPosition().dst(b2Enemy.body.getPosition());
            SteeringComponent scom = Mapper.sCom.get(entity);
            if(distance < 3 && scom.currentMode != FLEE){
                scom.steeringBehavior = SteeringPresets.getFlee(Mapper.sCom.get(entity),Mapper.sCom.get(player));
                scom.currentMode = FLEE;
            }else if(distance > 3 && distance < 10 && scom.currentMode != ARRIVE){
                scom.steeringBehavior = SteeringPresets.getArrive(Mapper.sCom.get(entity),Mapper.sCom.get(player));
                scom.currentMode = ARRIVE;
            }else if(distance > 15 && scom.currentMode != WANDER){
                scom.steeringBehavior  = SteeringPresets.getWander(Mapper.sCom.get(entity));
                scom.currentMode = WANDER;
            }

            if(scom.currentMode == ARRIVE){
                if(enemyCom.timeSinceLastShot >= enemyCom.shootDelay){
                    Vector2 aim = FLGTUtils.aimTo(bodyCom.body.getPosition(), b2Player.body.getPosition());
                    aim.scl(3);
                    levelFactory.createBullet(bodyCom.body.getPosition().x,
                            bodyCom.body.getPosition().y,
                            aim.x,
                            aim.y,
                            ENEMY);
                    enemyCom.timeSinceLastShot = 0;
                }
            }
        }

        enemyCom.timeSinceLastShot += deltaTime;

        if(enemyCom.isDead){
            bodyCom.isDead =true;
        }
    }
}
