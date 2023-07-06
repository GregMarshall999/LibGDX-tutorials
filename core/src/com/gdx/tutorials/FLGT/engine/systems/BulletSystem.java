package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gdx.tutorials.FLGT.engine.components.BodyComponent;
import com.gdx.tutorials.FLGT.engine.components.BulletComponent;
import com.gdx.tutorials.FLGT.engine.components.Mapper;

public class BulletSystem extends IteratingSystem {
    private Entity player;

    public BulletSystem(Entity player){
        super(Family.all(BulletComponent.class).get());
        this.player = player;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent b2body = Mapper.bodyCom.get(entity);
        BulletComponent bullet = Mapper.bulletCom.get(entity);

        b2body.body.setLinearVelocity(bullet.xVel, bullet.yVel);

        BodyComponent playerBodyComp = Mapper.bodyCom.get(player);
        float px = playerBodyComp.body.getPosition().x;
        float py = playerBodyComp.body.getPosition().y;

        float bx = b2body.body.getPosition().x;
        float by = b2body.body.getPosition().y;

        if(bx - px > 30 || by - py > 30){
            bullet.isDead = true;
        }

        if(bullet.isDead){
            Mapper.peCom.get(bullet.particleEffect).isDead = true;
            b2body.isDead = true;
        }
    }
}
