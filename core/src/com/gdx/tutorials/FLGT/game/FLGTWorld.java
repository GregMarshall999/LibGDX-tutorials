package com.gdx.tutorials.FLGT.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.game.body.BodyFactory;
import com.gdx.tutorials.FLGT.game.body.CollisionListener;
import com.gdx.tutorials.FLGT.game.body.material.MaterialType;
import com.gdx.tutorials.FLGT.game.body.type.Static;

public class FLGTWorld {
    private World world;
    private BodyFactory bodyFactory;

    private Static floor;
    private Body player;
    private Body water;

    public FLGTWorld() {
        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new CollisionListener(this));
        bodyFactory = BodyFactory.getInstance(world);

        floor = new Static(world);

        player = bodyFactory.makeBoxPolyBody(1, 1, 2, 2, MaterialType.RUBBER, BodyDef.BodyType.DynamicBody, false);

        water = bodyFactory.makeBoxPolyBody(1, -8, 40, 4, MaterialType.RUBBER, BodyDef.BodyType.StaticBody, false);
        bodyFactory.makeAllFixturesSensors(water);
    }

    public World getWorld() {
        return world;
    }

    public void logicStep(float delta) {
        world.step(delta, 3, 3);
    }
}
