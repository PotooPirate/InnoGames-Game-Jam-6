package de.teampotoo.gamejam6.game.gui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
	private ShapeRenderer mDebugRenderer;
	private int mStepCounter;
	
	public DancePattern() {
		mArrows = new ArrayList<Arrow>();
		mStepCounter = 1;
		mBackground = new Image(ResourceLoader.sDancePatternBackground);
		addActor(mBackground);
		
		mPerfect = new Rectangle(0, 540, 400, 20);
		mGood = new Rectangle(0, 520, 400, 60);
		mBad = new Rectangle(0, 500, 400, 100);
		
		addAction(Actions.alpha(0.75f));
		
		mDebugRenderer = new  ShapeRenderer();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		batch.end();
		mDebugRenderer.begin(ShapeType.Line);
		mDebugRenderer.setColor(1f, 0f, 0f, 1f);
		mDebugRenderer.rect(mBad.x+getX(), mBad.y+getY(), mBad.width, mBad.height);
		mDebugRenderer.setColor(1f, 0.5f, 0f, 1f);
		mDebugRenderer.rect(mGood.x+getX(), mGood.y+getY(), mGood.width, mGood.height);
		mDebugRenderer.setColor(0f, 1f, 0f, 1f);
		mDebugRenderer.rect(mPerfect.x+getX(), mPerfect.y+getY(), mPerfect.width, mPerfect.height);
		mDebugRenderer.end();
		batch.begin();
	}

	public void fireArrow(IStep.StepType direction, float targetTime) {
		mStepCounter++;
		Arrow arrow = new Arrow(direction, targetTime, mStepCounter);
		arrow.setShapeRenderer(mDebugRenderer);
		addActor(arrow);
		mArrows.add(arrow);
	}
	
	public void removeArrow(Arrow arrow) {
		mArrows.remove(arrow);
	}
	
	public void checkArrow(IStep.StepType direction) {
		for(Arrow a : mArrows) {
			if(a.getStepType().equals(direction)) {
				float centerX = a.getCenterX() ;
				float centerY = a.getCenterY() ;
				if(mPerfect.contains(centerX, centerY)) {
					System.out.println("PERFECT");
					a.setColor(0, 1f, 0, 1f);
				}else if(mGood.contains(centerX, centerY)) {
					System.out.println("GOOD");
					a.setColor(0.5f, 1f, 0, 1f);
				}else if(mBad.contains(centerX, centerY)) {
					System.out.println("BAD");
					a.setColor(1f, 0, 0, 1f);
				}
			}
		}
	}
}
