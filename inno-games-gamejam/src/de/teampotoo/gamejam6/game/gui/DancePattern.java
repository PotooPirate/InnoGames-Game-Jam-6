package de.teampotoo.gamejam6.game.gui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.song.IStep;

public class DancePattern extends Group {
	private Image mBackground;
	
	private Rectangle mPerfect, mGood, mBad;
	private List<Arrow> mArrows;
	
	public DancePattern() {
		mArrows = new ArrayList<Arrow>();
		
		mBackground = new Image(ResourceLoader.sDancePatternBackground);
		addActor(mBackground);
		
		mPerfect = new Rectangle(0, 540, 400, 20);
		mGood = new Rectangle(0, 520, 400, 60);
		mBad = new Rectangle(0, 500, 400, 100);
		
		addAction(Actions.alpha(0.75f));
	}
	
	public void fireArrow(IStep.StepType direction, float targetTime) {
		Arrow arrow = new Arrow(direction, targetTime);
		addActor(arrow);
		mArrows.add(arrow);
	}
	
	public void checkArrow(IStep.StepType direction) {
		for(Arrow a : mArrows) {
			if(a.getStepType().equals(direction)) {
				float centerX = a.getX()+50;
				float centerY = a.getY()+50;
				if(mPerfect.contains(centerX, centerY)) {
					a.setColor(0, 1f, 0, 1f);
				}else if(mGood.contains(centerX, centerY)) {
					a.setColor(0.5f, 1f, 0, 1f);
				}else if(mBad.contains(centerX, centerY)) {
					a.setColor(1f, 0, 0, 1f);
				}
			}
		}
	}
}
