package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.trashmelody.components.*;

import static com.trashmelody.constants.B2Dvars.PPM;

public class Player extends Entity {
    private World world;

    public Player(World world,
                  PlayerComponent player,
                  TypeComponent type) {
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        shape.setAsBox(20F / PPM, 20F / PPM);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.5F;
        fixtureDef.filter.groupIndex = type.getGroup();
        body.createFixture(fixtureDef);
        shape.dispose();


        TransformComponent transformComponent = new TransformComponent(new Vector2(640F / PPM, 520F / PPM));
        body.setTransform(transformComponent.position, 0F);
        // Set user data of body to this entity for handling collide
        body.setUserData(this);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body, "Player"));
        super.add(player);
        super.add(type);
        super.add(new CollisionComponent());
        super.add(new StateComponent());
    }
}
