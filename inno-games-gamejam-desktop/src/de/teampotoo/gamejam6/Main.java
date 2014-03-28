package de.teampotoo.gamejam6;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "inno-games-gamejam";
		cfg.width = 480;
		cfg.height = 320;
		
		new LwjglApplication(new GameJam6(), cfg);
	}
}
