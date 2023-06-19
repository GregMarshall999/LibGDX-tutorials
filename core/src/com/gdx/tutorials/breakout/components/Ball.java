package com.gdx.tutorials.breakout.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball implements GameComponent {
    private int x, y;
    private int xSpeed, ySpeed;

    private final int radius;

    private Color color = Color.WHITE;

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

        if(x < radius || x > Gdx.graphics.getWidth() - radius)
            xSpeed = -xSpeed;

        if(y < radius || y > Gdx.graphics.getHeight() - radius)
            ySpeed = -ySpeed;
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        renderer.setColor(color);
        renderer.circle(x, y, radius);
        renderer.setColor(Color.WHITE);
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.BALL;
    }
}
