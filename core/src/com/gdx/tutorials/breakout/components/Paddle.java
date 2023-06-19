package com.gdx.tutorials.breakout.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle implements GameComponent {
    private int x, y;
    private int width;
    private int height;

    public Paddle() {
        x = Gdx.input.getX();
        y = 10;
        width = 100;
        height = 10;
    }

    @Override
    public void update() {
        x = Gdx.input.getX() - width/2;
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        renderer.rect(x, y, width, height);
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.PADDLE;
    }
}
