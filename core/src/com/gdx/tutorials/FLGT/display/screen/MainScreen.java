package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.control.PCControls;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.assets.FLGTAssets;
import com.gdx.tutorials.FLGT.game.EntityFactory;
import com.gdx.tutorials.FLGT.game.body.BodyFactory;
import com.gdx.tutorials.FLGT.game.body.FLGTContactListener;
import com.gdx.tutorials.FLGT.systems.*;

public class MainScreen extends AbstractFLGTScreen {
    private PCControls controller;
    private World world;
    private BodyFactory bodyFactory;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private TextureAtlas atlas;
    private Sound ping;
    private Sound boing;

    private PooledEngine engine;

    public MainScreen(FLGT applicationContext) {
        super(applicationContext);

        controller = new PCControls();
        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new FLGTContactListener());
        bodyFactory = BodyFactory.getInstance(world);
        batch = new SpriteBatch();
        RenderingSystem renderingSystem = new RenderingSystem(batch);
        camera = renderingSystem.getCamera();
        batch.setProjectionMatrix(camera.combined);

        this.applicationContext.assetManager.queueAddAllSounds().finishLoading();
        atlas = this.applicationContext.assetManager.get(FLGTAssets.gameImages, TextureAtlas.class);
        ping = this.applicationContext.assetManager.get(FLGTAssets.pingSound, Sound.class);
        boing = this.applicationContext.assetManager.get(FLGTAssets.boingSound, Sound.class);

        engine = new PooledEngine();

        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));

        EntityFactory.createPlayer(engine, bodyFactory, atlas);

        EntityFactory.createPlatform(engine, bodyFactory, atlas, 2, 2);
        EntityFactory.createPlatform(engine, bodyFactory, atlas, 2, 7);
        EntityFactory.createPlatform(engine, bodyFactory, atlas, 7, 2);
        EntityFactory.createPlatform(engine, bodyFactory, atlas, 7, 7);

        EntityFactory.createFloor(engine, bodyFactory, atlas);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        batch.dispose();
        atlas.dispose();
        ping.dispose();
        boing.dispose();
    }
}
