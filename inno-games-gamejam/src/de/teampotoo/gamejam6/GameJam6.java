package de.teampotoo.gamejam6;

import java.io.IOException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.teampotoo.gamejam6.credits.CreditsScreen;
import de.teampotoo.gamejam6.game.GameScreen;
import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.helper.SoundEffectPlayer;
import de.teampotoo.gamejam6.highscore.HighscoreScreen;
import de.teampotoo.gamejam6.mainmenu.MainScreen;
import de.teampotoo.musikeditor.musikMappingEditor;

public class GameJam6 implements ApplicationListener {

	/****************************************************************************
	 * 				variables
	 ****************************************************************************/
	
	public final static int STATE_MAINMENU 	= 1;
	public final static int STATE_GAME 		= 2;
	public final static int STATE_CREDITS 	= 3;
	public final static int STATE_HIGHSCORE = 4;
	
	private int state;
	
	private Stage mMainMenu;
	private Stage mGame;
	private Stage mCredits;
	private Stage mHighscore;
	
	private Music mainMenuMusic;
	
	private GameScreen mGameGroup;
	private HighscoreScreen mHighscoreScreen;
	private musikMappingEditor editor;
	
	/****************************************************************************
	 * 				methods
	 ****************************************************************************/	
	
	@Override
	public void create() {
		ResourceLoader.loadResources();
		SoundEffectPlayer.loadSounds();
		
		mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("data/music/NorkoreaTechno.mp3"));
		mainMenuMusic.setLooping(true);
		mainMenuMusic.play();
		
		state = STATE_MAINMENU;
		
		mHighscore = new Stage();
		mHighscoreScreen = new HighscoreScreen(this);
		mHighscore.addActor(mHighscoreScreen);
		
		mGame = new Stage();
		mGameGroup = new GameScreen(this, mHighscoreScreen);
		mGame.addActor(mGameGroup);
		mGame.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				mGameGroup.checkArrows(keycode);
				return super.keyDown(event, keycode);
			}
		});
		
		mCredits = new Stage();
		CreditsScreen creditScreen = new CreditsScreen(this);
		mCredits.addActor(creditScreen);
		
		mMainMenu = new Stage();
		MainScreen mainScreen = new MainScreen(this);
		mMainMenu.addActor(mainScreen);
		
		try {
			editor = new musikMappingEditor();
		} catch (IOException e) {
			System.out.println("fehlgeschlagen");
			e.printStackTrace();
		}
		mGameGroup.addActor(editor);
		
		Gdx.input.setInputProcessor(mMainMenu);
	}

	@Override
	public void resize(int width, int height) {
		mGameGroup.resize(width, height);
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
	
	private void stopMusic(){
		mainMenuMusic.stop();
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
	
	/****************************************************************************
	 * 				statemashine state changer
	 ****************************************************************************/
	
	
	public void startGame() {
		state = STATE_GAME;
		
		mGameGroup.startGame();
		Gdx.input.setInputProcessor(mGame);
		stopMusic();
	}
	
	public void startHighscore() {
		state = STATE_HIGHSCORE;
		Gdx.input.setInputProcessor(mHighscore);
		stopMusic();
	}
	
	public void startCredits() {
		state = STATE_CREDITS;
		Gdx.input.setInputProcessor(mCredits);
		stopMusic();
	}
	
	public void startMainMenu() {
		state = STATE_MAINMENU;
		Gdx.input.setInputProcessor(mMainMenu);
		mainMenuMusic.play();
	}
}
