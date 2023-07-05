package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gdx.tutorials.FLGT.control.PCControls;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.engine.systems.*;
import com.gdx.tutorials.FLGT.game.FLGTAssets;
import com.gdx.tutorials.FLGT.game.factory.LevelFactory;

public class MainScreen extends AbstractFLGTScreen {
    private OrthographicCamera camera;
    private PCControls controller;
    private SpriteBatch batch;
    private PooledEngine engine;
    private LevelFactory levelFactory;

    private Sound ping;
    private Sound boing;
    private TextureAtlas atlas;

    public MainScreen(FLGT applicationContext) {
        super(applicationContext);

        this.applicationContext.assetManager.queueAddAllSounds().finishLoading();

        atlas = this.applicationContext.assetManager.get(FLGTAssets.gameImages, TextureAtlas.class);
        ping = this.applicationContext.assetManager.get(FLGTAssets.pingSound, Sound.class);
        boing = this.applicationContext.assetManager.get(FLGTAssets.boingSound, Sound.class);
        controller = new PCControls();
        engine = new PooledEngine();
        levelFactory = new LevelFactory(engine, atlas.findRegion("player"));

        batch = new SpriteBatch();
        RenderingSystem renderingSystem = new RenderingSystem(batch);
        camera = renderingSystem.getCamera();
        batch.setProjectionMatrix(camera.combined);

        engine.addSystem(new AnimationSystem());
        engine.addSystem(new PhysicsSystem(levelFactory.world));
        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsDebugSystem(levelFactory.world, renderingSystem.getCamera()));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));
        engine.addSystem(new LevelGenerationSystem(levelFactory));

        levelFactory.createPlayer(atlas.findRegion("player"), camera);
        levelFactory.createFloor(atlas.findRegion("player"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);
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
        batch.dispose();
        ping.dispose();
        boing.dispose();
        atlas.dispose();
    }
}
