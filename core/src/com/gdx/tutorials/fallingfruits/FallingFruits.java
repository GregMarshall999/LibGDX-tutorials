package com.gdx.tutorials.fallingfruits;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class FallingFruits extends ApplicationAdapter {
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private ExtendViewport viewport;

    private TextureAtlas textureAtlas;
    private Sprite banana;

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);

        textureAtlas = new TextureAtlas("fallingfruits/sprites.txt");
        banana = textureAtlas.createSprite("banana");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        banana.setPosition(0, 0);
        banana.draw(batch);

        banana.setPosition(100, 100);
        banana.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
    }
}
