package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.helper.ResourceLoader;

public class Arrow extends Group {
	
	public enum Direction{
		left, right, up, down
	}
	
	private Image mArrow;
	
	public Arrow(Direction dir, float targetTime) {
		mArrow = new Image(ResourceLoader.sArrow);
		addActor(mArrow);
		
		switch(dir) {
			case left:
				mArrow.setPosition(0, -mArrow.getHeight());
				break;
			case up:
				mArrow.setPosition(100, -mArrow.getHeight());
				break;
			case down:
				mArrow.setPosition(200, -mArrow.getHeight());
				break;
			case right:
			default:
				mArrow.setPosition(300, -mArrow.getHeight());
				break;
		}
		
		addAction(Actions.moveBy(0, 600, targetTime));
	}
}
