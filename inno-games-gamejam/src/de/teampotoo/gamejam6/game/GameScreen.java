package de.teampotoo.gamejam6.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.game.gui.Player;
import de.teampotoo.gamejam6.game.gui.DancePattern;
import de.teampotoo.gamejam6.game.gui.SugarBar;
import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.song.IStep;

public class GameScreen extends Group implements IGameScreen {

	private GameJam6 mGameJam6;
	private Image mBackground;

	// player stuff
	private Player player;
	
	//HUD
	private SugarBar mSugarBar;
	private DancePattern mDancePattern;
	
	// Blur shader
	private boolean blurShaderEnabled;
	private FrameBuffer blurShaderFBO;
	private TextureRegion blurShaderTextureRegion;
	private SpriteBatch blurShaderFBOBatch;
	private ShaderProgram blurShaderProgram;
	private float blurShaderTime;
	
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
		
		initBlurShader();
	}
	
	private void initBlurShader() {
		blurShaderEnabled = false;
 		blurShaderFBOBatch = new SpriteBatch();
 		blurShaderProgram = new ShaderProgram(Gdx.files.internal("data/shader/blur.vsh"), Gdx.files.internal("data/shader/blur.fsh"));
		blurShaderFBO = new FrameBuffer(Format.RGB565, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		blurShaderTextureRegion = new TextureRegion(blurShaderFBO.getColorBufferTexture());
		blurShaderTextureRegion.flip(false, true);
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		player.render(batch);
	}
	
	@Override
	public void fireStep(IStep step) {
		
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
	public void draw(Batch batch, float parentAlpha) {
		blurShaderFBO.begin();
		blurShaderTime += Gdx.graphics.getDeltaTime();
		int width = Gdx.graphics.getWidth();
 	    int height = Gdx.graphics.getHeight();
		super.draw(batch, parentAlpha);
		blurShaderFBO.end();
		blurShaderProgram.begin();
	    blurShaderProgram.setUniform2fv("u_radial_origin", new float[]{0.5f, 0.5f}, 0, 2);
	    blurShaderProgram.setUniform2fv("u_radial_size", new float[]{1f / width, 1f / height}, 0, 2);
	    blurShaderProgram.setUniformf("u_radial_blur", 0f); // 0.5f + (MathUtils.sin(blurShaderTime * 10f) / 2));
	    blurShaderProgram.setUniformf("u_radial_bright", 1f);
	    blurShaderFBOBatch.setShader(blurShaderProgram);
	    blurShaderFBOBatch.begin();
	    blurShaderFBOBatch.draw(blurShaderTextureRegion, 0, 0, width, height);               
	    blurShaderFBOBatch.end();
	}
}
