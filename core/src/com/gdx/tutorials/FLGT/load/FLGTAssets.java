package com.gdx.tutorials.FLGT.load;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class FLGTAssets extends AssetManager {
    public static final String playerImage = "flgt/images/player.png";
    public static final String enemyImage = "flgt/images/enemy.png";

    public static final String music = "flgt/music/gameMusic.mp3";

    public static final String menuGlassySkin = "flgt/skins/glassy-ui.json";
    private static final String menuGlassySkinAtlas = "flgt/skins/glassy-ui.atlas";

    public static final String boingSound = "flgt/sounds/boing.wav";
    public static final String pingSound = "flgt/sounds/ping.wav";

    /**
     * Async assets images file loading
     */
    public void queueAddAllImages() {
        load(playerImage, Texture.class);
        load(enemyImage, Texture.class);
    }

    /**
     * Async assets music file loading
     */
    public void queueAddAllMusic() {
        load(music, Music.class);
    }

    /**
     * Async assets skins file loading
     */
    public void queueAddAllSkin() {
        SkinParameter params = new SkinParameter(menuGlassySkinAtlas);
        load(menuGlassySkin, Skin.class, params);
    }

    /**
     * Async assets sounds file loading
     */
    public void queueAddAllSounds() {
        load(boingSound, Sound.class);
        load(pingSound, Sound.class);
    }
}
