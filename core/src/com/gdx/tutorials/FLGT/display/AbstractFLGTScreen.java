package com.gdx.tutorials.FLGT.display;

import com.badlogic.gdx.Screen;
import com.gdx.tutorials.FLGT.FLGT;

public abstract class AbstractFLGTScreen implements Screen {
    protected FLGT context;

    public AbstractFLGTScreen(FLGT context) {
        this.context = context;
    }
}
