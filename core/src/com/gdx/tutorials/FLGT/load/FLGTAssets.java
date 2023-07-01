package com.gdx.tutorials.FLGT.load;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class FLGTAssets {
    private final AssetManager manager = new AssetManager();

    public final String playerImage = "flgt/images/player.png";
    public final String enemyImage = "flgt/images/enemy.png";

    public final String boingSound = "flgt/sounds/boing.wav";
    public final String pingSound = "flgt/sounds/ping.wav";

    public final String music = "flgt/music/music.mp3";

    public final String skin = "flgt/skin/glassy-ui.json";

    public void queueAddImages() {
        manager.load(playerImage, Texture.class);
        manager.load(enemyImage, Texture.class);
    }

    public void queueAddSounds() {
        manager.load(boingSound, Sound.class);
        manager.load(pingSound, Sound.class);
    }

    public void queueAddMusic() {
        manager.load(music, Music.class);
    }

    public void queueAddSkin() {
        String atlasSkin = skin.split("\\.")[0] + ".atlas";

        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter(atlasSkin);
        manager.load(skin, Skin.class, params);
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
