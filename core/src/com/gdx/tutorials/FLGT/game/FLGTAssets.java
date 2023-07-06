package com.gdx.tutorials.FLGT.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//todo prevent reloading loaded assets
public class FLGTAssets extends AssetManager {
    public static final String GAME_IMAGES = "flgt/images/game.atlas";
    public static final String LOADING_IMAGES = "flgt/images/loading.atlas";

    public static final String MUSIC = "flgt/music/gameMusic.mp3";

    public static final String SMOKE_EFFECT = "flgt/particles/smoke.pe";
    public static final String WATER_EFFECT = "flgt/particles/water.pe";
    public static final String FIRE_EFFECT = "flgt/particles/fire.pe";

    public static final String MENU_GLASSY_SKIN = "flgt/skins/glassy-ui.json";

    public static final String BOING_SOUND = "flgt/sounds/boing.wav";
    public static final String PING_SOUND = "flgt/sounds/ping.wav";

    public void queueAddFonts(){

    }

    public void queueAddParticleEffects(){
        ParticleEffectParameter pep = new ParticleEffectParameter();
        pep.atlasFile = GAME_IMAGES;
        load(SMOKE_EFFECT, ParticleEffect.class, pep);
        load(WATER_EFFECT, ParticleEffect.class, pep);
        load(FIRE_EFFECT, ParticleEffect.class, pep);
    }

    public void queueAddImages(){
        load(GAME_IMAGES, TextureAtlas.class);
    }

    public void queueAddLoadingImages(){
        load(LOADING_IMAGES, TextureAtlas.class);
    }

    public void queueAddSkin(){
        SkinParameter params = new SkinParameter("flgt/skins/glassy-ui.atlas");
        load(MENU_GLASSY_SKIN, Skin.class, params);
    }

    public void queueAddMusic(){
        load(MUSIC, Music.class);
    }

    public void queueAddSounds(){
        load(BOING_SOUND, Sound.class);
        load(PING_SOUND, Sound.class);
    }
}
