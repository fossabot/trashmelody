package com.trashmelody.components;

import com.badlogic.ashley.core.ComponentMapper;

public class Mapper {
    public static final ComponentMapper<PhysicsComponent> physics = ComponentMapper.getFor(PhysicsComponent.class);
    public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<TextureRegionComponent> textureRegion = ComponentMapper.getFor(TextureRegionComponent.class);
    public static final ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<CollisionComponent> collision = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<PlayerComponent> player = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<StateComponent> state = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<TypeComponent> type = ComponentMapper.getFor(TypeComponent.class);
    public static final ComponentMapper<HitObjectComponent> hitObject = ComponentMapper.getFor(HitObjectComponent.class);
    public static final ComponentMapper<ScanLineComponent> scanLine = ComponentMapper.getFor(ScanLineComponent.class);
    public static final ComponentMapper<DispatchComponent> dispatch = ComponentMapper.getFor(DispatchComponent.class);
    public static final ComponentMapper<DestroyComponent> removing = ComponentMapper.getFor(DestroyComponent.class);
    public static final ComponentMapper<ScoringComponent> scoring = ComponentMapper.getFor(ScoringComponent.class);
    public static final ComponentMapper<TimerComponent> timer = ComponentMapper.getFor(TimerComponent.class);
    public static final ComponentMapper<HealthComponent> health = ComponentMapper.getFor(HealthComponent.class);
    public static final ComponentMapper<CallbackComponent> callback = ComponentMapper.getFor(CallbackComponent.class);
}
