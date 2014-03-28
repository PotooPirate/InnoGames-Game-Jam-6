package de.teampotoo.gamejam6;

import com.badlogic.gdx.Game;

import de.teampotoo.gamejam6.credits.CreditsScreen;
import de.teampotoo.gamejam6.game.GameScreen;
import de.teampotoo.gamejam6.mainmenu.MainScreen;

public class GameJam6 extends Game {

	private MainScreen mMainScreen;
	private CreditsScreen mCredetsScreen;
	private MainScreen mHighscoreScreen;
	private GameScreen mGameScreen;
	
	@Override
	public void create() {
		this.mMainScreen = new MainScreen();
		this.mCredetsScreen = new CreditsScreen();
		this.mHighscoreScreen = new MainScreen();
		this.mGameScreen = new GameScreen();
		setScreen(mMainScreen);
	}
	
}
