package com.trashmelody;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Stage;
import com.trashmelody.screens.LoadingScreen;
import com.trashmelody.screens.SplashScreen;

import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertNotSame;

import static com.trashmelody.Utils.getViewportWidth;

public class TrashMelody extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	private Assets assets;
	private ScreenProvider screenProvider;
	Injector injector;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		Constant.SCALE = getViewportWidth() / Constant.WIDTH;

		injector = Guice.createInjector(Stage.PRODUCTION, new GameModule(this));
		this.assets = injector.getInstance(Assets.class);
		this.screenProvider = injector.getInstance(ScreenProvider.class);

		Gdx.input.setInputProcessor(injector.getInstance(DebugInputProcessor.class));
		setScreen(injector.getInstance(SplashScreen.class));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();

		batch.dispose();
		font.dispose();
		assets.dispose();
	}

	public void setLazyScreen(LazyScreen screen) {
		screen.loadLazyAssets(assets);
		LoadingScreen loadingScreen = screenProvider.get(LoadingScreen.class);
		loadingScreen.setNextScreen(screen);
		super.setScreen(loadingScreen);
	}
}
