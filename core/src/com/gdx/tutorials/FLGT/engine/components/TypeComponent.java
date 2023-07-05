package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.gdx.tutorials.FLGT.engine.components.constants.Type;

import static com.gdx.tutorials.FLGT.engine.components.constants.Type.OTHER;

public class TypeComponent implements Component, Poolable {
    public Type type = OTHER;

    @Override
    public void reset() {
        type = OTHER;
    }
}
