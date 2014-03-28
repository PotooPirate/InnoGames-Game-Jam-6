package de.teampotoo.gamejam6.shader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

abstract class AbstractShader implements IShader {
	
	protected ShaderProgram mProgram;
	
	private SpriteBatch mBatch;
	private FrameBuffer mFBO;
	private TextureRegion mTextureRegion;
	
	private float mTime;
	
	protected int mWidth;
	protected int mHeight;
	
	protected AbstractShader(FileHandle vertexShader, FileHandle fragmentShader) {
 		mProgram = new ShaderProgram(vertexShader, fragmentShader);
		mBatch = new SpriteBatch();
		mBatch.setShader(mProgram); 		
 		mWidth = Gdx.graphics.getWidth();
 		mHeight = Gdx.graphics.getHeight();
 		mFBO = new FrameBuffer(Format.RGB565, mWidth, mHeight, false);
		mTextureRegion = new TextureRegion(mFBO.getColorBufferTexture());
		mTextureRegion.flip(false, true);
		mTime = 0f;
	}
	
	@Override
	public void begin(float deltaTime, float intensity) {
		mFBO.begin();
		mTime += deltaTime;
	    //mProgram.setUniformf("u_time", mTime);
		//mProgram.setUniformf("u_intensity", intensity);
	}
	
	@Override
	public void end() {
		mFBO.end();
	    mBatch.begin();
	    mBatch.draw(mTextureRegion, 0, 0, mWidth, mHeight);               
	    mBatch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		mWidth = width;
		mHeight = height;
		mFBO = new FrameBuffer(Format.RGB565, mWidth, mHeight, false);
		mTextureRegion = new TextureRegion(mFBO.getColorBufferTexture());
		mTextureRegion.flip(false, true);
	}
}
