package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;
import com.trashmelody.Assets;
import com.trashmelody.models.Button;
import com.trashmelody.models.Position;
import io.vavr.collection.List;

import static com.trashmelody.Utils.*;

public class SettingsScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Assets assets;
    private List<String> rightSections;
    private List<Position> rightPositions;
    private List<String> leftSections;
    private List<Position> leftPositions;
    private String currentSection;
    private BitmapFont largeFont;
    private BitmapFont mediumFont;

    @Inject
    SettingsScreen(SpriteBatch batch, OrthographicCamera camera, Assets assets) {
        this.batch = batch;
        this.camera = camera;
        this.assets = assets;

        largeFont = assets.getSuperSpaceFont(40, Color.BLACK);
        mediumFont = assets.getSuperSpaceFont(25, Color.BLACK);

        leftSections = List.empty();
        rightSections = List.of(
                "Effect Volume",
                "Screen Mode",
                "Calibrate",
                "Default"
        );
        currentSection = "Music Volume";
        leftPositions = List.of(
                Position.of(getCenterX(), getCenterY() + 60),
                Position.of(getCenterX(), getCenterY() + 120),
                Position.of(getCenterX(), getCenterY() + 180)
        );
        rightPositions = List.of(
                Position.of(getCenterX(), getCenterY() - 60),
                Position.of(getCenterX(), getCenterY() - 120),
                Position.of(getCenterX(), getCenterY() - 180)
        );
    }

    @Override
    public void render(float delta) {
        clearScreen();

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !rightSections.isEmpty()) {
            leftSections = leftSections.prepend(currentSection);
            currentSection = rightSections.head();
            rightSections = rightSections.tail();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !leftSections.isEmpty()) {
            rightSections = rightSections.prepend(currentSection);
            currentSection = leftSections.head();
            leftSections = leftSections.tail();
        }

        batch.begin();
        rightSections.zipWith(rightPositions, Button::new).forEach(this::drawButton);
        leftSections.zipWith(leftPositions, Button::new).forEach(this::drawButton);
        largeFont.draw(batch, currentSection, getViewportWidth()/2, getViewportHeight()/2 + 10);
        mediumFont.draw(batch, "Settings Screen", 30, 40);
        batch.end();
    }

    private void drawButton(Button button) {
        mediumFont.draw(batch, button.text, button.position.x, button.position.y);
    }
}
