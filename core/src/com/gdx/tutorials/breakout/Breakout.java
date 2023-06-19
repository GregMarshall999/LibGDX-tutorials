package com.gdx.tutorials.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gdx.tutorials.breakout.components.Ball;
import com.gdx.tutorials.breakout.components.GameComponent;

import java.util.ArrayList;
import java.util.List;

public class Breakout extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    private List<GameComponent> gameComponents = new ArrayList<>();

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        gameComponents.add(new Ball(
                Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2,
                10,
                5,
                5));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (GameComponent gameComponent : gameComponents) {
            gameComponent.update();
            gameComponent.draw(shapeRenderer);
        }
        shapeRenderer.end();
    }
}
