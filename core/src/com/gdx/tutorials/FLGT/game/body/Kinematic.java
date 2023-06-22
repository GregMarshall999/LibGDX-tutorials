package com.gdx.tutorials.FLGT.game.body;

import com.badlogic.gdx.physics.box2d.*;

public class Kinematic {
    public Kinematic(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0, -12);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        body.createFixture(shape, 0.0f);

        shape.dispose();

        body.setLinearVelocity(0, 0.75f);
    }
}
