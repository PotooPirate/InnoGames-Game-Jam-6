package de.teampotoo.gamejam6;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.mainmenu.MainScreen;

public class GameJam6 implements ApplicationListener {

	public final static int STATE_MAINMENU 	= 1;
	public final static int STATE_GAME 		= 2;
	public final static int STATE_CREDITS 	= 3;
	public final static int STATE_HIGHSCORE = 4;
	
	private int state;
	
	private Stage mMainMenu;
	private Stage mGame;
	private Stage mCredits;
	private Stage mHighscore;
	
	@Override
	public void create() {
		ResourceLoader.loadResources();
		
		state = STATE_MAINMENU;
		
		mGame = new Stage();
		mHighscore = new Stage();
		mCredits = new Stage();
		
		mMainMenu = new Stage();
		MainScreen mainScreen = new MainScreen(this);
		mMainMenu.addActor(mainScreen);
		
		Gdx.input.setInputProcessor(mMainMenu);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT | Gdx.gl20.GL_DEPTH_BUFFER_BIT);
		
		switch(state) {
			case STATE_MAINMENU:
				mMainMenu.act(Gdx.graphics.getDeltaTime());
				mMainMenu.draw();
				break;
			case STATE_CREDITS:
				mCredits.act(Gdx.graphics.getDeltaTime());
				mCredits.draw();
				break;
			case STATE_HIGHSCORE:
				mHighscore.act(Gdx.graphics.getDeltaTime());
				mHighscore.draw();
				break;
			case STATE_GAME:
				mGame.act(Gdx.graphics.getDeltaTime());
				mGame.draw();
				break;
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
	
	public void startGame() {
		state = STATE_GAME;
		Gdx.input.setInputProcessor(mGame);
	}
}
