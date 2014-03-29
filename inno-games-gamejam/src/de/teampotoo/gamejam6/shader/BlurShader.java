package de.teampotoo.gamejam6.shader;

import com.badlogic.gdx.Gdx;

public class BlurShader extends AbstractShader implements IBlurShader {

	private float mOriginX;
	private float mOriginY;
	private float mBlur;
	private float mBright;
	private float mRetro;
	private float mWidthRatio;
	private float mHeightRatio;
	
	private BlurShader() {
		super(Gdx.files.internal("data/shader/post.vsh"), Gdx.files.internal("data/shader/blur.fsh"));
	}
	
	public static IBlurShader newInstance() {
		return new BlurShader();
	}
	
	@Override
	public void begin(float deltaTime, float originX, float originY, float blur, float bright, float retro) {
		super.begin(deltaTime);
		mOriginX = originX;
		mOriginY = originY;
		mBlur = blur;
		mBright = bright;
		mRetro = retro;
	}

	@Override
	void setUniforms() {
		mProgram.setUniform2fv("u_radial_origin", new float[]{mOriginX, mOriginY}, 0, 2);
		mProgram.setUniform2fv("u_radial_size", new float[]{1f / mWidth, 1f / mHeight}, 0, 2);
		mProgram.setUniformf("u_radial_blur", mBlur);
		mProgram.setUniformf("u_radial_bright", mBright);
		mProgram.setUniformf("u_width_ratio", (1f / mWidth) * (1 + mRetro * 20f));
		mProgram.setUniformf("u_height_ratio", (1f / mHeight) * (1 + mRetro * 20f));
	}
}
