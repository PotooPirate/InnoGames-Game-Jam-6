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
	public void begin(float deltaTime) {
		begin(deltaTime, 0f, 0f, 0f, 0f, 1f);
	}
	
	@Override
	public void begin(float deltaTime, float intensity, float originX, float originY, float blur, float bright) {
		super.begin(deltaTime, intensity);
	}

	@Override
	protected void draw() {
		// TODO Auto-generated method stub
		
	}
}
