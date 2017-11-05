package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

import javax.inject.Inject;

import static com.trashmelody.Utils.*;

public class MenuScreen extends ScreenAdapter {
    private TrashMelody game;
    private StageSelectScreen stageSelectScreen;
    private Camera camera;
    private Viewport viewport;
    private Texture splashScreenLogo;
    private Texture bg, btnStart, btnCollection, btnSetting, btnExit, borderLeft, borderRight;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    public MenuScreen(TrashMelody game, Assets assets, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = viewport;
        this.splashScreenLogo = assets.getSplashScreenLogo();
        this.bg = assets.getMenuScreenAssets("bg");
        this.btnStart = assets.getMenuScreenAssets("btnStart");
        this.btnCollection = assets.getMenuScreenAssets("btnCollection");
        this.btnSetting = assets.getMenuScreenAssets("btnSetting");
        this.btnExit = assets.getMenuScreenAssets("btnExit");
        this.borderLeft = assets.getMenuScreenAssets("borderLeft");
        this.borderRight = assets.getMenuScreenAssets("borderRight");
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();

        game.batch.begin();
        drawCenter(game.batch, bg, 691*2F, vh);
        drawCenterX(game.batch, splashScreenLogo, 500F, 286F, 520F);
        drawCenterX(game.batch, btnStart, 320F, 56F, 400F);
        drawCenterX(game.batch, btnCollection, 320F, 56F, 300F);
        drawCenterX(game.batch, btnSetting, 320F, 56F, 200F);
        drawCenterX(game.batch, btnExit, 320F, 56F, 100F);
        game.batch.draw(borderLeft, 0, 0, 168, 900);
        game.batch.draw(borderRight, vw-168, 0, 168, 900);

        // Click 'ENTER' equivalent to clicking play (for now)
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(stageSelectScreen);
        }

        /// Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Debugger.debug_mode = !Debugger.debug_mode;
        }
        if (Debugger.debug_mode){
            Debugger.runDebugger(game.batch, game.font,"Menu Screen Screen");
            Debugger.runAdvancedDebugger(game.batch,game.font,0,0);
        }
        // Debug zone

        game.batch.end();
    }

    private void update(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }
}
