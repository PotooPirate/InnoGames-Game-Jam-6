package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.song.IStep;

public class Arrow extends Group {
	
	private Image mArrow;
	private IStep.StepType mStepType;
	
	public Arrow(IStep.StepType dir, float targetTime) {
		mArrow = new Image(ResourceLoader.sArrow);
		mStepType = dir;
		addActor(mArrow);
		
		switch(dir) {
			case left:
				mArrow.setPosition(0, -mArrow.getHeight());
				mArrow.setOrigin(50, 50);
				mArrow.setRotation(180);
				break;
			case up:
				mArrow.setPosition(100, -mArrow.getHeight());
				mArrow.setOrigin(50, 50);
				mArrow.setRotation(90);
				break;
			case down:
				mArrow.setPosition(200, -mArrow.getHeight());
				mArrow.setOrigin(50, 50);
				mArrow.setRotation(-90);
				break;
			case right:
			default:
				mArrow.setPosition(300, -mArrow.getHeight());
				break;
		}
		
		SequenceAction seq = new SequenceAction(Actions.moveBy(0, 600, targetTime),
				Actions.parallel(Actions.moveBy(0, 150, 1f),
				Actions.fadeOut(1f)),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						deleteArrow();
					}
				}));
		
		addAction(seq);
	}
	
	public IStep.StepType getStepType() {
		return mStepType;
	}

	private void deleteArrow() {
		getParent().removeActor(this);
	}
}
