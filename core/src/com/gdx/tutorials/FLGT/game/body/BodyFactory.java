package com.gdx.tutorials.FLGT.game.body;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gdx.tutorials.FLGT.game.body.material.MaterialType;
import com.gdx.tutorials.FLGT.data.GameConstants;

public final class BodyFactory {
    private final World world;

    private static BodyFactory instance;

    private BodyFactory(World world) {
        this.world = world;
    }

    public static BodyFactory getInstance(World world) {
        if(instance == null)
            instance = new BodyFactory(world);

        return instance;
    }

    public Body makeCirclePolyBody(float posX, float posY, float radius, MaterialType material) {
        return makeCirclePolyBody(posX, posY, radius, material, BodyDef.BodyType.DynamicBody, false);
    }

    public Body makeCirclePolyBody(float posX, float posY, float radius, MaterialType material, BodyDef.BodyType bodyType) {
        return makeCirclePolyBody(posX, posY, radius, material, bodyType, false);
    }

    public Body makeCirclePolyBody(float posX, float posY, float radius, MaterialType material, BodyDef.BodyType bodyType, boolean fixedRotation) {
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posX;
        boxBodyDef.position.y = posY;
        boxBodyDef.fixedRotation = fixedRotation;

        Body boxBody = world.createBody(boxBodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        boxBody.createFixture(makeFixture(material, circleShape));
        circleShape.dispose();

        return boxBody;
    }

    public Body makeBoxPolyBody(float posX, float posY, float width, float height, MaterialType material, BodyDef.BodyType bodyType){
        return makeBoxPolyBody(posX, posY, width, height, material, bodyType, false);
    }

    public Body makeBoxPolyBody(float posX, float posY, float width, float height, MaterialType material, BodyDef.BodyType bodyType, boolean fixedRotation){
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posX;
        boxBodyDef.position.y = posY;
        boxBodyDef.fixedRotation = fixedRotation;

        Body boxBody = world.createBody(boxBodyDef);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2, height/2);
        boxBody.createFixture(makeFixture(material, poly));
        poly.dispose();

        return boxBody;
    }

    public Body makePolygonShapeBody(Vector2[] vertices, float posX, float posY, MaterialType material, BodyDef.BodyType bodyType) {
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posX;
        boxBodyDef.position.y = posY;
        Body boxBody = world.createBody(boxBodyDef);

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices);
        boxBody.createFixture(makeFixture(material, polygon));
        polygon.dispose();

        return boxBody;
    }

    public void makeConeSensor(Body body, float size) {
        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape polygon = new PolygonShape();

        float radius = size;
        Vector2[] vertices = new Vector2[5];
        vertices[0] = new Vector2(0, 0);

        for (int i = 2; i < 6; i++) {
            float angle = (float) (i / 6.0 * 145 * GameConstants.DEGTORAD);
            vertices[i-1] = new Vector2(radius * ((float)Math.cos(angle)), radius * ((float)Math.sin(angle)));
        }

        polygon.set(vertices);
        fixtureDef.shape = polygon;
        body.createFixture(fixtureDef);
        polygon.dispose();
    }

    public void makeAllFixturesSensors(Body body) {
        for(Fixture fixture : body.getFixtureList())
            fixture.setSensor(true);
    }

    private static FixtureDef makeFixture(MaterialType material, Shape shape) {
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.shape = shape;
        fixtureDef.density = material.getDensity();
        fixtureDef.friction = material.getFriction();
        fixtureDef.restitution = material.getRestitution();

        return fixtureDef;
    }
}
