package de.teampotoo.gamejam6.shader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

abstract class AbstractShader {
	
	protected ShaderProgram mProgram;
	
	private SpriteBatch mBatch;
	private FrameBuffer mFBO;
	private TextureRegion mTextureRegion;
	
	protected float mTime;
	
	protected int mWidth;
	protected int mHeight;
	
	protected AbstractShader(FileHandle vertexShader, FileHandle fragmentShader) {
 		mProgram = new ShaderProgram(vertexShader, fragmentShader);
		mBatch = new SpriteBatch();
		mBatch.setShader(mProgram); 		
 		mWidth = Gdx.graphics.getWidth();
 		mHeight = Gdx.graphics.getHeight();
 		mFBO = new FrameBuffer(Format.RGBA8888, mWidth, mHeight, true);
		mTextureRegion = new TextureRegion(mFBO.getColorBufferTexture());
		mTextureRegion.flip(false, true);
		mTime = 0f;
	}
	
	public void begin(float deltaTime) {
		mFBO.begin();
		Gdx.gl.glClearColor(0.3f, 0.5f, 0.7f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		mTime += deltaTime;
	}
	
	public void end() {
		mFBO.end();
		mProgram.begin();
		mBatch.setShader(mProgram);
		setUniforms();
	    mBatch.begin();
	    mBatch.draw(mTextureRegion, 0, 0, mWidth, mHeight);
	    mBatch.end();
	}
	
	abstract void setUniforms();
	
	public void resize(int width, int height) {
		mWidth = width;
		mHeight = height;
		mFBO = new FrameBuffer(Format.RGBA8888, mWidth, mHeight, true);
		mTextureRegion = new TextureRegion(mFBO.getColorBufferTexture());
		mTextureRegion.flip(false, true);
	}
}
