package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.game.GameScreen;
import de.teampotoo.gamejam6.helper.ResourceLoader;

public class SugarBar extends Group {

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private Image mBackground;
	private Image mForderground;

	private float mValue;
	private int animationlevels;

	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	public static SugarBar createSugarBar(int animationLevels) {
		SugarBar result = new SugarBar(animationLevels);
		return result;
	}

	private SugarBar(int animationLevels) {
		this.animationlevels = animationLevels;
		mBackground = new Image(ResourceLoader.sSugarbarBackground);
		mForderground = new Image(ResourceLoader.sSugarbarForderground);
		mForderground.setScaleY(0f);

		addActor(mBackground);
		addActor(mForderground);

		setValue(0.5f);
		addAction(Actions.alpha(0.75f));
	}

	/****************************************************************************
	 * setter and getter
	 ****************************************************************************/

	public void setValue(float value) {
		if (value >= 0 && value <= 1) {
			this.mValue = value;
			mForderground.addAction(Actions.scaleTo(1, value, 0.2f));
		} else if (value < 0) {
			this.mValue = 0;
			mForderground.addAction(Actions.sequence(
					Actions.scaleTo(1, 0, 0.2f), Actions.run(new Runnable() {
						@Override
						public void run() {
							((GameScreen) getParent()).gameOver();
						}
					})));
		} else if (value > 1) {
			this.mValue = 1;
			mForderground.addAction(Actions.scaleTo(1, 1, 0.2f));
		}
	}

	public boolean isEmpty() {
		return mValue <= 0;
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	@Override
	public void clearActions() {
		super.clearActions();
		mForderground.clearActions();
		mBackground.clearActions();
	}

	public float getValue() {
		return this.mValue;
	}

	public int getAnimationLevel() {
		return calcAnimationLevel();
	}

	private int calcAnimationLevel() {
		
		return (int) (animationlevels * mValue);
	}
}
