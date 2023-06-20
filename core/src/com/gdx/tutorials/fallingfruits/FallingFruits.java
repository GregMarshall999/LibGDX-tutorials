package com.gdx.tutorials.fallingfruits;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.gdx.tutorials.fallingfruits.banks.SpriteBank.drawSprite;
import static com.gdx.tutorials.fallingfruits.banks.SpriteBank.initBank;

public class FallingFruits extends ApplicationAdapter {
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private ExtendViewport viewport;

    private TextureAtlas textureAtlas;

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);

        textureAtlas = new TextureAtlas("fallingfruits/sprites.txt");

        initBank(textureAtlas);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        drawSprite(batch, "banana", 0, 0);
        drawSprite(batch, "cherries", 100, 100);

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
