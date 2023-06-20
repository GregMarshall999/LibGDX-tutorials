package com.gdx.tutorials.fallingfruits;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class FallingFruits extends ApplicationAdapter {
    TextureAtlas textureAtlas;

    @Override
    public void create() {
        textureAtlas = new TextureAtlas("fallingfruits/sprites.txt");
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
    }
}
