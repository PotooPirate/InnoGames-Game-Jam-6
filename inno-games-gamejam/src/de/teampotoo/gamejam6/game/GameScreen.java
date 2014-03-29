package de.teampotoo.gamejam6.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.game.gui.DancePattern;
import de.teampotoo.gamejam6.game.gui.Player;
import de.teampotoo.gamejam6.game.gui.Player.DanceStyle;
import de.teampotoo.gamejam6.game.gui.SugarBar;
import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.highscore.HighscoreScreen;
import de.teampotoo.gamejam6.shader.IBlurShader;
import de.teampotoo.gamejam6.shader.ShaderFactory;
import de.teampotoo.gamejam6.song.ISong;
import de.teampotoo.gamejam6.song.IStep;
import de.teampotoo.gamejam6.song.IStep.StepType;
import de.teampotoo.gamejam6.song.SongFactory;

public class GameScreen extends Group implements IGameScreen {

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private GameJam6 mGameJam6;
	private HighscoreScreen mHighscore;
	private Image mBackground;

	private int mPlayerPoints; // current points while the game runs
	private int mHighscorePoints; // Frozen points after the game finished
	
	//Special HACK
	private Matrix4 mx4Font = new Matrix4();
	private SpriteBatch spriteFont;
	
	// player stuff
	private Player player;

	// HUD
	private SugarBar mSugarBar;
	private DancePattern mDancePattern;
	private Label mPointsLabel;
	private Label mComboLabel;
	
	// Music
	private ISong mCurrentSong;

	// Blur shader
	private IBlurShader mBlurShader = ShaderFactory.createBlurShader();
	private IBlurShader mPlayerBlurShader = ShaderFactory.createBlurShader();
	private SpriteBatch mPlayerBatch = new SpriteBatch();

	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	public GameScreen(GameJam6 gameJam6, HighscoreScreen highscore) {
		this.mGameJam6 = gameJam6;
		this.mHighscore = highscore;
		this.mPlayerPoints = 0;

		mBackground = new Image(ResourceLoader.BACKGROUND);
		mBackground.setBounds(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		addActor(mBackground);

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
		addBackButton();
		this.mPointsLabel = new Label("Punkte: 0", ResourceLoader.SKIN);
		this.mPointsLabel.setPosition(Gdx.graphics.getWidth() / 2
				- mPointsLabel.getWidth() / 2, Gdx.graphics.getHeight()
				- mPointsLabel.getHeight() - 20);
		addActor(mPointsLabel);

		spriteFont = new SpriteBatch();
		
		// Let the music
		mCurrentSong = SongFactory.createSong1(this, Difficulty.easy);

		mBlurShader = ShaderFactory.createBlurShader();
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
		mHighscore.insertScore(mHighscorePoints);
		mHighscore.saveHighscoreToPreferences();
		mHighscore.refreshLabels();
		mPlayerPoints = mHighscorePoints = 0;
		mSugarBar.clearActions();
		mSugarBar.setValue(0.5f);
		mDancePattern.clear();
		mGameJam6.startHighscore();
	}

	@Override
	public void fireStep(IStep step) {
		mDancePattern.fireArrow(step.getType(), step.getTargetTime());
	}

	private void addBackButton() {
		Image backButton = new Image(ResourceLoader.BUTTON);
		backButton.setWidth(100);
		backButton.setHeight(50);
		backButton.setPosition(20,
				Gdx.graphics.getHeight() - backButton.getHeight() - 20);
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

	@Override
	public void act(float delta) {
		super.act(delta);

		System.out.println("Level: " + mSugarBar.getAnimationLevel());
		
		if (mSugarBar.getAnimationLevel() == 1) {
			player.setState(DanceStyle.light);
		} else if (mSugarBar.getAnimationLevel() == 2) {

			player.setState(DanceStyle.light);
		} else if (mSugarBar.getAnimationLevel() == 3) {

			player.setState(DanceStyle.light);
		} else if (mSugarBar.getAnimationLevel() == 4) {

			player.setState(DanceStyle.middle);
		}  else if (mSugarBar.getAnimationLevel() == 5) {

			player.setState(DanceStyle.crazy);
		}  
		
		mCurrentSong.update(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		mBlurShader.begin(Gdx.graphics.getDeltaTime(), 0.5f, 0.5f, 0f, 1f);
		super.draw(batch, parentAlpha);
		mBlurShader.end();

		player.render();
//		
//		if(mDancePattern.getComboCounter() > 0) {
		spriteFont.setTransformMatrix(mx4Font);
		spriteFont.begin();
		ResourceLoader.sComboFont.draw(spriteFont,
				"COMBO " + mDancePattern.getComboCounter(),
				400, 400);
		spriteFont.end();
//		}
	}

	public void setPlayerPoints(int points) {
		this.mPlayerPoints = points;
		mPointsLabel.setText("Punkte: " + this.mPlayerPoints);
	}

	public void startGame() {
		mCurrentSong.start();
		mPlayerPoints = 0;
		mSugarBar.setValue(0.5f);
		mPointsLabel.setText("Punkte: 0");
	}

	@Override
	public void songEnd() {
		mCurrentSong.start();
	}
}
