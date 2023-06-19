package com.gdx.tutorials.breakout.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle implements GameComponent{
    private int x;

    private final int y;
    private final int width;
    private final int height;

    public Paddle() {
        this.x = Gdx.input.getX();
        this.y = 20;
        width = 100;
        height = 5;
    }

    @Override
    public void update() {
        x = Gdx.input.getX();
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(
                x - width/2f,
                y - height/2f,
                width,
                height);
    }

    @Override
    public int getOriginX() {
        return x - width/2;
    }

    @Override
    public int getOriginY() {
        return y - height/2;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
