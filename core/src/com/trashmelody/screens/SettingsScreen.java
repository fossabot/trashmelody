package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;
import com.trashmelody.models.Button;
import com.trashmelody.models.Position;
import io.vavr.collection.List;

import static com.trashmelody.Constant.SCALE;
import static com.trashmelody.Constant.WIDTH;
import static com.trashmelody.Utils.*;

public class SettingsScreen extends ScreenAdapter {
    private TrashMelody game;
    private SpriteBatch batch;
    private Camera camera;
    private Assets assets;
    private Viewport viewport;
    private BitmapFont largeFont;
    private BitmapFont mediumFont;

    @Inject
    SettingsScreen(TrashMelody game, SpriteBatch batch, Camera camera, Assets assets, Viewport viewport) {
        this.game = game;
        this.batch = batch;
        this.camera = camera;
        this.assets = assets;
        this.viewport = viewport;

        largeFont = assets.getSuperSpaceFont((int)(40 * SCALE), Color.BLACK);
        mediumFont = assets.getSuperSpaceFont((int)(25 * SCALE), Color.BLACK);
        mediumFont.setUseIntegerPositions(false);
        largeFont.setUseIntegerPositions(false);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

//        SCALE = getViewportWidth() / WIDTH;
//        largeFont = assets.getSuperSpaceFont((int)(40 * SCALE), Color.BLACK);
//        mediumFont = assets.getSuperSpaceFont((int)(25 * SCALE), Color.BLACK);
        viewport.update(width, height);
    }
}
