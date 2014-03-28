package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.helper.ResourceLoader;

public class SugarBar extends Group {
	
	private Image mBackground;
	private Image mForderground;
	
	private float value;
	
	public SugarBar() {
		mBackground = new Image(ResourceLoader.sSugarbarBackground);
		mForderground = new Image(ResourceLoader.sSugarbarForderground);
		
		addActor(mBackground);
		addActor(mForderground);
		
		setValue(0.5f);
	}
	
	public void setValue(float value) {
		this.value = value;
		mForderground.addAction(Actions.scaleTo(1, value, 1.0f));
	}
	
	public boolean isEmpty() {
		return value <= 0;
	}
	
}
