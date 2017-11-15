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
}
