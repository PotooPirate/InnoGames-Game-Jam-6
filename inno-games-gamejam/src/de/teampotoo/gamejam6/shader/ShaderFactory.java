package de.teampotoo.gamejam6.shader;

public final class ShaderFactory {

	private ShaderFactory() {}
	
	public static IBlurShader createBlurShader() {
		return BlurShader.newInstance();
	}
}
