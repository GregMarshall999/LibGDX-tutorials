package com.gdx.tutorials.FLGT;

import com.badlogic.gdx.Game;
import com.gdx.tutorials.FLGT.load.FLGTAssets;
import com.gdx.tutorials.FLGT.display.DisplayUtility;

public class FLGT extends Game {
    public FLGTAssets assetManager = new FLGTAssets();

    @Override
    public void create() {
        DisplayUtility.initLoading(this);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
