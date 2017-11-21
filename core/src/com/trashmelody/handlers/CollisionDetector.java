package com.trashmelody.handlers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.trashmelody.components.CollisionComponent;
import com.trashmelody.components.Mapper;

public class CollisionDetector implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (a.getBody().getUserData() instanceof Entity) {
            Entity entity = (Entity) a.getBody().getUserData();
            entityCollision(entity, b);
        } else if (b.getBody().getUserData() instanceof Entity) {
            Entity entity = (Entity) b.getBody().getUserData();
            entityCollision(entity, a);
        }
    }

    private void entityCollision(Entity entity, Fixture f) {
        // Check the collided Entity is also an Entity
        if (f.getBody().getUserData() instanceof Entity) {
            Entity colEnt = (Entity) f.getBody().getUserData();
            CollisionComponent col = Mapper.collision.get(entity);
            CollisionComponent colb = Mapper.collision.get(colEnt);

            // set the CollidedEntity of the component
            col.setCollidedEntity(colEnt);
            colb.setCollidedEntity(entity);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}
}
