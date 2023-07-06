package com.gdx.tutorials.FLGT.engine.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gdx.tutorials.FLGT.engine.components.*;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;
import static com.gdx.tutorials.FLGT.engine.components.constants.State.STATE_NORMAL;
import static com.gdx.tutorials.FLGT.engine.components.constants.Type.PLAYER;
import static com.gdx.tutorials.FLGT.engine.components.constants.Type.SCENERY;

public abstract class EntityFactory {
    public static void createPlayer(PooledEngine engine, BodyFactory bodyFactory, TextureAtlas atlas) {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        CollisionComponent collider = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);

        body.body = bodyFactory.makeCirclePolyBody(10, 10, 1, MaterialType.STONE, DynamicBody, true);
        position.position.set(10, 10, 0);
        texture.region = atlas.findRegion("player");
        type.type = PLAYER;
        state.set(STATE_NORMAL);
        body.body.setUserData(entity);

        entity.add(body);
        entity.add(position);
        entity.add(texture);
        entity.add(player);
        entity.add(collider);
        entity.add(type);
        entity.add(state);

        engine.addEntity(entity);
    }

    public static void createPlatform(PooledEngine engine, BodyFactory bodyFactory, TextureAtlas atlas, float x, float y) {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        body.body = bodyFactory.makeBoxPolyBody(x, y, 3, 0.2f, MaterialType.STONE, StaticBody);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = atlas.findRegion("player");
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = SCENERY;
        body.body.setUserData(entity);

        entity.add(body);
        entity.add(texture);
        entity.add(type);

        engine.addEntity(entity);
    }

    public static void createFloor(PooledEngine engine, BodyFactory bodyFactory, TextureAtlas atlas){
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        body.body = bodyFactory.makeBoxPolyBody(0, 0, 100, 0.2f, MaterialType.STONE, StaticBody);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = atlas.findRegion("player");
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = SCENERY;

        body.body.setUserData(entity);

        entity.add(body);
        entity.add(texture);
        entity.add(type);

        engine.addEntity(entity);
    }
}
