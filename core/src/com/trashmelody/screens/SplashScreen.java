package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;
import com.google.inject.Provider;

import static com.trashmelody.Utils.*;

public class SplashScreen extends ScreenAdapter {
    private TrashMelody game;
    private Assets assets;
    private Provider<WarningScreen> warningScreen;
    private Texture splashScreenLogo;
    private SettingsScreen settingsScreen;
    private MenuScreen menuScreen;
    private StageSelectScreen stageSelectScreen;
    private CollectionScreen collectionScreen;
    private SandboxScreen sandboxScreen;
    private ResultScreen resultScreen;
    private int count = 0;

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, MenuScreen menuScreen, SettingsScreen settingsScreen,
                        WarningScreen warningScreen, StageSelectScreen stageSelectScreen, CollectionScreen collectionScreen,
                        SandboxScreen sandboxScreen, ResultScreen resultScreen) {
        this.game = game;
        this.assets = assets;
        this.warningScreen = warningScreen;
        this.settingsScreen = settingsScreen;
        this.menuScreen = menuScreen;
        this.stageSelectScreen = stageSelectScreen;
        this.collectionScreen = collectionScreen;
        this.sandboxScreen = sandboxScreen;
        this.resultScreen = resultScreen;
        this.splashScreenLogo = assets.get(Assets.SPLASH_LOGO, Assets.TEXTURE);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if(assets.assetManager.update()){
            game.setScreen(warningScreen.get());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            game.setScreen(resultScreen);
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);


        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Splash Screen");
        // Debug zone

        game.batch.end();
    }

}
