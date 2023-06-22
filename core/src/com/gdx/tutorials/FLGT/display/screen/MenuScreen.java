package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.AbstractFLGTScreen;
import com.gdx.tutorials.FLGT.display.FLGTButton;

import java.util.HashMap;
import java.util.Map;

import static com.gdx.tutorials.FLGT.display.FLGTButton.*;

public class MenuScreen extends AbstractFLGTScreen {
    private Stage stage;
    private Table table;
    private Skin skin;

    private Map<FLGTButton, TextButton> buttons = new HashMap<>();

    public MenuScreen(FLGT context) {
        super(context);

        stage = new Stage(new ScreenViewport());
        table = new Table();
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        //buttons init
        buttons.put(NEW_GAME, new TextButton(NEW_GAME.getName(), skin));
        buttons.put(PREFERENCES, new TextButton(PREFERENCES.getName(), skin));
        buttons.put(EXIT, new TextButton(EXIT.getName(), skin));

        //stage init
        Gdx.input.setInputProcessor(stage);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        //UI table init
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        table.add(buttons.get(NEW_GAME)).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(buttons.get(PREFERENCES)).fillX().uniformX();
        table.row();
        table.add(buttons.get(EXIT)).fillX().uniformX();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    }
}
