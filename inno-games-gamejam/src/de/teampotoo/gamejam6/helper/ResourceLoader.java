package de.teampotoo.gamejam6.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ResourceLoader {
	
	//Sugarbar
	public static TextureRegion sSugarbarForderground;
	public static TextureRegion sSugarbarBackground;
	
	//DancePattern
	public static TextureRegion sDancePatternBackground;
	
	public static TextureRegion BACKGROUND;
	
	public static Skin SKIN;
	public static BitmapFont FONT;
	public static BitmapFont BUTTON_FONT;
	public static TextureRegion BUTTON;
	public static TextureRegion BUTTON_PRESSED;
	
	public static void loadResources() {	
		sSugarbarBackground = new TextureRegion(new Texture(Gdx.files.internal("data/gfx/sugarbar_background.png")));
		sSugarbarForderground = new TextureRegion(new Texture(Gdx.files.internal("data/gfx/sugarbar_forderground.png")));
		
		sDancePatternBackground = new TextureRegion(new Texture(Gdx.files.internal("data/gfx/dancepattern_background.png")));
		
		BACKGROUND = new TextureRegion(new Texture(Gdx.files.internal("data/gfx/background.png")));
		
		BUTTON = new TextureRegion(new Texture(Gdx.files.internal("data/buttons/Button.png")));
		BUTTON_PRESSED = new TextureRegion(new Texture(Gdx.files.internal("data/buttons/ButtonPressed.png")));
		
		//Creating the Skin
		SKIN = new Skin();

		FONT = new BitmapFont(Gdx.files.internal("data/font/ButtonFont.fnt"));
		SKIN.add("button_font", FONT);

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = new TextureRegionDrawable(BUTTON);
		textButtonStyle.down = new TextureRegionDrawable(BUTTON_PRESSED);
		textButtonStyle.over = new TextureRegionDrawable(BUTTON_PRESSED);
		textButtonStyle.checked = new TextureRegionDrawable(BUTTON_PRESSED);
		textButtonStyle.font = SKIN.get("button_font", BitmapFont.class);
		SKIN.add("default", textButtonStyle);

		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = SKIN.get("button_font", BitmapFont.class);
		SKIN.add("default", labelStyle);
	}
}
