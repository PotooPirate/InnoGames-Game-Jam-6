package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.helper.ResourceLoader;

public class SugarBar extends Group {
	
	private Image mBackground;
	private Image mForderground;
	
	private float mValue;
	
	public SugarBar() {
		mBackground = new Image(ResourceLoader.sSugarbarBackground);
		mForderground = new Image(ResourceLoader.sSugarbarForderground);
		mForderground.setScaleY(0f);
		
		addActor(mBackground);
		addActor(mForderground);
		
		setValue(0.5f);
		addAction(Actions.alpha(0.75f));
	}
	
	public float getValue() {
		return this.mValue;
	}
	
	public void setValue(float value) {
		if(value >= 0 && value <= 1) {
			this.mValue = value;
			mForderground.addAction(Actions.scaleTo(1, value, 1.0f));
		}else if(value < 0){
			this.mValue = 0;
			mForderground.addAction(Actions.scaleTo(1, 0, 1.0f));
		}else if(value > 1){
			this.mValue = 1;
			mForderground.addAction(Actions.scaleTo(1, 1, 1.0f));
		}
	}
	
	public boolean isEmpty() {
		return mValue <= 0;
	}
	
}
