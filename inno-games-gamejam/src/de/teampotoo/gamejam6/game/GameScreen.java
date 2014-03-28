package de.teampotoo.gamejam6.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.song.IStep;

public class GameScreen extends Group implements IGameScreen {

	private GameJam6 mGameJam6;
	
	// Blur shader
	private boolean blurShaderEnabled;
	private FrameBuffer blurShaderFBO;
	private TextureRegion blurShaderTextureRegion;
	private SpriteBatch blurShaderFBOBatch;
	private ShaderProgram blurShaderProgram;
	
	public GameScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;
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
	public void fireStep(IStep step) {
		
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

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
}
