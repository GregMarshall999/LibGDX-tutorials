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

import static com.gdx.tutorials.FLGT.display.screen.FLGTScreen.MENU;

public class PreferencesScreen extends AbstractFLGTScreen {
    private Stage stage;
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;


    public PreferencesScreen(FLGT applicationContext){
        super(applicationContext);
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = applicationContext.assetManager.get(FLGTAssets.MENU_GLASSY_SKIN);

        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeMusicSlider.setValue(FLGTPreferences.getMusicVolume());

        volumeMusicSlider.addListener(event -> {
            FLGTPreferences.setMusicVolume(volumeMusicSlider.getValue());
            return false;
        });

        final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundMusicSlider.setValue(FLGTPreferences.getSoundVolume());
        soundMusicSlider.addListener(event -> {
            FLGTPreferences.setSoundVolume(soundMusicSlider.getValue());
            return false;
        });

        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked(FLGTPreferences.isMusicEnabled());
        musicCheckbox.addListener(event -> {
            boolean enabled = musicCheckbox.isChecked();
            FLGTPreferences.setMusicEnabled(enabled);
            return false;
        });

        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.setChecked(FLGTPreferences.isSoundEnabled());
        soundEffectsCheckbox.addListener(event -> {
            boolean enabled = soundEffectsCheckbox.isChecked();
            FLGTPreferences.setSoundEnabled(enabled);
            return false;
        });

        final TextButton backButton = new TextButton("Back", skin, "small");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DisplayUtility.changeScreen(MENU, applicationContext);

            }
        });

        titleLabel = new Label( "Preferences", skin );
        volumeMusicLabel = new Label( "Music Volume", skin );
        volumeSoundLabel = new Label( "Sound Volume", skin );
        musicOnOffLabel = new Label( "Music", skin );
        soundOnOffLabel = new Label( "Sound Effect", skin );

        table.add(titleLabel).colspan(2);
        table.row().pad(10,0,0,10);
        table.add(volumeMusicLabel).left();
        table.add(volumeMusicSlider);
        table.row().pad(10,0,0,10);
        table.add(musicOnOffLabel).left();
        table.add(musicCheckbox);
        table.row().pad(10,0,0,10);
        table.add(volumeSoundLabel).left();
        table.add(soundMusicSlider);
        table.row().pad(10,0,0,10);
        table.add(soundOnOffLabel).left();
        table.add(soundEffectsCheckbox);
        table.row().pad(10,0,0,10);
        table.add(backButton).colspan(2);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
