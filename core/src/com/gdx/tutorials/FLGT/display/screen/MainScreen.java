package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.gdx.tutorials.FLGT.control.PCControls;
import com.gdx.tutorials.FLGT.game.FLGTWorld;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.assets.FLGTAssets;

public class MainScreen extends AbstractFLGTScreen {
    private FLGTWorld world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private PCControls controller;

    private AtlasRegion playerTex;

    private SpriteBatch batch;

    public MainScreen(FLGT context) {
        super(context);

        controller = new PCControls();
        camera = new OrthographicCamera(32, 24);
        world = new FLGTWorld(controller, camera, assetManager);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        TextureAtlas atlas = assetManager.get(FLGTAssets.gameImages);
        playerTex = atlas.findRegion("player");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.logicStep(delta);

        batch.begin();
        batch.draw(playerTex, world.player.getPosition().x-1, world.player.getPosition().y-1, 2, 2);
        batch.end();

        debugRenderer.render(world.getWorld(), camera.combined);
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

    }
}
