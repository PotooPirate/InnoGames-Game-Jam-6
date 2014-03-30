package de.teampotoo.gamejam6.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.game.gui.DancePattern;
import de.teampotoo.gamejam6.game.gui.ISugarRocket;
import de.teampotoo.gamejam6.game.gui.Player;
import de.teampotoo.gamejam6.game.gui.Player.DanceStyle;
import de.teampotoo.gamejam6.game.gui.SugarBar;
import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.helper.SoundEffectPlayer;
import de.teampotoo.gamejam6.helper.SoundEffectPlayer.Effect;
import de.teampotoo.gamejam6.highscore.HighscoreScreen;
import de.teampotoo.gamejam6.shader.IBlurShader;
import de.teampotoo.gamejam6.shader.ShaderFactory;
import de.teampotoo.gamejam6.song.IBeat;
import de.teampotoo.gamejam6.song.ISong;
import de.teampotoo.gamejam6.song.IStep;
import de.teampotoo.gamejam6.song.IStep.StepType;
import de.teampotoo.gamejam6.song.SongFactory;

public class GameScreen extends Group implements IGameScreen, ISugarRocket {

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private GameJam6 mGameJam6;
	private HighscoreScreen mHighscore;
	private Image mUpperBackground;
	private SugarRocket mRocket1;
	private Image mLowerBackground;

	private int mPlayerPoints; // current points while the game runs
	private int mHighscorePoints; // Frozen points after the game finished
	private float mMultiplicator; //Combo multiplicator
	
	// player stuff
	private Player player;

	// HUD
	private SugarBar mSugarBar;
	private DancePattern mDancePattern;
	private Label mPointsLabel;
	private Label mComboLabel;
	private Label mRocketReachedSkyLabel;
	
	// Music
	private ISong mCurrentSong;
	
	private IBlurShader shader = ShaderFactory.createBlurShader();
	private float shaderBlurIntensity = 0f;
	private float shaderBrightIntensity = 0f;
	private float shaderRetroIntensity = 0f;
	
	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	public GameScreen(GameJam6 gameJam6, HighscoreScreen highscore) {
		this.mGameJam6 = gameJam6;
		this.mHighscore = highscore;
		this.mPlayerPoints = 0;
		this.mMultiplicator = 1.0f;

		mLowerBackground = new Image(ResourceLoader.sGameLowerBackground);
		mLowerBackground.setBounds(0, 0, mLowerBackground.getWidth(),
				mLowerBackground.getHeight());
		addActor(mLowerBackground);

		mRocket1 = new SugarRocket(150, 10f, this);
		addActor(mRocket1);
		
		mUpperBackground = new Image(ResourceLoader.sGameUpperBackground);
		mUpperBackground.setBounds(0, 0, mUpperBackground.getWidth(),
				mUpperBackground.getHeight());
		addActor(mUpperBackground);

		mSugarBar = SugarBar.createSugarBar(5);
		mSugarBar.setPosition(20, 130);

		mDancePattern = new DancePattern();
		mDancePattern.setPosition(Gdx.graphics.getWidth() - 400, 0);

		player = new Player();
		player.create();
		player.setPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 20);

		// HUD
		addActor(mSugarBar);
		addActor(mDancePattern);
		this.mPointsLabel = new Label("Points: 0", ResourceLoader.SKIN);
		this.mPointsLabel.setPosition(Gdx.graphics.getWidth() / 2
				- mPointsLabel.getWidth() / 2, Gdx.graphics.getHeight()
				- mPointsLabel.getHeight() - 20);
		addActor(mPointsLabel);
		this.mComboLabel = new Label("COMBO 0", ResourceLoader.sComboSkin);
		this.mComboLabel.setPosition(Gdx.graphics.getWidth() / 4
				- mComboLabel.getWidth() / 2, Gdx.graphics.getHeight()
				- mComboLabel.getHeight() - 150);
		addActor(mComboLabel);
		
		// Let the music
		mCurrentSong = SongFactory.createSong1(this, Difficulty.easy);
		
