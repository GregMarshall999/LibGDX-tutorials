package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.display.FLGTScreen;
import com.gdx.tutorials.FLGT.display.DisplayUtility;
import com.gdx.tutorials.FLGT.assets.FLGTAssets;

public class StartupScreen extends AbstractFLGTScreen {
    private final int IMAGE = 0;
    private final int FONT = 1;
    private final int SKIN = 2;
    private final int PARTICLE = 3;
    private final int SOUND = 4;
    private final int MUSIC = 5;

    private final SpriteBatch spriteBatch;

    private TextureRegion title;
    private TextureRegion dash;
    private Animation<TextureRegion> flameAnimation;

    private int currentLoadingStage = 0;
    private float countDown = 5f;
    private float stateTime = 0f;

    public StartupScreen(FLGT applicationContext) {
        super(applicationContext);

        spriteBatch = new SpriteBatch();
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);

        assetManager.queueAddAllLoadingImages().finishLoading();
    }

    @Override
    public void show() {
        TextureAtlas atlas = assetManager.get(FLGTAssets.loadingImages);

        title = atlas.findRegion("staying-alight-logo");
        dash = atlas.findRegion("loading-dash");

        flameAnimation = new Animation<>(0.07f, atlas.findRegions("flames/flames"), Animation.PlayMode.LOOP);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += delta;
        TextureRegion currentFrame = flameAnimation.getKeyFrame(stateTime, true);

        spriteBatch.begin();
        spriteBatch.draw(title, 135, 250);
        drawLoadingBar(currentLoadingStage * 2, currentFrame);
        spriteBatch.end();

        if(assetManager.update()) {
            switch (currentLoadingStage) {
                case IMAGE -> {
                    assetManager.queueAddAllGameImages();
                    System.out.println("Loading images...");
                }
                case FONT -> {
                    System.out.println("Loading fonts...");
                    assetManager.queueAddAllFonts();
                }
                case SKIN -> {
                    System.out.println("Loading fonts...");
                    assetManager.queueAddAllSkins();
                }
                case PARTICLE -> {
                    System.out.println("Loading particle effects...");
                    assetManager.queueAddAllParticles();
                }
                case SOUND -> {
                    System.out.println("Loading sounds...");
                    assetManager.queueAddAllSounds();
                }
                case MUSIC -> {
                    System.out.println("Loading music...");
                    assetManager.queueAddAllMusic();
                }
                default -> System.out.println("Finished");
            }

            currentLoadingStage++;

            if(currentLoadingStage > 6) {
                countDown -= delta;
                currentLoadingStage = 6;
                if(countDown < 0) {
                    DisplayUtility.changeScreen(applicationContext, FLGTScreen.MENU);
                }
            }
        }
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
        spriteBatch.dispose();
    }

    private void drawLoadingBar(int stage, TextureRegion currentFrame) {
        for(int i = 0; i < stage; i++) {
            spriteBatch.draw(currentFrame, 50 + (i*50), 150, 50, 50);
            spriteBatch.draw(dash, 35 + (i*50), 140, 80, 80);
        }
    }
}
