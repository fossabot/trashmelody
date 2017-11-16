package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.components.DispatchComponent.State;
import com.trashmelody.entities.HitObjectEntity;
import com.trashmelody.managers.Assets;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

import java.util.function.Predicate;

import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.managers.Assets.CIGARETTE_HIT_OBJECT;
import static com.trashmelody.managers.Assets.TEXTURE;
import static com.trashmelody.utils.Functional.isBetween;
import static com.trashmelody.utils.Functional.isLessThan;
import static com.trashmelody.utils.Functional.isMoreThan;

public class DispatchSystem extends IteratingSystem {
    private World world;
    private Assets assets;
    private static final float leftBorderX = 0;
    private static final float rightBorderX = 1920 / PPM;
    private static final Predicate<Float> isInBound = isBetween.apply(leftBorderX, rightBorderX);
    private static final Predicate<Float> isOverBound = isMoreThan.apply(rightBorderX);
    private static final Predicate<Float> isUnderBound = isLessThan.apply(leftBorderX);

    @Inject
    public DispatchSystem(World world, Assets assets) {
        super(Family.all(DispatchComponent.class).get());

        this.world = world;
        this.assets = assets;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DispatchComponent dispatch = Mapper.dispatch.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);

        if (dispatch.state == State.Ready) {
            dispatch.state = State.Playing;
            physics.body.setLinearVelocity(new Vector2(dispatch.velocity, 0F));
        }

        dispatch.elapsedTime += deltaTime * 1000;
        Vector2 velocity = physics.body.getLinearVelocity();
        Vector2 position = physics.body.getPosition();

        if (!isInBound.test(position.x)) {
            physics.body.setLinearVelocity(-velocity.x, velocity.y);
        }

        if (!isInBound.test(position.x)) {
            if (isUnderBound.test(position.x)) {
                physics.body.setLinearVelocity(dispatch.velocity, velocity.y);
            } else {
                physics.body.setLinearVelocity(-dispatch.velocity, velocity.y);
            }
        }

        dispatch.hitObjects
                .takeWhile(ready(dispatch.elapsedTime))
                .map(hitObject -> new HitObjectEntity(
                        world,
                        new HitObjectComponent(hitObject),
                        new TypeComponent(TypeComponent.DISPATCHER),
                        new TextureComponent(assets.get(CIGARETTE_HIT_OBJECT, TEXTURE)),
                        position.x
                ))
                .peek(getEngine()::addEntity)
                .forEach(hitObjectEntity -> dispatch.hitObjects = dispatch.hitObjects.tail());
    }

    private Predicate<HitObject> ready(float elapsedTime) {
        return hitObject -> hitObject.isAfterStartTime(elapsedTime);
    }
}
