package com.gdx.tutorials.FLGT.display;

import com.badlogic.gdx.Screen;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.loadable.FLGTAssets;

public abstract class AbstractFLGTScreen implements Screen {
    protected FLGT applicationContext;
    protected final FLGTAssets assetManager;

    public AbstractFLGTScreen(FLGT applicationContext) {
        this.applicationContext = applicationContext;
        assetManager = this.applicationContext.assetManager;
    }
}
