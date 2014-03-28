package de.teampotoo.gamejam6.shader;

public interface IShader {

	public void begin(float deltaTime);
	
	public void begin(float deltaTime, float intensity);
	
	public void end();
	
}
