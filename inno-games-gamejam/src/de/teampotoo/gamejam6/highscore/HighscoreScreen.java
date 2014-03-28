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
	private int[] mHighscores = new int[]{0,0,0,0,0,0,0,0,0,0};
	
	public HighscoreScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;
		addBackButton();
		loadHighscoreFromPreferences();
	}
	
	private void loadHighscoreFromPreferences(){
		Preferences prefs = Gdx.app.getPreferences("Highscores");
		int place1 = prefs.getInteger("placeone", 0);
		int place2 = prefs.getInteger("placetwo", 0);
		int place3 = prefs.getInteger("placethree", 0);
		int place4 = prefs.getInteger("placefour",0);
		int place5 = prefs.getInteger("placefive", 0);
		int place6 = prefs.getInteger("placesix", 0);
		int place7 = prefs.getInteger("placeseven", 0);
		int place8 = prefs.getInteger("placeeight", 0);
		int place9 = prefs.getInteger("placenine", 0);
		int place10 = prefs.getInteger("placeten", 0);
		mHighscores = new int[]{place1,place2,place3,place4,place5,place6,place7,place8,place9,place10}; 
	}
	
	private void saveHighscoreToPreferences(){
		Preferences prefs = Gdx.app.getPreferences("Highscores");
		prefs.putInteger("placeone", mHighscores[0]);
		prefs.putInteger("placetwo", mHighscores[1]);
		prefs.putInteger("placethree", mHighscores[2]);
		prefs.putInteger("placefour", mHighscores[3]);
		prefs.putInteger("placefife", mHighscores[4]);
		prefs.putInteger("placesix", mHighscores[5]);
		prefs.putInteger("placeseven", mHighscores[6]);
		prefs.putInteger("placeeight", mHighscores[7]);
		prefs.putInteger("placenine", mHighscores[8]);
		prefs.putInteger("placeten", mHighscores[9]);
		prefs.flush();
	}
	
	public boolean insertScore(int score)
	{
		boolean inserted = false;
		int[] exchHighscore = new int[10];
		
		for(int x = 0; x < mHighscores.length ; x++)
		{
			if (score < mHighscores[x])
				exchHighscore[x] = mHighscores[x];
			else if (score > mHighscores[x] && !inserted ){
				exchHighscore[x] = score;
				inserted = true;
			}
			else if (inserted)
			{
				exchHighscore[x] = mHighscores[x-1];
			}	
		}
		mHighscores = exchHighscore;
		return inserted;
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
