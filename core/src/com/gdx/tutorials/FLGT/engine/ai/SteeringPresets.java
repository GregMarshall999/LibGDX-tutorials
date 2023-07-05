package com.gdx.tutorials.FLGT.engine.ai;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Flee;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gdx.tutorials.FLGT.engine.components.SteeringComponent;

public class SteeringPresets {
    public static Wander<Vector2> getWander(SteeringComponent scom){
        Wander<Vector2> wander = new Wander<Vector2>(scom)
                .setFaceEnabled(false)
                .setWanderOffset(20f)
                .setWanderOrientation(0f)
                .setWanderRadius(10f)
                .setWanderRate(MathUtils.PI2 * 2);
        return wander;
    }

    public static Seek<Vector2> getSeek(SteeringComponent seeker, SteeringComponent target){
        Seek<Vector2> seek = new Seek<>(seeker, target);
        return seek;
    }

    public static Flee<Vector2> getFlee(SteeringComponent runner, SteeringComponent fleeingFrom){
        Flee<Vector2> seek = new Flee<>(runner, fleeingFrom);
        return seek;
    }

    public static Arrive<Vector2> getArrive(SteeringComponent runner, SteeringComponent target){
        Arrive<Vector2> arrive = new Arrive<>(runner, target)
                .setTimeToTarget(0.1f)
                .setArrivalTolerance(7f)
                .setDecelerationRadius(10f);

        return arrive;
    }
}
