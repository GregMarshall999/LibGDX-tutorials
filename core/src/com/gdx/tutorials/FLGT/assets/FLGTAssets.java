package com.gdx.tutorials.FLGT.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//todo prevent reloading loaded assets
public class FLGTAssets extends AssetManager {
    public static final String gameImages = "flgt/images/game.atlas";
    public static final String loadingImages = "flgt/images/loading.atlas";

    public static final String music = "flgt/music/gameMusic.mp3";

    public static final String menuGlassySkin = "flgt/skins/glassy-ui.json";
    private static final String menuGlassySkinAtlas = "flgt/skins/glassy-ui.atlas";

    public static final String boingSound = "flgt/sounds/boing.wav";
    public static final String pingSound = "flgt/sounds/ping.wav";

    /**
     * Async assets game images file loading
     * @return assets instance
     */
    public FLGTAssets queueAddAllGameImages() {
        load(gameImages, TextureAtlas.class);
        return this;
    }

    /**
     * Async assets loading images file loading
     * @return assets instance
     */
    public FLGTAssets queueAddAllLoadingImages() {
        load(loadingImages, TextureAtlas.class);
        return this;
    }

    /**
     * Async assets fonts file loading
     * @return assets instance
     */
    public FLGTAssets queueAddAllFonts() {
        return this;
    }

    /**
     * Async assets particles file loading
     * @return assets instance
     */
    public FLGTAssets queueAddAllParticles() {
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
