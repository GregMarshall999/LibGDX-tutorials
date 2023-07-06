package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.tutorials.FLGT.control.PCControls;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.engine.FLGTUtils;
import com.gdx.tutorials.FLGT.engine.systems.*;
import com.gdx.tutorials.FLGT.game.FLGTAssets;
import com.gdx.tutorials.FLGT.engine.factory.LevelFactory;

public class MainScreen extends AbstractFLGTScreen {
    private OrthographicCamera camera;
    private PCControls controller;
    private SpriteBatch batch;
    private PooledEngine engine;
    private LevelFactory levelFactory;
    private Entity player;

    private Sound ping;
    private Sound boing;
    private TextureAtlas atlas;

    public MainScreen(FLGT applicationContext) {
        super(applicationContext);

        this.applicationContext.assetManager.queueAddAllSounds().finishLoading();

        atlas = this.applicationContext.assetManager.get(FLGTAssets.GAME_IMAGES, TextureAtlas.class);
        ping = this.applicationContext.assetManager.get(FLGTAssets.PING_SOUND, Sound.class);
        boing = this.applicationContext.assetManager.get(FLGTAssets.BOING_SOUND, Sound.class);
        controller = new PCControls();
        engine = new PooledEngine();
        levelFactory = new LevelFactory(engine, atlas.findRegion("player"));

        batch = new SpriteBatch();
        RenderingSystem renderingSystem = new RenderingSystem(batch);
        camera = renderingSystem.getCamera();
        batch.setProjectionMatrix(camera.combined);

        player = levelFactory.createPlayer(atlas.findRegion("player"), camera);

        engine.addSystem(new AnimationSystem());
        engine.addSystem(new PhysicsSystem(levelFactory.world));
        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsDebugSystem(levelFactory.world, renderingSystem.getCamera()));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));
        engine.addSystem(new WallSystem(player));
        engine.addSystem(new WaterFloorSystem(player));
        engine.addSystem(new LevelGenerationSystem(levelFactory));

        int floorWidth = (int) (40*RenderingSystem.PPM);
        int floorHeight = (int) (1*RenderingSystem.PPM);
        TextureRegion floorRegion = FLGTUtils.makeTextureRegion(floorWidth, floorHeight, "11331180");
        levelFactory.createFloor(floorRegion);

        int wFloorWidth = (int) (40*RenderingSystem.PPM);
        int wFloorHeight = (int) (10*RenderingSystem.PPM);
        TextureRegion wFloorRegion = FLGTUtils.makeTextureRegion(wFloorWidth, wFloorHeight, "11113380");
        levelFactory.createWaterFloor(wFloorRegion);

        int wallWidth = (int) (1*RenderingSystem.PPM);
        int wallHeight = (int) (60*RenderingSystem.PPM);
        TextureRegion wallRegion = FLGTUtils.makeTextureRegion(wallWidth, wallHeight, "222222FF");
        levelFactory.createWalls(wallRegion);
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
