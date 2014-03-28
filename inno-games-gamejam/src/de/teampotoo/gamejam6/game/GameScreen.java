package de.teampotoo.gamejam6.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class GameScreen implements Screen {

	private boolean blurShaderEnabled;
	private FrameBuffer blurShaderFBO;
	private TextureRegion blurShaderTextureRegion;
	private SpriteBatch blurShaderFBOBatch;
	private ShaderProgram blurShaderProgram;
	
	public GameScreen() {
		blurShaderEnabled = false;
 		blurShaderFBOBatch = new SpriteBatch();
 		blurShaderProgram = new ShaderProgram(Gdx.files.internal("data/shader/blur.vsh"), Gdx.files.internal("data/shader/blur.fsh"));
		blurShaderFBO = new FrameBuffer(Format.RGB565, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		blurShaderTextureRegion = new TextureRegion(blurShaderFBO.getColorBufferTexture());
		blurShaderTextureRegion.flip(false, true);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
