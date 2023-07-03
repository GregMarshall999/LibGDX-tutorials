package com.gdx.tutorials.FLGT.display.loading;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBar extends Actor {
    private TextureRegion region;
    private Animation<TextureRegion> animation;

    private TextureRegion currentFrame;

    private float stateTime = 0f;

    public LoadingBar(TextureRegion region, Animation<TextureRegion> animation) {
        super();

        this.region = region;
        this.animation = animation;

        setWidth(30);
        setHeight(25);
        setVisible(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(region, getX(), getY(), 30, 30);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        batch.draw(currentFrame, getX()-5, getY(), 40, 40);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        stateTime += delta;
        currentFrame = animation.getKeyFrame(stateTime, true);
    }
}
