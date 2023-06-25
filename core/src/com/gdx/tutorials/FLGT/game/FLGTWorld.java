package com.gdx.tutorials.FLGT.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.game.body.BodyFactory;
import com.gdx.tutorials.FLGT.game.body.CollisionListener;
import com.gdx.tutorials.FLGT.game.body.type.Dynamic;
import com.gdx.tutorials.FLGT.game.body.type.Kinematic;
import com.gdx.tutorials.FLGT.game.body.type.Static;
import com.gdx.tutorials.FLGT.game.body.material.MaterialType;

public class FLGTWorld {
    private World world;

    private Dynamic bodyD;
    private Static bodyS;
    private Kinematic bodyK;

    private BodyFactory bodyFactory;

    public FLGTWorld() {
        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new CollisionListener(this));

        bodyD = new Dynamic(world);
        bodyS = new Static(world);
        bodyK = new Kinematic(world);

        bodyFactory = BodyFactory.getInstance(world);

        bodyFactory.makeCirclePolyBody(1, 1, 1, MaterialType.RUBBER);
        bodyFactory.makeCirclePolyBody(4, 1, 1, MaterialType.STEEL);
        bodyFactory.makeCirclePolyBody(-4, 1, 1, MaterialType.STONE);
    }

    public World getWorld() {
        return world;
    }

    public void logicStep(float delta) {
        world.step(delta, 3, 3);
    }
}
