package com.gdx.tutorials.breakout.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gdx.tutorials.breakout.util.CollisionUtil;

import java.util.List;

public class Ball implements GameComponent {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

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

        if (x < radius || x > Gdx.graphics.getWidth() - radius)
            xSpeed = -xSpeed;
        if (y < radius || y > Gdx.graphics.getHeight() - radius)
            ySpeed = -ySpeed;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(x, y, radius);
    }

    @Override
    public int getWidth() {
        return radius*2;
    }

    @Override
    public int getHeight() {
        return radius*2;
    }

    @Override
    public int getOriginX() {
        return x - radius;
    }

    @Override
    public int getOriginY() {
        return y - radius;
    }

    public void checkCollision(List<GameComponent> components) {
        for(GameComponent component : components) {
            if(component != this)
                if(CollisionUtil.isColliding(this, component)) {
                    ySpeed = -ySpeed;
                    if(component instanceof Block)
                        ((Block)component).destroy();
                }
        }
    }
}