package com.gdx.tutorials.FLGT.engine.components;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.gdx.tutorials.FLGT.engine.FLGTUtils;

public class FLGTLocation implements Location<Vector2> {
    Vector2 position;
    float orientation;

    public FLGTLocation () {
        this.position = new Vector2();
        this.orientation = 0;
    }

    @Override
    public Vector2 getPosition () {
        return position;
    }

    @Override
    public float getOrientation () {
        return orientation;
    }

    @Override
    public void setOrientation (float orientation) {
        this.orientation = orientation;
    }

    @Override
    public Location<Vector2> newLocation () {
        return new FLGTLocation();
    }

    @Override
    public float vectorToAngle (Vector2 vector) {
        return FLGTUtils.vectorToAngle(vector);
    }

    @Override
    public Vector2 angleToVector (Vector2 outVector, float angle) {
        return FLGTUtils.angleToVector(outVector, angle);
    }
}
