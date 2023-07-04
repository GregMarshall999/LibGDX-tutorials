package com.gdx.tutorials.FLGT.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gdx.tutorials.FLGT.components.CollisionComponent;
import com.gdx.tutorials.FLGT.components.PlayerComponent;
import com.gdx.tutorials.FLGT.components.TypeComponent;

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
                    case TypeComponent.ENEMY -> System.out.println("Player hit enemy");
                    case TypeComponent.SCENERY -> System.out.println("Player hit scenery");
                    case TypeComponent.OTHER -> System.out.println("Player hit other");
                }
                cc.entity = null;
            }
        }
    }
}
