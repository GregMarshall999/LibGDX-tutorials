package com.gdx.tutorials.breakout.components;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface GameComponent {
    void update();
    void draw(ShapeRenderer shapeRenderer);
    int getWidth();
    int getHeight();
    int getOriginX();
    int getOriginY();
}
