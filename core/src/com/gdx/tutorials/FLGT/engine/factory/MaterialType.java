package com.gdx.tutorials.FLGT.engine.factory;

public enum MaterialType {
    DEFAULT(7f, 0.5f, 0.3f),
    STEEL(1f, 0.3f, 0.1f),
    WOOD(0.5f, 0.7f, 0.3f),
    RUBBER(1f, 0f, 1f),
    STONE(1F, 0.9f, 0.1f);

    private final float density, friction, restitution;

    MaterialType(float density, float friction, float restitution) {
        this.density = density;
        this.friction = friction;
        this.restitution = restitution;
    }

    public float getDensity() {
        return density;
    }

    public float getFriction() {
        return friction;
    }

    public float getRestitution() {
        return restitution;
    }
}
