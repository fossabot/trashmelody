package com.trashmelody.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.trashmelody.TrashMelody;
import com.trashmelody.handlers.CollisionDetector;
import com.trashmelody.handlers.KeyboardController;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.screens.*;
import com.trashmelody.systems.*;
import io.vavr.collection.List;

import java.util.Arrays;

import static com.trashmelody.utils.RenderingUtils.getViewportHeight;
import static com.trashmelody.utils.RenderingUtils.getViewportWidth;

public class GameModule implements Module {
    private TrashMelody game;

    public GameModule(TrashMelody trashMelody) {
        this.game = trashMelody;
    }

    @Override
    public void configure(Binder binder) {
        binder.requireAtInjectOnConstructors();
        binder.requireExactBindingAnnotations();
        binder.bind(TrashMelody.class).toInstance(game);
        binder.bind(Assets.class).in(Singleton.class);
        binder.bind(MusicManager.class).in(Singleton.class);
        binder.bind(KeyboardController.class).in(Singleton.class);
//        binder.bind(ScreenProvider.cl ass).in(Singleton.class);
//        binder.bind(DebugInputProcessor.class).in(Singleton.class);

//        ScreenProvider.screenClasses.forEach(screen -> binder.bind(screen).in(Singleton.class));
    }

    @Provides @Singleton
    public Camera provideCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, getViewportWidth(), getViewportHeight());
        camera.update();
        return camera;
    }

    @Provides @Singleton
    public Viewport provideViewport(Camera camera) {
        return new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
    }

    @Provides
    @Singleton
    public World world() {
        Box2D.init();
        World world = new World(new Vector2(0F, -9.81F), true);
        world.setContactListener(new CollisionDetector());
        return world;
    }

    @Provides
    @Singleton
    public Systems systems() {
        return new Systems(Arrays.asList(
                CollisionSystem.class,
                RenderingSystem.class,
                PlayerControlSystem.class,
                PhysicsDebugSystem.class,
                PhysicsSystem.class,
                PhysicsSynchronizationSystem.class
        ));
    }
}
