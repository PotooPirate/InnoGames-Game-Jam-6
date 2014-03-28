package de.teampotoo.gamejam6.highscore;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.helper.ResourceLoader;

public class HighscoreScreen extends Group {

	private GameJam6 mGameJam6;
	private int[] Highscores = new int[10];
	
	public HighscoreScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;
		addBackButton();
	}
	
	private void loadHighscoreFromPreferences(){
		Preferences prefs = Gdx.app.getPreferences("Highscores");
		int place1 = prefs.getInteger("placeone", 0);
		int place2 = prefs.getInteger("placetwo", 0);
		int place3 ? prefs.getInteger("placethree", 0);
	}

	private void addBackButton() {
		Image backButton = new Image(ResourceLoader.BUTTON);
		backButton.setWidth(100);
		backButton.setHeight(50);
		backButton.setPosition(0, Gdx.graphics.getHeight() - backButton.getHeight());
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				mGameJam6.startMainMenu();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		addActor(backButton);
	}
}
