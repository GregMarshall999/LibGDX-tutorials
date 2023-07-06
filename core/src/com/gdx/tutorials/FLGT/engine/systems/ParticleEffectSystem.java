package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.gdx.tutorials.FLGT.engine.components.Mapper;
import com.gdx.tutorials.FLGT.engine.components.ParticleEffectComponent;

public class ParticleEffectSystem extends IteratingSystem {
    private static final boolean shouldRender = true;

    private Array<Entity> renderQueue;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public ParticleEffectSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(ParticleEffectComponent.class).get());
        renderQueue = new Array<>();
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();

        if(shouldRender) {
            batch.begin();

            for (Entity entity : renderQueue) {
                ParticleEffectComponent pec = Mapper.peCom.get(entity);
                pec.particleEffect.draw(batch, deltaTime);
            }

            batch.end();
        }

        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ParticleEffectComponent pec = Mapper.peCom.get(entity);

        if(pec.isDead)
            pec.timeTilDeath -= deltaTime;

        if(pec.isattached)
            pec.particleEffect.setPosition(pec.attachedBody.getPosition().x + pec.xOffset,
                                            pec.attachedBody.getPosition().y + pec.yOffset);

        if(pec.particleEffect.isComplete() || pec.timeTilDeath <= 0)
            getEngine().removeEntity(entity);
        else
            renderQueue.add(entity);
    }
}
