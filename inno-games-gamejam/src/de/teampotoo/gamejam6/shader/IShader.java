package de.teampotoo.gamejam6.shader;

public interface IShader {

	public void begin(float deltaTime);
	
	public void end();
	
	public void resize(int width, int height);
	
}
