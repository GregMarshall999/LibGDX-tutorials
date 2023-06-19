package com.gdx.tutorials.breakout.util;

import com.gdx.tutorials.breakout.components.Ball;
import com.gdx.tutorials.breakout.components.GameComponent;

public abstract class CollisionUtil {
    public static boolean isColliding(Ball ball, GameComponent target) {
        return ball.getOriginX() < target.getOriginX() + target.getWidth() &&
                ball.getOriginX() + ball.getWidth() > target.getOriginX() &&
                ball.getOriginY() < target.getOriginY() + target.getHeight() &&
                ball.getOriginY() + ball.getHeight() > target.getOriginY();
    }
}
