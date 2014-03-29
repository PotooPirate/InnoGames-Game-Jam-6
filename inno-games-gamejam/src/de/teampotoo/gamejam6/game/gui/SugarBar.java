package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.game.GameScreen;
import de.teampotoo.gamejam6.helper.ResourceLoader;

public class SugarBar extends Group {
	
	private Image mBackground;
	private Image mForderground;
	
	private float mValue;
	private short animationlevels;
	
	public static SugarBar createSugarBar(short animationLevels){
		animationLevels = animationLevels;
		return new SugarBar();
	}
	
	private SugarBar() {
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
	
	public float getAnimationLevel(){
		return calcAnimationLevel();
	}
	
	private float calcAnimationLevel(){
		
	}
	
	public void setValue(float value) {
		if(value >= 0 && value <= 1) {
			this.mValue = value;
			mForderground.addAction(Actions.scaleTo(1, value, 0.2f));
		}else if(value < 0){
			this.mValue = 0;
			mForderground.addAction(Actions.sequence(Actions.scaleTo(1, 0, 0.2f),
					Actions.run(new Runnable() {
						@Override
						public void run() {
							((GameScreen)getParent()).gameOver();
						}
					})));
		}else if(value > 1){
			this.mValue = 1;
			mForderground.addAction(Actions.scaleTo(1, 1, 0.2f));
		}
	}
	
	public boolean isEmpty() {
		return mValue <= 0;
	}
	
}
