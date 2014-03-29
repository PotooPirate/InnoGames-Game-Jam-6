package de.teampotoo.gamejam6.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.game.gui.Player;
import de.teampotoo.gamejam6.game.gui.Player.DanceStyle;
import de.teampotoo.gamejam6.helper.ResourceLoader;

public class HighscoreScreen extends Group {

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private GameJam6 mGameJam6;
	private int[] mHighscores = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private Table mHighscoreTable;
	
	private Player mPlayer;

	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	public HighscoreScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;
		addBackButton();
		addClearHighscoreButton();
		loadHighscoreFromPreferences();
		addLabels();
		// RESET THE SCORES
//		 Preferences prefs = Gdx.app.getPreferences("Highscores");
//		 prefs.clear();
//		 prefs.flush();
		
		mPlayer = new Player();
		mPlayer.create();
		
		mPlayer.setPosition(Gdx.graphics.getWidth()  / 5, Gdx.graphics.getHeight() / 14);
		mPlayer.setState(DanceStyle.level2);
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		mPlayer.render();
	}
	
	private void loadHighscoreFromPreferences() {
		Preferences prefs = Gdx.app.getPreferences("Highscores");
		int place1 = prefs.getInteger("placeone", 0);
		int place2 = prefs.getInteger("placetwo", 0);
		int place3 = prefs.getInteger("placethree", 0);
		int place4 = prefs.getInteger("placefour", 0);
		int place5 = prefs.getInteger("placefive", 0);
		int place6 = prefs.getInteger("placesix", 0);
		int place7 = prefs.getInteger("placeseven", 0);
		int place8 = prefs.getInteger("placeeight", 0);
		int place9 = prefs.getInteger("placenine", 0);
		int place10 = prefs.getInteger("placeten", 0);
		mHighscores = new int[] { place1, place2, place3, place4, place5,
				place6, place7, place8, place9, place10 };
	}

	public void saveHighscoreToPreferences() {
		Preferences prefs = Gdx.app.getPreferences("Highscores");
		prefs.putInteger("placeone", mHighscores[0]);
		prefs.putInteger("placetwo", mHighscores[1]);
		prefs.putInteger("placethree", mHighscores[2]);
		prefs.putInteger("placefour", mHighscores[3]);
		prefs.putInteger("placefive", mHighscores[4]);
		prefs.putInteger("placesix", mHighscores[5]);
		prefs.putInteger("placeseven", mHighscores[6]);
		prefs.putInteger("placeeight", mHighscores[7]);
		prefs.putInteger("placenine", mHighscores[8]);
		prefs.putInteger("placeten", mHighscores[9]);
		prefs.flush();
	}

	public boolean insertScore(int score) {
		boolean inserted = false;
		int[] exchHighscore = new int[10];

		for (int x = 0; x < mHighscores.length; x++) {
			if (score <= mHighscores[x])
				exchHighscore[x] = mHighscores[x];
			else if (score > mHighscores[x] && !inserted) {
				exchHighscore[x] = score;
				inserted = true;
			} else if (inserted) {
				exchHighscore[x] = mHighscores[x - 1];
			}
		}
		mHighscores = exchHighscore;
		return inserted;
	}

	private void addClearHighscoreButton(){
		Image clearButton = new Image(ResourceLoader.BUTTON);
		clearButton.setBounds(0, 0, 100, 50);
		clearButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				resetHighscore();
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		addActor(clearButton);
	}
	
	
	private void addBackButton() {
		Image backButton = new Image(ResourceLoader.BUTTON);
		backButton.setWidth(100);
		backButton.setHeight(50);
		backButton.setPosition(0,
				Gdx.graphics.getHeight() - backButton.getHeight());
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

	private void addLabels() {
		mHighscoreTable = new Table();
		Label place1Label = new Label("1. " + mHighscores[0],
				ResourceLoader.SKIN);
		Label place2Label = new Label("2. " + mHighscores[1],
				ResourceLoader.SKIN);
		Label place3Label = new Label("3. " + mHighscores[2],
				ResourceLoader.SKIN);
		Label place4Label = new Label("4. " + mHighscores[3],
				ResourceLoader.SKIN);
		Label place5Label = new Label("5. " + mHighscores[4],
				ResourceLoader.SKIN);
		Label place6Label = new Label("6. " + mHighscores[5],
				ResourceLoader.SKIN);
		Label place7Label = new Label("7. " + mHighscores[6],
				ResourceLoader.SKIN);
		Label place8Label = new Label("8. " + mHighscores[7],
				ResourceLoader.SKIN);
		Label place9Label = new Label("9. " + mHighscores[8],
				ResourceLoader.SKIN);
		Label place10Label = new Label("10. " + mHighscores[9],
				ResourceLoader.SKIN);

		mHighscoreTable.add(place1Label).row();
		mHighscoreTable.add(place2Label).row();
		mHighscoreTable.add(place3Label).row();
		mHighscoreTable.add(place4Label).row();
		mHighscoreTable.add(place5Label).row();
		mHighscoreTable.add(place6Label).row();
		mHighscoreTable.add(place7Label).row();
		mHighscoreTable.add(place8Label).row();
		mHighscoreTable.add(place9Label).row();
		mHighscoreTable.add(place10Label).row();

		mHighscoreTable.setPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
		addActor(mHighscoreTable);
	}

	public void refreshLabels() {
		removeActor(mHighscoreTable);

		mHighscoreTable = new Table();
		Label place1Label = new Label("1. " + mHighscores[0],
				ResourceLoader.SKIN);
		Label place2Label = new Label("2. " + mHighscores[1],
				ResourceLoader.SKIN);
		Label place3Label = new Label("3. " + mHighscores[2],
				ResourceLoader.SKIN);
		Label place4Label = new Label("4. " + mHighscores[3],
				ResourceLoader.SKIN);
		Label place5Label = new Label("5. " + mHighscores[4],
				ResourceLoader.SKIN);
		Label place6Label = new Label("6. " + mHighscores[5],
				ResourceLoader.SKIN);
		Label place7Label = new Label("7. " + mHighscores[6],
				ResourceLoader.SKIN);
		Label place8Label = new Label("8. " + mHighscores[7],
				ResourceLoader.SKIN);
		Label place9Label = new Label("9. " + mHighscores[8],
				ResourceLoader.SKIN);
		Label place10Label = new Label("10. " + mHighscores[9],
				ResourceLoader.SKIN);

		mHighscoreTable.add(place1Label).row();
		mHighscoreTable.add(place2Label).row();
		mHighscoreTable.add(place3Label).row();
		mHighscoreTable.add(place4Label).row();
		mHighscoreTable.add(place5Label).row();
		mHighscoreTable.add(place6Label).row();
		mHighscoreTable.add(place7Label).row();
		mHighscoreTable.add(place8Label).row();
		mHighscoreTable.add(place9Label).row();
		mHighscoreTable.add(place10Label).row();

		mHighscoreTable.setPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
		addActor(mHighscoreTable);
	}
	
	private void resetHighscore()
	{
		 Preferences prefs = Gdx.app.getPreferences("Highscores");
		 prefs.clear();
		 prefs.flush();
		 
		 refreshLabels();
	}
}
