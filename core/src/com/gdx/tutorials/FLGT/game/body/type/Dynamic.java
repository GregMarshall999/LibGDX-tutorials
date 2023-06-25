package com.gdx.tutorials.FLGT.game.body.type;

import com.badlogic.gdx.physics.box2d.*;

public class Dynamic {
    public Dynamic(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        body.createFixture(shape, 0.0f);

        shape.dispose();
    }
}
