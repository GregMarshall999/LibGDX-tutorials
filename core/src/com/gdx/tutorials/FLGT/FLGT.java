package com.gdx.tutorials.FLGT;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.gdx.tutorials.FLGT.display.DisplayUtility;
import com.gdx.tutorials.FLGT.game.FLGTAssets;

import static com.gdx.tutorials.FLGT.display.screen.FLGTScreen.STARTUP;

public class FLGT extends Game {
    private Music music;

    public FLGTAssets assetManager = new FLGTAssets();
    public int lastScore = 0;

    @Override
    public void create() {
        DisplayUtility.changeScreen(STARTUP, this);

        assetManager.queueAddMusic();
        assetManager.finishLoading();
        music = assetManager.get(FLGTAssets.MUSIC);
    }

    @Override
    public void dispose() {
        music.dispose();
        assetManager.dispose();
    }
}
