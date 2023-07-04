package com.gdx.tutorials.FLGT.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool.Poolable;

public class AnimationComponent<T> implements Component, Poolable {
    public IntMap<Animation<T>> animations = new IntMap<>();

    @Override
    public void reset() {

    }
}
