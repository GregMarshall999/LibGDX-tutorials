package com.gdx.tutorials.FLGT.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.game.body.Dynamic;
import com.gdx.tutorials.FLGT.game.body.Kinematic;
import com.gdx.tutorials.FLGT.game.body.Static;

public class B2dModel {
    private World world;

    private Dynamic bodyD;
    private Static bodyS;
    private Kinematic bodyK;

    public B2dModel() {
        world = new World(new Vector2(0, -10f), true);
        bodyD = new Dynamic(world);
        bodyS = new Static(world);
        bodyK = new Kinematic(world);
    }

    public World getWorld() {
        return world;
    }

    public void logicStep(float delta) {
        world.step(delta, 3, 3);
    }
}
