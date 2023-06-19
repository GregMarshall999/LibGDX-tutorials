package com.gdx.tutorials.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Breakout extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    private int x = 50, y = 50;
    private int xSpeed = 5;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        x += xSpeed;
        if(x > Gdx.graphics.getWidth())
            xSpeed = -5;
        if(x < 0)
            xSpeed = 5;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, 50);
        shapeRenderer.end();
    }
}
