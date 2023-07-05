package com.gdx.tutorials.FLGT.game.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.engine.components.*;
import com.gdx.tutorials.FLGT.engine.noise.SimplexNoise;
import com.gdx.tutorials.FLGT.game.FLGTContactListener;
import com.gdx.tutorials.FLGT.game.body.BodyFactory;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;
import static com.gdx.tutorials.FLGT.game.body.material.MaterialType.STONE;

public class LevelFactory {
    private BodyFactory bodyFactory;
    private PooledEngine engine;
    private SimplexNoise noise;
    private TextureRegion floorTex;

    public World world;
    public int currentLevel = 0;

    public LevelFactory(PooledEngine engine, TextureRegion floorTex) {
        this.engine = engine;
        this.floorTex = floorTex;

        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new FLGTContactListener());
        bodyFactory = BodyFactory.getInstance(world);
        noise = new SimplexNoise(512, 0.85f, 1);
    }

    public void generateLevel(int yLevel) {
        float noise1, noise2, noise3, noise4;

        while (yLevel > currentLevel) {
            noise1 = (float) noise.getNoise(1, currentLevel, 0);
            noise2 = (float) noise.getNoise(1, currentLevel, 100);
            noise3 = (float) noise.getNoise(1, currentLevel, 200);
            noise4 = (float) noise.getNoise(1, currentLevel, 300);

            if(noise1 > 0.2f)
                createPlatform(noise2 * 25 + 2, currentLevel * 2);

            if(noise3 > 0.2f)
                createPlatform(noise4 * 25 + 2, currentLevel * 2);

            currentLevel++;
        }
    }

    public void createPlatform(float x, float y) {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        body.body = bodyFactory.makeBoxPolyBody(x, y, 1.5f, 0.2f, STONE, StaticBody);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = floorTex;
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = TypeComponent.SCENERY;
        body.body.setUserData(entity);
        entity.add(body);
        entity.add(texture);
        entity.add(type);
        engine.addEntity(entity);
    }

    public void createFloor(TextureRegion tex){
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        body.body = bodyFactory.makeBoxPolyBody(0, 0, 100, 0.2f, STONE, StaticBody);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = tex;
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = TypeComponent.SCENERY;

        body.body.setUserData(entity);

        entity.add(body);
        entity.add(texture);
        entity.add(type);

        engine.addEntity(entity);
    }

    public void createPlayer(TextureRegion tex, OrthographicCamera camera){
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);

        player.camera = camera;
        body.body = bodyFactory.makeCirclePolyBody(10,1,1, STONE, DynamicBody,true);
        position.position.set(10,1,0);
        texture.region = tex;
        type.type = TypeComponent.PLAYER;
        stateCom.set(StateComponent.STATE_NORMAL);
        body.body.setUserData(entity);

        entity.add(body);
        entity.add(position);
        entity.add(texture);
        entity.add(player);
        entity.add(colComp);
        entity.add(type);
        entity.add(stateCom);

        engine.addEntity(entity);
    }
}
