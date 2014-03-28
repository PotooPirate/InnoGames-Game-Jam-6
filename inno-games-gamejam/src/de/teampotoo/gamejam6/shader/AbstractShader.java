package de.teampotoo.gamejam6.shader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

abstract class AbstractShader implements IShader {
	
	private final ShaderProgram mProgram;
	private final FrameBuffer mFBO;
	private final TextureRegion mTextureRegion;
	private final SpriteBatch mBatch;
	
	private float mTime;
	
	protected float mIntensity;
	
	protected AbstractShader(FileHandle vertexShader, FileHandle fragmentShader) {
 		mProgram = new ShaderProgram(vertexShader, fragmentShader);
		mFBO = new FrameBuffer(Format.RGB565, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		mBatch = new SpriteBatch();
		mTextureRegion = new TextureRegion(mFBO.getColorBufferTexture());
		mTextureRegion.flip(false, true);
		mTime = 0f;
		mIntensity = 0f;
	}
	
	@Override
	public void begin(float deltaTime) {
		begin(deltaTime, 0f);
	}
	
	@Override
	public void begin(float deltaTime, float intensity) {
		mFBO.begin();
		mTime += deltaTime;
		mIntensity = intensity;
	    mProgram.setUniformf("u_time", mTime);
		mProgram.setUniformf("u_intensity", mIntensity);
	}
	
	@Override
	public void end() {
		mFBO.end();
		draw();
	}
	
	abstract void draw();
}
