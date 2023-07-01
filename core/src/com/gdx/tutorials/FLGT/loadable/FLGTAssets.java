package com.gdx.tutorials.FLGT.loadable;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//todo prevent reloading loaded assets
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
     * @return assets instance
     */
    public FLGTAssets queueAddAllImages() {
        load(playerImage, Texture.class);
        load(enemyImage, Texture.class);
        return this;
    }

    /**
     * Async assets music file loading
     * @return assets instance
     */
    public FLGTAssets queueAddAllMusic() {
        load(music, Music.class);
        return this;
    }

    /**
     * Async assets skins file loading
     * @return assets instance
     */
    public FLGTAssets queueAddAllSkins() {
        SkinParameter params = new SkinParameter(menuGlassySkinAtlas);
        load(menuGlassySkin, Skin.class, params);
        return this;
    }

    /**
     * Async assets sounds file loading
     * @return assets instance
     */
    public FLGTAssets queueAddAllSounds() {
        load(boingSound, Sound.class);
        load(pingSound, Sound.class);
        return this;
    }
}
