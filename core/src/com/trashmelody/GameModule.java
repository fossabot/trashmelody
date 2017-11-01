package com.trashmelody;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.google.inject.*;
import com.trashmelody.screens.*;
import com.google.inject.Module;
import com.trashmelody.screens.WarningScreen;
import com.trashmelody.screens.NameScreen;

import static com.trashmelody.Utils.getViewportHeight;
import static com.trashmelody.Utils.getViewportWidth;

public class GameModule implements Module {
    private TrashMelody game;

    GameModule(TrashMelody trashMelody) {
        this.game = trashMelody;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(TrashMelody.class).toInstance(game);
    }

    @Provides
    @Singleton
    public SplashScreen provideSplashScreen(Assets assets, SettingsScreen settingsScreen,
                                            WarningScreen warningScreen, MenuScreen menuScreen, StageSelectScreen stageSelectScreen) {
        return new SplashScreen(game, assets, menuScreen, settingsScreen, warningScreen, stageSelectScreen);
    }

    @Provides
    @Singleton
    public WarningScreen provideWarningScreen(MenuScreen menuScreen, Assets assets) {
        return new WarningScreen(game, assets, menuScreen);
    }

    @Provides
    @Singleton
    public NameScreen provideNameScreen(Assets assets) {
        return new NameScreen(game, assets);
    }

    @Provides
    @Singleton
    public OrthographicCamera provideCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, getViewportWidth(), getViewportHeight());
        camera.update();
        return camera;
    }

    @Provides
    @Singleton
    public Assets provideAssetManager() {
        return new Assets();
    }

    @Provides
    @Singleton
    public StageSelectScreen provideStageSelectionScreen(Assets assets, MenuScreen menuScreen,
                                                         OrthographicCamera camera){
        return new StageSelectScreen(game, assets, menuScreen, camera);
    }

}
