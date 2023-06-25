package com.gdx.tutorials.FLGT.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.game.body.BodyFactory;
import com.gdx.tutorials.FLGT.game.body.Dynamic;
import com.gdx.tutorials.FLGT.game.body.Kinematic;
import com.gdx.tutorials.FLGT.game.body.Static;
import com.gdx.tutorials.FLGT.game.body.material.MaterialType;

public class B2dModel {
    private World world;

    private Dynamic bodyD;
    private Static bodyS;
    private Kinematic bodyK;

    private BodyFactory bodyFactory;

    public B2dModel() {
        world = new World(new Vector2(0, -10f), true);
        bodyD = new Dynamic(world);
        bodyS = new Static(world);
        bodyK = new Kinematic(world);

        bodyFactory = BodyFactory.getInstance(world);

        bodyFactory.makeCirclePolyBody(1, 1, 2, MaterialType.RUBBER, BodyDef.BodyType.DynamicBody, false);
        bodyFactory.makeCirclePolyBody(4, 1, 2, MaterialType.STEEL, BodyDef.BodyType.DynamicBody, false);
        bodyFactory.makeCirclePolyBody(-4, 1, 2, MaterialType.STONE, BodyDef.BodyType.DynamicBody, false);
    }

    public World getWorld() {
        return world;
    }

    public void logicStep(float delta) {
        world.step(delta, 3, 3);
    }
}
