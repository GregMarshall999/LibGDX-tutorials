package com.gdx.tutorials.FLGT.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

public class AnimationComponent<T> implements Component {
    public IntMap<Animation<T>> animations = new IntMap<>();
}
