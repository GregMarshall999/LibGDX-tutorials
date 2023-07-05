package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool.Poolable;

public class AnimationComponent implements Component, Poolable {
    public IntMap<Animation> animations = new IntMap<>();

    @Override
    public void reset() {

    }
}
