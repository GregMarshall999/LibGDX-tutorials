package com.gdx.tutorials.FLGT.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class FLGTAssets {
    private final AssetManager manager = new AssetManager();

    public final String playerImage = "images/player.png";
    public final String enemyImage = "images/enemy.png";

    public void queueAddImages() {
        manager.load(playerImage, Texture.class);
        manager.load(enemyImage, Texture.class);
    }

    public void finishLoading() {
        manager.finishLoading();
    }

    public <T> T get(String assetLocation) {
        return manager.get(assetLocation);
    }
}
