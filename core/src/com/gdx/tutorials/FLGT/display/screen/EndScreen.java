package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.DisplayUtility;
import com.gdx.tutorials.FLGT.engine.FLGTUtils;

import static com.gdx.tutorials.FLGT.display.screen.FLGTScreen.MENU;

public class EndScreen extends AbstractFLGTScreen {
    private Skin skin;
    private Stage stage;
    private TextureAtlas atlas;
    private TextureAtlas.AtlasRegion background;

    public EndScreen(FLGT applicationContext) {
        super(applicationContext);
    }

    @Override
    public void show() {
        skin = applicationContext.assetManager.get("skin/glassy-ui.json");
        atlas = applicationContext.assetManager.get("images/loading.atlas");
        background = atlas.findRegion("flamebackground");

        TextButton menuButton = new TextButton("Back", skin, "small");

        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FLGTUtils.log("To the MENU");
                DisplayUtility.changeScreen(MENU, applicationContext);
            }
        });

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.setBackground(new TiledDrawable(background));

        Label labelScore = new Label("You score was "+applicationContext.lastScore+" Meters", skin);
        Label labelCredits = new Label("Credits:", skin);
        Label labelCredits1 = new Label("Game Design by", skin);
        Label labelCredits2 = new Label("gamedevelopment.blog", skin);
        Label labelCredits3 = new Label("Art Design by", skin);
        Label labelCredits4 = new Label("Random stuff off the internet", skin);

        table.add(labelScore).colspan(2);
        table.row().padTop(10);
        table.add(labelCredits).colspan(2);
        table.row().padTop(10);
        table.add(labelCredits1).uniformX().align(Align.left);
        table.add(labelCredits2).uniformX().align(Align.left);
        table.row().padTop(10);
        table.add(labelCredits3).uniformX().align(Align.left);
        table.add(labelCredits4).uniformX().align(Align.left);
        table.row().padTop(50);
        table.add(menuButton).colspan(2);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        DisplayUtility.changeScreen(MENU, applicationContext);
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

    }
}
