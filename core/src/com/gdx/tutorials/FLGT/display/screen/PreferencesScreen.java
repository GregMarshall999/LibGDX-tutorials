package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.game.FLGTPreferences;
import com.gdx.tutorials.FLGT.display.DisplayUtility;
import com.gdx.tutorials.FLGT.game.FLGTAssets;

public class PreferencesScreen extends AbstractFLGTScreen {
    private final Stage stage;
    private final Table table; //<- can't be disposed

    private final Slider musicVolumeSlider;
    private final Slider soundVolumeSlider;
    private final CheckBox musicSwitch;
    private final CheckBox soundSwitch;
    private final TextButton menuReturn;

    private final Label title;
    private final Label musicVolumeLabel;
    private final Label soundVolumeLabel;
    private final Label musicSwitchLabel;
    private final Label soundSwitchLabel;

    public PreferencesScreen(FLGT context) {
        super(context);

        assetManager.queueAddAllSkins().finishLoading();
        Skin skin = assetManager.get(FLGTAssets.MENU_GLASSY_SKIN);

        stage = new Stage(new ScreenViewport());
        table = new Table();

        title = new Label("Preferences", skin);

        musicVolumeLabel = new Label("Music Volume", skin);
        musicVolumeSlider = new Slider(0f, 1f, 0.1f, false, skin);

        soundVolumeLabel = new Label("Sound Volume", skin);
        soundVolumeSlider = new Slider(0f, 1f, 0.1f, false, skin);

        musicSwitchLabel = new Label("Music", skin);
        musicSwitch = new CheckBox(null, skin);

        soundSwitchLabel = new Label("Sound", skin);
        soundSwitch = new CheckBox(null, skin);

        menuReturn = new TextButton("Back", skin, "small");

        musicVolumeSlider.setValue(FLGTPreferences.getMusicVolume());
        musicVolumeSlider.addListener(event -> {
            FLGTPreferences.setMusicVolume(musicVolumeSlider.getValue());
            return false;
        });

        musicSwitch.setChecked(FLGTPreferences.isMusicEnabled());
        musicSwitch.addListener(event -> {
            FLGTPreferences.setMusicEnabled(musicSwitch.isChecked());
            return false;
        });

        soundVolumeSlider.setValue(FLGTPreferences.getSoundVolume());
        soundVolumeSlider.addListener(event -> {
            FLGTPreferences.setSoundVolume(soundVolumeSlider.getValue());
            return false;
        });

        soundSwitch.setChecked(FLGTPreferences.isSoundEnabled());
        soundSwitch.addListener(event -> {
            FLGTPreferences.setSoundEnabled(soundSwitch.isChecked());
            return false;
        });

        menuReturn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DisplayUtility.changeScreen(context, FLGTScreen.MENU);
            }
        });
    }

    @Override
    public void show() {
        stage.clear();
        table.clear();

        table.setFillParent(true);
        //table.setDebug(true); //we can see the item boxes
        stage.addActor(table);

        table.add(title).colspan(2);
        table.row().pad(10, 0, 0, 10);
        table.add(musicVolumeLabel).left();
        table.add(musicVolumeSlider);
        table.row().pad(10, 0, 0, 10);
        table.add(musicSwitchLabel).left();
        table.add(musicSwitch);
        table.row().pad(10, 0, 0, 10);
        table.add(soundVolumeLabel).left();
        table.add(soundVolumeSlider);
        table.row().pad(10, 0, 0, 10);
        table.add(soundSwitchLabel).left();
        table.add(soundSwitch);
        table.row().pad(10, 0, 0, 10);
        table.add(menuReturn).colspan(2);

        Gdx.input.setInputProcessor(stage);
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
        stage.dispose();
    }
}
