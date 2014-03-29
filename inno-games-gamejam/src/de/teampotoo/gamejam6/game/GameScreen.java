package de.teampotoo.gamejam6.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.game.gui.Arrow;
import de.teampotoo.gamejam6.game.gui.DancePattern;
import de.teampotoo.gamejam6.game.gui.Player;
import de.teampotoo.gamejam6.game.gui.SugarBar;
import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.shader.IBlurShader;
import de.teampotoo.gamejam6.shader.ShaderFactory;
import de.teampotoo.gamejam6.song.ISong;
import de.teampotoo.gamejam6.song.IStep;
import de.teampotoo.gamejam6.song.IStep.StepType;
import de.teampotoo.gamejam6.song.Song;
import de.teampotoo.gamejam6.song.SongFactory;

public class GameScreen extends Group implements IGameScreen {

	private GameJam6 mGameJam6;
	private Image mBackground;

	// player stuff
	private Player player;
	
	//HUD
	private SugarBar mSugarBar;
	private DancePattern mDancePattern;
	
	//Music
	private ISong mCurrentSong;
	
	// Blur shader
	private IBlurShader mBlurShader = ShaderFactory.createBlurShader();
	private IBlurShader mPlayerBlurShader = ShaderFactory.createBlurShader();
	private SpriteBatch mPlayerBatch = new SpriteBatch();
	
	public GameScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;		
		mBackground = new Image(ResourceLoader.BACKGROUND);
		mBackground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		addActor(mBackground);
		
		mSugarBar = new SugarBar();
		mSugarBar.setPosition(20, 130);
		
		mDancePattern = new DancePattern();
		mDancePattern.setPosition(Gdx.graphics.getWidth() - 400, 0); 
		
		player = new Player();
		player.create();
		player.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 20);
		
		//HUD
		addActor(mSugarBar);
		addActor(mDancePattern);
		addBackButton();
		
		//Let the music
		mCurrentSong = SongFactory.createSong1(this);
		
		mBlurShader = ShaderFactory.createBlurShader();
	}

	public void checkArrows(int keycode) {
		switch(keycode) {
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
	
	@Override
	public void fireStep(IStep step) {
		mDancePattern.fireArrow(step.getType(), step.getTargetTime());
	}
	
	private void addBackButton() {
		Image backButton = new Image(ResourceLoader.BUTTON);
		backButton.setWidth(100);
		backButton.setHeight(50);
		backButton.setPosition(20, Gdx.graphics.getHeight() - backButton.getHeight() - 20);
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
		
		mCurrentSong.update(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		mBlurShader.begin(Gdx.graphics.getDeltaTime(), 0.5f, 0.5f, 0.1f, 1f);
		super.draw(batch, parentAlpha); 
		mBlurShader.end();
		
		player.render();
	}
	
	public void startMusic()
	{
		mCurrentSong.start();
	}

	@Override
	public void songEnd() {
		mCurrentSong.start();
	}
}
