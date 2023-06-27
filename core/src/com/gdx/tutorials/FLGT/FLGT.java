package com.gdx.tutorials.FLGT;

import com.badlogic.gdx.Game;
import com.gdx.tutorials.FLGT.loader.FLGTAssets;
import com.gdx.tutorials.FLGT.tools.ScreenUtil;

public class FLGT extends Game {
    private FLGTAssets assetManager = new FLGTAssets();

    @Override
    public void create() {
        ScreenUtil.initLoading(this);
    }

    public FLGTAssets getAssetManager() {
        return assetManager;
    }
}
