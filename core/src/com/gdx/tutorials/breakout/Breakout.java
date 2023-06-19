package com.gdx.tutorials.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gdx.tutorials.breakout.components.Ball;
import com.gdx.tutorials.breakout.components.GameComponent;

public class Breakout extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    private GameComponent ball;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        ball = new Ball(150, 200, 70, 12, 5);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ball.update();
        ball.draw(shapeRenderer);
        shapeRenderer.end();
    }
}
