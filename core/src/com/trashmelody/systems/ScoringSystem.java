package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.components.ScoringComponent.Accuracy;
import com.trashmelody.managers.Assets;

import static com.trashmelody.managers.Assets.*;

public class ScoringSystem extends IteratingSystem {
    private Assets assets;

    @Inject
    public ScoringSystem(Assets assets) {
        super(Family.all(ScoringComponent.class).get(), Systems.getIndex(ScoringSystem.class));

        this.assets = assets;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoringComponent scoring = Mapper.scoring.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);
        HealthComponent health = getHealthComponent();
        Texture accuracyTexture = assets.get(MISS_ACCURACY);

        if (scoring.getAccuracy() == Accuracy.Perfect) {
            accuracyTexture = assets.get(PERFECT_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Good) {
            accuracyTexture = assets.get(GOOD_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Cool) {
            accuracyTexture = assets.get(COOL_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Bad) {
            accuracyTexture = assets.get(BAD_ACCURACY);
            health.health -= 300;
        } else if (scoring.getAccuracy() == Accuracy.Miss) {
            accuracyTexture = assets.get(MISS_ACCURACY);
            health.health -= 800;
        }
        texture.texture = accuracyTexture;

        entity.remove(ScoringComponent.class);
        entity.add(fadeDown());
        entity.add(new CallbackComponent(entity1 -> entity1.add(new DestroyComponent()), 2000));
    }

    private static TimerComponent fadeDown() {
        return new TimerComponent((entity, lifeTime, remaining, delta) -> {
            TransformComponent transform = Mapper.transform.get(entity);
            TextureComponent texture = Mapper.texture.get(entity);

            texture.setAlpha(remaining / lifeTime);
            transform.scale = 2F - remaining / lifeTime;
        }, 2000);
    }

//    private static TimerComponent reduceHealth(float healthReduced) {
//        return new TimerComponent((entity, lifeTime, remaining, delta) -> {
//            HealthComponent health = Mapper.health.get(entity);
//            System.out.println("reducing health");
//            health.health -= (delta / lifeTime) * healthReduced;
//        }, 2000);
//    }

    private HealthComponent getHealthComponent() {
        return Mapper.health.get(getEngine().getEntitiesFor(Family.all(HealthComponent.class).get()).first());
    }
}
