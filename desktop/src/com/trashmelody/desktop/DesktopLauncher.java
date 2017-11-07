package com.trashmelody.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trashmelody.TrashMelody;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        DisplayMode[] primaryDesktopMode = LwjglApplicationConfiguration.getDisplayModes();
        config.width = 900;
        config.height = 700;

		new LwjglApplication(new TrashMelody(), config);
	}
}