		//Implementing background labels
		mRocketReachedSkyLabel = new Label("ROCKET START +500 POINTS"
				, ResourceLoader.sComboSkin);
		mRocketReachedSkyLabel.addAction(Actions.alpha(0f));
		mRocketReachedSkyLabel.setPosition(Gdx.graphics.getWidth() / 2
				- mRocketReachedSkyLabel.getWidth() / 2, Gdx.graphics.getHeight()
				- mRocketReachedSkyLabel.getHeight() - 20);
		addActor(mRocketReachedSkyLabel);
	}

	/****************************************************************************
	 * getter and setter
	 ****************************************************************************/
	public float getSugarBarValue() {
		return mSugarBar.getValue();
	}

	public void setSugarBar(float value) {
		if (value < 0) {
			mHighscorePoints = mPlayerPoints;
		}
		mSugarBar.setValue(value);
	}

	public int getPlayerPoints() {
		return this.mPlayerPoints;
	}

	public float getMultiplicator() {
		return this.mMultiplicator;
	}
	
	/****************************************************************************
	 * methods
	 ****************************************************************************/
	
	public void checkArrows(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
			mDancePattern.checkArrow(StepType.left);
			break;
		case Input.Keys.UP:
			mDancePattern.checkArrow(StepType.up);
			break;
		case Input.Keys.DOWN:
			mDancePattern.checkArrow(StepType.down);
			break;
		case Input.Keys.RIGHT:
			mDancePattern.checkArrow(StepType.right);
			break;
		}
	}

	public void gameOver() {
		boolean inHighscore = mHighscore.insertScore(mHighscorePoints);
		mHighscore.saveHighscoreToPreferences();
		mHighscore.refreshLabels();
		mPlayerPoints = mHighscorePoints = 0;
		mSugarBar.clearActions();
		mSugarBar.setValue(0.5f);
		mDancePattern.clear();
		mDancePattern.createLabel();
		if (inHighscore)
			SoundEffectPlayer.Play(Effect.highscore);
		else
			SoundEffectPlayer.Play(Effect.looser);
		mGameJam6.startHighscore();
		mCurrentSong.stop();

	}
	
	public void successGameOver(int finalscore)
	{
		boolean inHighscore = mHighscore.insertScore(finalscore);
		mHighscore.saveHighscoreToPreferences();
		mHighscore.refreshLabels();
		mPlayerPoints = mHighscorePoints = 0;
		mSugarBar.clearActions();
		mSugarBar.setValue(0.5f);
		mDancePattern.clear();
		mDancePattern.createLabel();
		if (inHighscore)
			SoundEffectPlayer.Play(Effect.highscore);
		else
			SoundEffectPlayer.Play(Effect.looser);
		mGameJam6.startHighscore();
	}

	@Override
	public void fireStep(IStep step) {
		mDancePattern.fireArrow(step.getType(), step.getTargetTime());
	}

	/**
	 * was fired if the player miss a combo!
	 */
	public void miss() {
		mRocket1.crashRocket();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta); 
		
		//Calculate the multiplicator
		mMultiplicator = 1.0f + ((int)(mDancePattern.getComboCounter()*10)/10.0f);
		
		if (mSugarBar.getAnimationLevel() == 0) {
			player.setState(DanceStyle.losing);
		} else if (mSugarBar.getAnimationLevel() == 1) {

			player.setState(DanceStyle.level1);
		} else if (mSugarBar.getAnimationLevel() == 2) {

			player.setState(DanceStyle.level2);
		} else if (mSugarBar.getAnimationLevel() == 3) {

			player.setState(DanceStyle.level3);
		}  else if (mSugarBar.getAnimationLevel() == 4) {

			player.setState(DanceStyle.level4);
		}    else if (mSugarBar.getAnimationLevel() == 5) {

			player.setState(DanceStyle.level5);
		}  
		
		if (mDancePattern.getComboCounter() > 0) {
			mComboLabel.setText("COMBO " + mDancePattern.getComboCounter() + "\nx"+mMultiplicator);
		} else {
			mComboLabel.setText("");
		}
		
		mCurrentSong.update(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		shaderBlurIntensity -= Gdx.graphics.getDeltaTime() / 2;
		if (shaderBlurIntensity < 0) {
			shaderBlurIntensity = 0f;
		}
		shaderBrightIntensity -= Gdx.graphics.getDeltaTime() / 2;
		if (shaderBrightIntensity < 0) {
			shaderBrightIntensity = 0f;
		}
		shaderRetroIntensity -= Gdx.graphics.getDeltaTime() / 2;
		if (shaderRetroIntensity < 0) {
			shaderRetroIntensity = 0f;
		}
		shader.begin(0f, 0.5f, 0.5f, shaderBlurIntensity, 1f + shaderBrightIntensity, shaderRetroIntensity);
		super.draw(batch, parentAlpha);
		player.render();
		shader.end();
	}

	public void setPlayerPoints(int points) {
		this.mPlayerPoints = points;
		mPointsLabel.setText("Points: " + this.mPlayerPoints);
		
		switch(mDancePattern.getComboCounter()) {
			case 9:
				SoundEffectPlayer.Play(Effect.great);
				break;
			case 19:
				SoundEffectPlayer.Play(Effect.awesome);
				break;
			case 39:
				SoundEffectPlayer.Play(Effect.fantastic);
				break;
			case 59:
				SoundEffectPlayer.Play(Effect.brilliant);
				break;
			case 99:
				SoundEffectPlayer.Play(Effect.potoo);
				break;
		}
	}

	public void startGame() {
		mCurrentSong.start();
		mPlayerPoints = 0;
		mSugarBar.setValue(0.5f);
		mPointsLabel.setText("Points: 0");
	}
	
	public void stopCurrentSong() {
		mCurrentSong.stop();
	}

	@Override
	public void songEnd() {
		successGameOver(mPlayerPoints);
	}

	@Override
	public void fireBeat(IBeat beat) {
		shaderBlurIntensity = beat.getBlurIntensity();
		shaderBrightIntensity = beat.getBrightIntensity();
		shaderRetroIntensity = beat.getRetroIntensity();
	}
	
	public void resize(int width, int height) {
		shader.resize(width, height);
	}

	@Override
	public void rocketReachedSky() {
		System.out.println("ROCKET REACHED SKY!");
		mPlayerPoints += 500;
		mRocketReachedSkyLabel.addAction(Actions.sequence(
				Actions.fadeIn(0.25f),
				Actions.delay(2f),
				Actions.fadeOut(0.5f)
				));
	}
}
