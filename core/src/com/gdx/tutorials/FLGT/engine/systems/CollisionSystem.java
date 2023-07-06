package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gdx.tutorials.FLGT.engine.components.*;
import com.gdx.tutorials.FLGT.engine.components.constants.BulletOwner;
import com.gdx.tutorials.FLGT.engine.components.constants.Type;

import java.util.Objects;

import static com.gdx.tutorials.FLGT.engine.components.constants.Type.ENEMY;
import static com.gdx.tutorials.FLGT.engine.components.constants.Type.PLAYER;

public class CollisionSystem extends IteratingSystem {
    ComponentMapper<CollisionComponent> collisionMapper;
    ComponentMapper<PlayerComponent> playerMapper;

    public CollisionSystem() {
        super(Family.all(CollisionComponent.class, PlayerComponent.class).get());

        collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        playerMapper = ComponentMapper.getFor(PlayerComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent cc = collisionMapper.get(entity);
        Entity collidedEntity = cc.collisionEntity;

        TypeComponent thisType = entity.getComponent(TypeComponent.class);

        if(thisType.type == PLAYER){
            PlayerComponent pl = playerMapper.get(entity);
            if(collidedEntity != null){
                TypeComponent type = collidedEntity.getComponent(TypeComponent.class);
                if(type != null){
                    switch(type.type){
                        case ENEMY -> pl.isDead = true;
                        case SCENERY -> playerMapper.get(entity).onPlatform = true;
                        case SPRING -> playerMapper.get(entity).onSpring = true;
                        case BULLET -> {
                            // TODO add mask so player can't hit themselves
                            BulletComponent bullet = Mapper.bulletCom.get(collidedEntity);
                            if(bullet.owner != BulletOwner.PLAYER)
                                pl.isDead = true;
                        }
                    }
                    cc.collisionEntity = null;
                }
            }
        }else if(thisType.type == ENEMY){
            if(collidedEntity != null){
                TypeComponent type = collidedEntity.getComponent(TypeComponent.class);
                if(type != null){
                    if (Objects.requireNonNull(type.type) == Type.BULLET) {
                        EnemyComponent enemy = Mapper.enemyCom.get(entity);
                        BulletComponent bullet = Mapper.bulletCom.get(collidedEntity);
                        if (bullet.owner != BulletOwner.ENEMY) {
                            bullet.isDead = true;
                            enemy.isDead = true;
                        }
                    }
                    cc.collisionEntity = null;
                }
            }
        }else{
            cc.collisionEntity = null;
        }
    }
}
