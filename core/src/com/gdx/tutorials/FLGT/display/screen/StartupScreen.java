package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.DisplayUtility;
import com.gdx.tutorials.FLGT.game.FLGTAssets;
import com.gdx.tutorials.FLGT.display.loading.LoadingBar;

import static com.gdx.tutorials.FLGT.display.screen.FLGTScreen.MENU;

public class StartupScreen extends AbstractFLGTScreen {
    private TextureAtlas atlas;
    private AtlasRegion title;
    private Animation flameAnimation;

    public final int IMAGE = 0;
    public final int FONT = 1;
    public final int PARTY = 2;
    public final int SOUND = 3;
    public final int MUSIC = 4;

    private int currentLoadingStage = 0;

    public float countDown = 0.1f;
    private AtlasRegion dash;
    private Stage stage;
    private Table table;
    private Image titleImage;
    private AtlasRegion copyright;
    private Image copyrightImage;
    private Table loadingTable;
    private AtlasRegion background;

    public StartupScreen(FLGT applicationContext) {
        super(applicationContext);

        stage = new Stage(new ScreenViewport());

        loadAssets();

        this.applicationContext.assetManager.queueAddImages();
    }

    @Override
    public void show() {
        titleImage = new Image(title);
        copyrightImage = new Image(copyright);

        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.setBackground(new TiledDrawable(background));

        loadingTable = new Table();
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));
        loadingTable.add(new LoadingBar(dash,flameAnimation));


        table.add(titleImage).align(Align.center).pad(10, 0, 0, 0).colspan(10);
        table.row();
        table.add(loadingTable).width(400);
        table.row();
        table.add(copyrightImage).align(Align.center).pad(200, 0, 0, 0).colspan(10);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (applicationContext.assetManager.update()) {
            currentLoadingStage+= 1;
            if(currentLoadingStage <= 5){
                loadingTable.getCells().get((currentLoadingStage-1)*2).getActor().setVisible(true);
                loadingTable.getCells().get((currentLoadingStage-1)*2+1).getActor().setVisible(true);
            }
            switch(currentLoadingStage){
                case FONT:
                    System.out.println("Loading fonts....");
                    applicationContext.assetManager.queueAddFonts();
                    break;
                case PARTY:
                    System.out.println("Loading Particle Effects....");
                    applicationContext.assetManager.queueAddParticleEffects();
                    break;
                case SOUND:
                    System.out.println("Loading Sounds....");
                    applicationContext.assetManager.queueAddSounds();
                    break;
                case MUSIC:
                    System.out.println("Loading fonts....");
                    applicationContext.assetManager.queueAddMusic();
                    break;
                case 5:
                    System.out.println("Finished");
                    break;
            }
            if (currentLoadingStage >5){
                countDown -= delta;
                currentLoadingStage = 5;
                if(countDown < 0){
                    DisplayUtility.changeScreen(MENU, applicationContext);
                }
            }
        }

        stage.act();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,true);
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
        stage.dispose();
    }

    private void loadAssets() {
        applicationContext.assetManager.queueAddLoadingImages();
        applicationContext.assetManager.finishLoading();

        atlas = applicationContext.assetManager.get(FLGTAssets.LOADING_IMAGES);
        title = atlas.findRegion("staying-alight-logo");
        dash = atlas.findRegion("loading-dash");
        background = atlas.findRegion("flamebackground");
        copyright = atlas.findRegion("copyright");
        flameAnimation = new Animation(0.07f, atlas.findRegions("flames/flames"), Animation.PlayMode.LOOP);
    }
}
