package com.trashmelody.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

import javax.inject.Inject;
import javax.rmi.CORBA.Util;
import javax.xml.soap.Text;

import static com.trashmelody.Utils.*;

public class WarningScreen extends ScreenAdapter {
    private TrashMelody game;
    private MenuScreen menuScreen;
    private Texture warningScreenLogo;
    private Texture warningScreenText;
    private int count;

    @Inject
    public WarningScreen(TrashMelody game, Assets assets, MenuScreen menuScreen) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.warningScreenLogo = assets.getWarningScreenLogo();
        this.warningScreenText = assets.getWarningScreenText();
    }

    @Override
    public void render(float delta) {
        clearScreen(253,243,255,1);

        if (count >= 1000) {
            game.setScreen(menuScreen);
        }
        if(userSkipScene() && count > 500){
            // Speed up the delay time with SkipScene()
            count += 100;
        }
        count += 5;

        // Start loading assets
        game.batch.begin();
        drawCenterX(game.batch, warningScreenLogo, 180F, 237F, 500F);
        drawCenterX(game.batch, warningScreenText, 992F, 216F, 230F);

        // Debug zone
        game.font.draw(game.batch, "Warning Screen", 30, 40);
        game.font.draw(game.batch, (count/10) + "%",150,40); // Showing delay progress
        // Debug zone

        game.batch.end();
    }

//    public void update(float delta){}
}
