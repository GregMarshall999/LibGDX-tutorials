package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gdx.tutorials.FLGT.engine.components.CollisionComponent;
import com.gdx.tutorials.FLGT.engine.components.PlayerComponent;
import com.gdx.tutorials.FLGT.engine.components.TypeComponent;

import static com.gdx.tutorials.FLGT.engine.components.TypeComponent.*;

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

        Entity collidedEntity = cc.entity;

        if(collidedEntity != null) {
            TypeComponent type = collidedEntity.getComponent(TypeComponent.class);
            if(type != null) {
                switch (type.type) {
                    case ENEMY -> {
                        PlayerComponent player = playerMapper.get(entity);
                        player.isDead = true;
                        int score = (int) player.camera.position.y;
                        System.out.println("Score = " + score);
                    }
                    case SCENERY -> playerMapper.get(entity).onPlatform = true;
                    case SPRING -> playerMapper.get(entity).onSpring = true;
                    case OTHER -> System.out.println("Player hit other");
                    default -> System.out.println("No matching type found");
                }
                cc.entity = null;
            }
            else
                System.out.println("type == null");
        }
    }
}
