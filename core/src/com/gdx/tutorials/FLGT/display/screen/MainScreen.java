package com.gdx.tutorials.FLGT.display.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.control.PCControls;
import com.gdx.tutorials.FLGT.display.DisplayUtility;
import com.gdx.tutorials.FLGT.engine.FLGTUtils;
import com.gdx.tutorials.FLGT.engine.components.PlayerComponent;
import com.gdx.tutorials.FLGT.engine.factory.LevelFactory;
import com.gdx.tutorials.FLGT.engine.systems.*;

public class MainScreen extends AbstractFLGTScreen {
    private OrthographicCamera camera;
    private PCControls controls;
    private SpriteBatch batch;
    private PooledEngine engine;
    private LevelFactory factory;

    private Entity player;

    public MainScreen(FLGT applicationContext) {
        super(applicationContext);

        controls = new PCControls();
        engine = new PooledEngine();
        factory = new LevelFactory(engine, applicationContext.assetManager);

        batch = new SpriteBatch();
        RenderingSystem renderingSystem = new RenderingSystem(batch);
        camera = renderingSystem.getCamera();
        ParticleEffectSystem particleSystem = new ParticleEffectSystem(batch, camera);
        batch.setProjectionMatrix(camera.combined);

        engine.addSystem(new AnimationSystem());
        engine.addSystem(new PhysicsSystem(factory.world));
        engine.addSystem(renderingSystem);
        engine.addSystem(particleSystem);
        engine.addSystem(new PhysicsDebugSystem(factory.world, renderingSystem.getCamera()));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new SteeringSystem());
        engine.addSystem(new PlayerControlSystem(controls,factory));
        player = factory.createPlayer(camera);
        engine.addSystem(new EnemySystem(factory));
        engine.addSystem(new WallSystem(player));
        engine.addSystem(new WaterFloorSystem(player));
        engine.addSystem(new BulletSystem(player));
        engine.addSystem(new LevelGenerationSystem(factory));

        factory.createFloor();
        factory.createWaterFloor();

        int wallWidth = (int) (1*RenderingSystem.PPM);
        int wallHeight = (int) (60*RenderingSystem.PPM);
        TextureRegion wallRegion = FLGTUtils.makeTextureRegion(wallWidth, wallHeight, "222222FF");
        factory.createWalls(wallRegion); //TODO make some damn images for this stuff
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controls);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);

        PlayerComponent pc = (player.getComponent(PlayerComponent.class));
        if(pc.isDead){
            FLGTUtils.log("YOU DIED : back to menu you go!");
            applicationContext.lastScore = (int) pc.camera.position.y;
            DisplayUtility.changeScreen(FLGTScreen.ENDGAME, applicationContext);
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
        Gdx.input.setInputProcessor(controls);
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        engine.clearPools();
    }
}
