package com.gdx.tutorials.FLGT.game.body;

import com.badlogic.gdx.physics.box2d.*;
import com.gdx.tutorials.FLGT.game.body.material.MaterialType;

public final class BodyFactory {
    private final World world;

    private static BodyFactory instance;

    private BodyFactory(World world) {
        this.world = world;
    }

    public static BodyFactory getInstance(World world) {
        if(instance == null)
            instance = new BodyFactory(world);

        return instance;
    }

    public Body makeCirclePolyBody(float posX, float posY, float radius, MaterialType material, BodyDef.BodyType bodyType, boolean fixedRotation) {
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posX;
        boxBodyDef.position.y = posY;
        boxBodyDef.fixedRotation = fixedRotation;

        Body boxBody = world.createBody(boxBodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        boxBody.createFixture(makeFixture(material, circleShape));
        circleShape.dispose();

        return boxBody;
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
