package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.display.FLGTScreen;
import com.gdx.tutorials.FLGT.game.FLGTAssets;
import com.gdx.tutorials.FLGT.display.DisplayUtility;

public class MenuScreen extends AbstractFLGTScreen {
    private final Stage stage;
    private final Table table;

    private final TextButton newGame;
    private final TextButton preferences;
    private final TextButton exit;

    public MenuScreen(FLGT context) {
        super(context);

        assetManager.queueAddAllSkins().finishLoading();
        Skin skin = assetManager.get(FLGTAssets.menuGlassySkin);
        TextureAtlas atlas = assetManager.get(FLGTAssets.loadingImages);
        TextureRegion background = atlas.findRegion("flamebackground");

        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setBackground(new TiledDrawable(background));

        //buttons init
        newGame = new TextButton("New Game", skin);
        preferences = new TextButton("Preferences", skin);
        exit = new TextButton("Exit", skin);

        //button events init
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DisplayUtility.changeScreen(context, FLGTScreen.APPLICATION);
            }
        });
        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DisplayUtility.changeScreen(context, FLGTScreen.PREFERENCE);
            }
        });
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void show() {
        stage.clear();
        table.clear();

        //UI table init
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        //adding buttons to table
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //clear screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //stage redraw
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
}
