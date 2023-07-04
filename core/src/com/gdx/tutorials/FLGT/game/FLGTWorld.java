package com.gdx.tutorials.FLGT.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.control.PCControls;
import com.gdx.tutorials.FLGT.game.body.BodyFactory;
import com.gdx.tutorials.FLGT.game.body.BodyUserData;
import com.gdx.tutorials.FLGT.game.body.CollisionListener;
import com.gdx.tutorials.FLGT.game.body.material.MaterialType;
import com.gdx.tutorials.FLGT.game.body.type.Static;
import com.gdx.tutorials.FLGT.assets.FLGTAssets;

public class FLGTWorld {
    private World world;
    private BodyFactory bodyFactory;
    private PCControls controller;
    private OrthographicCamera camera;
    private FLGTAssets assets;

    private Sound ping;
    private Sound boing;

    private Music music;

    private Static floor;
    private Body water;

    public Body player;

    private boolean isSwimming = false;

    public FLGTWorld(PCControls controller, OrthographicCamera camera, FLGTAssets assets) {
        this.controller = controller;
        this.camera = camera;
        this.assets = assets;

        assets.queueAddAllMusic();
        assets.queueAddAllSounds();
        assets.finishLoading();

        ping = assets.get(assets.pingSound, Sound.class);
        boing = assets.get(assets.boingSound, Sound.class);

        music = assets.get(assets.music, Music.class);
        music.play();

        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new CollisionListener(this));
        bodyFactory = BodyFactory.getInstance(world);

        floor = new Static(world);

        player = bodyFactory.makeBoxPolyBody(1, 1, 2, 2, MaterialType.RUBBER, BodyDef.BodyType.DynamicBody, false);

        water = bodyFactory.makeBoxPolyBody(1, -4.5f, 40, 9, MaterialType.RUBBER, BodyDef.BodyType.StaticBody, false);
        bodyFactory.makeAllFixturesSensors(water);
        water.setUserData(BodyUserData.SEA);
    }

    public World getWorld() {
        return world;
    }

    public void logicStep(float delta) {
        if(PCControls.mouse1Down && pointIntersectsBody(player, PCControls.mouseLocation))
            System.out.println("Player was clicked");

        if(PCControls.left)
            player.applyForceToCenter(-10, 0, true);
        else if(PCControls.right)
            player.applyForceToCenter(10, 0, true);
        else if(PCControls.up)
            player.applyForceToCenter(0, 10, true);
        else if(PCControls.down)
            player.applyForceToCenter(0, -10, true);

        if(isSwimming)
            player.applyForceToCenter(0, 50, true);

        world.step(delta, 3, 3);
    }

    public void setSwimming(boolean swimming) {
        isSwimming = swimming;
    }

    public boolean pointIntersectsBody(Body body, Vector2 mouseLocation) {
        Vector3 mousePos = new Vector3(mouseLocation, 0);
        camera.unproject(mousePos);

        return body.getFixtureList().first().testPoint(mousePos.x, mousePos.y);
    }

    public void playSound(FLGTSound sound) {
        switch (sound) {
            case PING -> ping.play();
            case BOING -> boing.play();
        }
    }
}
