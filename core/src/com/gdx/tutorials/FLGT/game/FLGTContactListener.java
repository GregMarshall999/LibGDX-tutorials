package com.gdx.tutorials.FLGT.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.gdx.tutorials.FLGT.components.CollisionComponent;

public class FLGTContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType() + " has hit " + fb.getBody().getType());

        Entity entity;
        if(fa.getBody().getUserData() instanceof Entity) {
            entity = (Entity) fa.getBody().getUserData();
            entityCollision(entity, fb);
        }
        else if(fb.getBody().getUserData() instanceof Entity) {
            entity = (Entity) fb.getBody().getUserData();
            entityCollision(entity, fa);
        }
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("Contact end");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void entityCollision(Entity entity, Fixture fixture) {
        if(fixture.getBody().getUserData() instanceof Entity) {
            Entity collidingEntity = (Entity) fixture.getBody().getUserData();

            CollisionComponent colA = entity.getComponent(CollisionComponent.class);
            CollisionComponent colB = collidingEntity.getComponent(CollisionComponent.class);

            if(colA != null)
                colA.entity = collidingEntity;
            else if(colB != null)
                colB.entity = entity;
        }
    }
}
