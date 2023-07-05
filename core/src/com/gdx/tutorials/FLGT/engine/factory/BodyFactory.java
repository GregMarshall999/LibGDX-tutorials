package com.gdx.tutorials.FLGT.engine.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public final class BodyFactory {
    private static BodyFactory instance;
    private final static float DEG_TO_RAD = 0.0174533f;

    private World world;

    private BodyFactory(World world) {
        this.world = world;
    }

    public static BodyFactory getInstance(World world) {
        if(instance == null)
            instance = new BodyFactory(world);
        else
            instance.world = world;

        return instance;
    }

    public Body makeBoxPolyBody(float posX, float posY, float width, float height, MaterialType material, BodyType bodyType) {
        return makeBoxPolyBody(posX, posY, width, height, material, bodyType, false);
    }

    public Body makeBoxPolyBody(float posX, float posY, float width, float height, MaterialType material, BodyType bodyType, boolean fixedRotation) {
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

    public Body makeCirclePolyBody(float posX, float posY, float radius, MaterialType material) {
        return makeCirclePolyBody(posX, posY, radius, material, DynamicBody, false);
    }

    public Body makeCirclePolyBody(float posX, float posY, float radius, MaterialType material, BodyType bodyType) {
        return makeCirclePolyBody(posX, posY, radius, material, bodyType, false);
    }

    public Body makeCirclePolyBody(float posX, float posY, float radius, MaterialType material, BodyType bodyType, boolean fixedRotation) {
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

    public Body makeBullet(float posX, float posY, float radius, MaterialType material, BodyType bodyType) {
        Body body = makeCirclePolyBody(posX, posY, radius, material, bodyType, false);

        for(Fixture fixture : body.getFixtureList())
            fixture.setSensor(true);

        body.setBullet(true);
        return body;
    }

    public Body makeSensorBody(float posX, float posy, float radius , BodyType bodyType) {
        return makeSensorBody(posX, posy, radius, bodyType, false);
    }

    public Body makeSensorBody(float posX, float posy, float radius , BodyType bodyType, boolean fixedRotation) {
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posX;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        Body boxBody = world.createBody(boxBodyDef);
        makeSensorFixture(boxBody, radius);
        return boxBody;
    }

    private static FixtureDef makeFixture(MaterialType material, Shape shape) {
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.shape = shape;
        fixtureDef.density = material.getDensity();
        fixtureDef.friction = material.getFriction();
        fixtureDef.restitution = material.getRestitution();

        return fixtureDef;
    }

    public void makeSensorFixture(Body body, float size) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(size);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);
        circleShape.dispose();

    }

    public void makeConeSensor(Body body, float size) {
        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape polygon = new PolygonShape();

        Vector2[] vertices = new Vector2[5];
        vertices[0] = new Vector2(0, 0);

        for (int i = 2; i < 6; i++) {
            float angle = (float) (i / 6.0 * 145 * DEG_TO_RAD);
            vertices[i-1] = new Vector2(size * ((float)Math.cos(angle)), size * ((float)Math.sin(angle)));
        }

        polygon.set(vertices);
        fixtureDef.shape = polygon;
        body.createFixture(fixtureDef);
        polygon.dispose();
    }

    public Body makePolygonShapeBody(Vector2[] vertices, float posX, float posY, MaterialType material, BodyType bodyType) {
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

    public void makeAllFixturesSensors(Body body) {
        for(Fixture fixture : body.getFixtureList())
            fixture.setSensor(true);
    }

    public void setAllFixtureMask(Body bod, Short filter) {
        Filter fil = new Filter();
        fil.groupIndex = filter;
        for(Fixture fix :bod.getFixtureList())
            fix.setFilterData(fil);
    }

    public Body addCircleFixture(Body bod, float x, float y, float size, MaterialType material, boolean sensor) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(size /2);
        circleShape.setPosition(new Vector2(x,y));
        FixtureDef fix = makeFixture(material,circleShape);
        fix.isSensor = sensor;
        bod.createFixture(fix);
        circleShape.dispose();
        return bod;
    }
}
