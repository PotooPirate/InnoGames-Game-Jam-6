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

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	// Sugarbar
	public static TextureRegion sSugarbarForderground;
	public static TextureRegion sSugarbarBackground;

	// DancePattern
	public static TextureRegion sDancePatternBackground;
	public static TextureRegion sArrow;

	public static TextureRegion sGameLowerBackground;
	public static TextureRegion sGameUpperBackground;
	public static TextureRegion sRocket;

	public static Skin SKIN;
	public static Skin SKIN_BIG;
	public static Skin sComboSkin;
	public static Skin sComboSkinBig;
	public static BitmapFont sComboFont;
	public static BitmapFont sComboFontBig;
	public static BitmapFont FONT;
	public static BitmapFont FONT_BIG;
	public static BitmapFont BUTTON_FONT;
	public static TextureRegion BUTTON;
	public static TextureRegion BUTTON_PRESSED;

	/****************************************************************************
	 * loader
	 ****************************************************************************/

	public static void loadResources() {
		sSugarbarBackground = new TextureRegion(new Texture(
				Gdx.files.internal("data/gfx/sugarbar_background.png")));
		sSugarbarForderground = new TextureRegion(new Texture(
				Gdx.files.internal("data/gfx/sugarbar_forderground.png")));

		sRocket = new TextureRegion(new Texture(
				Gdx.files.internal("data/gfx/rocket.png")));
		
		
		sDancePatternBackground = new TextureRegion(new Texture(
				Gdx.files.internal("data/gfx/dancepattern_background.png")));
		sArrow = new TextureRegion(new Texture(
				Gdx.files.internal("data/gfx/arrow.png")));

		sGameLowerBackground = new TextureRegion(new Texture(
				Gdx.files.internal("data/gfx/game_lowerbackground.png")));
		sGameUpperBackground = new TextureRegion(new Texture(
				Gdx.files.internal("data/gfx/game_upperbackground.png")));

		BUTTON = new TextureRegion(new Texture(
				Gdx.files.internal("data/buttons/Button.png")));
		BUTTON_PRESSED = new TextureRegion(new Texture(
				Gdx.files.internal("data/buttons/ButtonPressed.png")));

		//Creating the combo skin
		sComboSkin = new Skin();
		sComboSkinBig = new Skin();
		
		sComboFont = new BitmapFont(Gdx.files.internal("data/font/ComboFont.fnt"));
		sComboSkin.add("combo_font", sComboFont);
		
		LabelStyle comboLabelStyle = new LabelStyle();
		comboLabelStyle.font = sComboSkin.get("combo_font", BitmapFont.class);
		sComboSkin.add("default", comboLabelStyle);
		 
		sComboFontBig = new BitmapFont(Gdx.files.internal("data/font/ComboFontBog.fnt"));
		sComboSkinBig.add("combo_font_big", sComboFontBig);
		
		LabelStyle comboBigLabelStyle = new LabelStyle();
		comboBigLabelStyle.font = sComboSkinBig.get("combo_font_big", BitmapFont.class);
		sComboSkinBig.add("default", comboBigLabelStyle); 
		
		// Creating the Skin
		SKIN = new Skin();
		SKIN_BIG = new Skin();
 
		FONT = new BitmapFont(Gdx.files.internal("data/font/UnFont.fnt"));
		SKIN.add("button_font", FONT);

		FONT_BIG = new BitmapFont(Gdx.files.internal("data/font/UnFontBig.fnt"));
		SKIN_BIG.add("button_font_big", FONT_BIG);

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

		LabelStyle labelStyleBig = new LabelStyle();
		labelStyleBig.font = SKIN_BIG.get("button_font_big", BitmapFont.class);
		SKIN_BIG.add("default", labelStyleBig);
	}
}
