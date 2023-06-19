package com.gdx.tutorials.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gdx.tutorials.breakout.components.Ball;
import com.gdx.tutorials.breakout.components.GameComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Breakout extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    private List<GameComponent> gameComponents = new ArrayList<>();
    private Random random = new Random();

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        for (int i = 0; i < 10; i++) {
            gameComponents.add(new Ball(
                    random.nextInt(Gdx.graphics.getWidth()),
                    random.nextInt(Gdx.graphics.getHeight()),
                    random.nextInt(100),
                    random.nextInt(15),
                    random.nextInt(15)));
        }
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
