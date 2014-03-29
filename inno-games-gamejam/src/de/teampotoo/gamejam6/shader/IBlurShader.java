package de.teampotoo.gamejam6.shader;

public interface IBlurShader {
	
	public void begin(float deltaTime, float originX, float originY, float blur, float bright);

	public void begin(float deltaTime);
	
	public void end();
	
	public void resize(int width, int height);
	
}
