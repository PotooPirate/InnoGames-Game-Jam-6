package de.teampotoo.gamejam6.shader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderFactory {

	// Blur shader
	private static boolean blurShaderEnabled;
	private static FrameBuffer blurShaderFBO;
	private static TextureRegion blurShaderTextureRegion;
	private static SpriteBatch blurShaderFBOBatch;
	private static ShaderProgram blurShaderProgram;
	private static float blurShaderTime;
}
