package com.gdx.tutorials.FLGT.display.screen;

import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.display.FLGTScreen;
import com.gdx.tutorials.FLGT.display.DisplayUtility;

public class LoadingScreen extends AbstractFLGTScreen {
    public LoadingScreen(FLGT context) {
        super(context);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        DisplayUtility.changeScreen(context, FLGTScreen.MENU);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
