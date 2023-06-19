package com.gdx.tutorials.breakout.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball implements GameComponent {
    private int x, y;
    private int xSpeed, ySpeed;

    private final int radius;

    public Ball(int x, int y, int radius, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public void update() {
        x += xSpeed;
        y += ySpeed;

        if(x < 0 || x > Gdx.graphics.getWidth())
            xSpeed = -xSpeed;

        if(y < 0 || y > Gdx.graphics.getHeight())
            ySpeed = -ySpeed;
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        renderer.circle(x, y, radius);
    }
}
