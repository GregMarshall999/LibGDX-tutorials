package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.gdx.tutorials.FLGT.game.FLGTWorld;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;

public class MainScreen extends AbstractFLGTScreen {
    private FLGTWorld model;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    public MainScreen(FLGT context) {
        super(context);

        model = new FLGTWorld();
        camera = new OrthographicCamera(32, 24);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        model.logicStep(delta);
        debugRenderer.render(model.getWorld(), camera.combined);
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
