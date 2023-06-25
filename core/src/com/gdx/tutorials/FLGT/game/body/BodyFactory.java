package com.gdx.tutorials.FLGT.game.body;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.game.body.material.MaterialType;

public class BodyFactory {
    private World world;

    private static BodyFactory instance;

    private BodyFactory(World world) {
        this.world = world;
    }

    public static BodyFactory getInstance(World world) {
        if(instance == null)
            instance = new BodyFactory(world);

        return instance;
    }

    private static FixtureDef makeFixture(MaterialType material, Shape shape) {
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.shape = shape;
        fixtureDef.density = material.getDensity();
        fixtureDef.friction = material.getFriction();
        fixtureDef.restitution = material.getRestitution();

        return fixtureDef;
    }
}
