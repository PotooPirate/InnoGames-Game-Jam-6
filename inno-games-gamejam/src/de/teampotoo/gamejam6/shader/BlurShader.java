package de.teampotoo.gamejam6.shader;

import com.badlogic.gdx.Gdx;

public class BlurShader extends AbstractShader implements IBlurShader {

	private BlurShader() {
		super(Gdx.files.internal("data/shader/blur.vsh"), Gdx.files.internal("data/shader/blur.fsh"));
	}
	
	public static IBlurShader newInstance() {
		return new BlurShader();
	}
	
	@Override
	public void begin(float deltaTime, float intensity, float originX, float originY, float blur, float bright) {
		super.begin(deltaTime, intensity);
		mProgram.setUniform2fv("u_radial_origin", new float[]{originX, originY}, 0, 2);
		mProgram.setUniform2fv("u_radial_size", new float[]{1f / mWidth, 1f / mHeight}, 0, 2);
		mProgram.setUniformf("u_radial_blur", blur);
		mProgram.setUniformf("u_radial_bright", bright);
	}
}
