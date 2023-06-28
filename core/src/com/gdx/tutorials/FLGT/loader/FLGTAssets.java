package com.gdx.tutorials.FLGT.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class FLGTAssets {
    private final AssetManager manager = new AssetManager();

    public final String playerImage = "flgt/images/player.png";
    public final String enemyImage = "flgt/images/enemy.png";

    public final String boingSound = "flgt/sounds/boing.wav";
    public final String pingSound = "flgt/sounds/ping.wav";

    public void queueAddImages() {
        manager.load(playerImage, Texture.class);
        manager.load(enemyImage, Texture.class);
    }

    public void queueAddSounds() {
        manager.load(boingSound, Sound.class);
        manager.load(pingSound, Sound.class);
    }

    public void finishLoading() {
        manager.finishLoading();
    }

    public <T> T get(String assetLocation) {
        return manager.get(assetLocation);
    }

    public <T> T get(String assetLocation, Class<T> clazz) {
        return manager.get(assetLocation, clazz);
    }
}
