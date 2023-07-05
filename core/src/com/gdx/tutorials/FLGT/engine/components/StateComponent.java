package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.gdx.tutorials.FLGT.engine.components.constants.State;

import static com.gdx.tutorials.FLGT.engine.components.constants.State.STATE_NORMAL;

public class StateComponent implements Component, Poolable {
    private State state = STATE_NORMAL;
    public float time = 0.0f;
    public boolean isLooping = false;

    @Override
    public void reset() {
        state = STATE_NORMAL;
        time = 0.0f;
        isLooping = false;
    }

    public void set(State newState){
        state = newState;
        time = 0.0f;
    }

    public State get(){
        return state;
    }
}
