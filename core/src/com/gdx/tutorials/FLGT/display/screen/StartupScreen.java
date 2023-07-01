package com.gdx.tutorials.FLGT.display.screen;

import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.display.FLGTScreen;
import com.gdx.tutorials.FLGT.display.DisplayUtility;

public class StartupScreen extends AbstractFLGTScreen {
    public StartupScreen(FLGT applicationContext) {
        super(applicationContext);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        DisplayUtility.changeScreen(applicationContext, FLGTScreen.MENU);
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
