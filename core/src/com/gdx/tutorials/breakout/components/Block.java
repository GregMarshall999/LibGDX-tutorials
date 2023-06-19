package com.gdx.tutorials.breakout.components;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block implements GameComponent {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private boolean destroy = false;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(x, y, width, height);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getOriginX() {
        return x - width/2;
    }

    @Override
    public int getOriginY() {
        return y - height/2;
    }

    public void destroy() {
        destroy = true;
    }

    public boolean isDestroy() {
        return destroy;
    }
}
