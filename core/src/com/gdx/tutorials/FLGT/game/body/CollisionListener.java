package com.gdx.tutorials.FLGT.game.body;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gdx.tutorials.FLGT.game.FLGTWorld;

public class CollisionListener implements ContactListener {
    private FLGTWorld parentWorld;

    public CollisionListener(FLGTWorld parentWorld) {
        this.parentWorld = parentWorld;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType() + " has hit " + fb.getBody().getType());

        if(fa.getBody().getUserData() != null && fa.getBody().getUserData().equals(BodyUserData.SEA) ||
                fb.getBody().getUserData() != null && fb.getBody().getUserData().equals(BodyUserData.SEA)) {
            parentWorld.setSwimming(true);
            return;
        }

        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody)
            shootUpInAir(fa, fb);
        else if(fb.getBody().getType() == BodyDef.BodyType.StaticBody)
            shootUpInAir(fb, fa);
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("Contact end");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getBody().getUserData() != null && fa.getBody().getUserData().equals(BodyUserData.SEA) ||
                fb.getBody().getUserData() != null && fb.getBody().getUserData().equals(BodyUserData.SEA))
            parentWorld.setSwimming(false);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture) {
        System.out.println("Adding force");
        otherFixture.getBody().applyForceToCenter(new Vector2(-1000, -1000), true);
    }
}
