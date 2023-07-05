package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.display.FLGTScreen;
import com.gdx.tutorials.FLGT.display.DisplayUtility;
import com.gdx.tutorials.FLGT.game.FLGTAssets;
import com.gdx.tutorials.FLGT.display.loading.LoadingBar;

public class StartupScreen extends AbstractFLGTScreen {
    private final int IMAGE = 0;
    private final int FONT = 1;
    private final int SKIN = 2;
    private final int PARTICLE = 3;
    private final int SOUND = 4;
    private final int MUSIC = 5;

    private final SpriteBatch spriteBatch;
    private final Stage stage;

    private TextureRegion background;
    private TextureRegion copyRight;
    private TextureRegion title;
    private TextureRegion dash;
    private Animation<TextureRegion> flameAnimation;
    private Table loadingTable;

    private int currentLoadingStage = 0;
    private float countDown = 2.5f;
    private float stateTime = 0f;

    public StartupScreen(FLGT applicationContext) {
        super(applicationContext);

        stage = new Stage(new ScreenViewport());

        loadAssets();

        spriteBatch = new SpriteBatch();
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
    }

    @Override
    public void show() {
        Image titleImage = new Image(title);
        Image copyRightImage = new Image(copyRight);
        Table table = new Table();
        loadingTable = new Table();

        table.setFillParent(true);
        //table.setDebug(true);
        table.setBackground(new TiledDrawable(background));

        for (int i = 0; i < 12; i++)
            loadingTable.add(new LoadingBar(dash, flameAnimation));

        table.add(titleImage).align(Align.center).pad(10, 0, 0, 0).colspan(10);
        table.row();
        table.add(loadingTable).width(400);
        table.row();
        table.add(copyRightImage).align(Align.center).pad(200, 0, 0, 0).colspan(10);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(assetManager.update()) {
            if(currentLoadingStage < 6) {
                loadingTable.getCells().get((currentLoadingStage)*2).getActor().setVisible(true);
                loadingTable.getCells().get((currentLoadingStage)*2+1).getActor().setVisible(true);
            }

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

        stage.act();
        stage.draw();
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
        stage.dispose();
    }

    private void drawLoadingBar(int stage, TextureRegion currentFrame) {
        for(int i = 0; i < stage; i++) {
            spriteBatch.draw(currentFrame, 50 + (i*50), 150, 50, 50);
            spriteBatch.draw(dash, 35 + (i*50), 140, 80, 80);
        }
    }

    private void loadAssets() {
        assetManager.queueAddAllLoadingImages().finishLoading();
        System.out.println("Startup images load complete");

        TextureAtlas atlas = assetManager.get(FLGTAssets.loadingImages);
        background = atlas.findRegion("flamebackground");
        copyRight = atlas.findRegion("copyright");
        title = atlas.findRegion("staying-alight-logo");
        dash = atlas.findRegion("loading-dash");
        flameAnimation = new Animation<>(0.07f, atlas.findRegions("flame"), Animation.PlayMode.LOOP);
    }
}
