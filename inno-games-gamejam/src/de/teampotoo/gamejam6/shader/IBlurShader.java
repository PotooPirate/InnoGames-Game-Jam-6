package de.teampotoo.gamejam6.shader;

public interface IBlurShader extends IShader {
	
	public void begin(float deltaTime, float intensity, float originX, float originY, float blur, float bright);

}
