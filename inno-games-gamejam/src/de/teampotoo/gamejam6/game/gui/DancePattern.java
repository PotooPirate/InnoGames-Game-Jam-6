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

import de.teampotoo.gamejam6.game.GameScreen;
import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.song.IStep;

public class DancePattern extends Group {

	/****************************************************************************
	 * variables
	 ****************************************************************************/
	private Rectangle mPerfect, mGood, mBad;
	private List<Arrow> mArrows;
	private ShapeRenderer mDebugRenderer;
	private int mStepCounter;
	private int mComboCounter;

	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	public DancePattern() {
		mArrows = new ArrayList<Arrow>();
		mStepCounter = 0;
		mComboCounter = 0;

		mPerfect = new Rectangle(0, 540, 400, 20);
		mGood = new Rectangle(0, 520, 400, 60);
		mBad = new Rectangle(0, 500, 400, 100);

		addAction(Actions.alpha(0.75f));

		mDebugRenderer = new ShapeRenderer();
	}

	/****************************************************************************
	 * getter and setter
	 ****************************************************************************/

	public int getComboCounter() {
		return mComboCounter;
	}
	
	/****************************************************************************
	 * methods
	 ****************************************************************************/

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(ResourceLoader.sDancePatternBackground, getX(), getY());
		super.draw(batch, parentAlpha);
		batch.end();
		mDebugRenderer.begin(ShapeType.Line);
		mDebugRenderer.setColor(1f, 0f, 0f, 1f);
		mDebugRenderer.rect(mBad.x + getX(), mBad.y + getY(), mBad.width,
				mBad.height);
		mDebugRenderer.setColor(1f, 0.5f, 0f, 1f);
		mDebugRenderer.rect(mGood.x + getX(), mGood.y + getY(), mGood.width,
				mGood.height);
		mDebugRenderer.setColor(0f, 1f, 0f, 1f);
		mDebugRenderer.rect(mPerfect.x + getX(), mPerfect.y + getY(),
				mPerfect.width, mPerfect.height);
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

	public void addComboCounter() {
		this.mComboCounter++;
	}
	
	public void resetComboCounter() {
		this.mComboCounter = 0;
	}
	
	public void removeArrow(Arrow arrow) {
		mArrows.remove(arrow);
	}

	public void checkArrow(IStep.StepType direction) {
		boolean hit = false;
		for (Arrow a : mArrows) {
			if (a.getStepType().equals(direction)) {
				float centerX = a.getCenterX();
				float centerY = a.getCenterY();
				if (mPerfect.contains(centerX, centerY) && a.isActive()) {
					GameScreen parent = (GameScreen) getParent();
					parent.setSugarBar(parent.getSugarBarValue() + 0.025f);
					parent.setPlayerPoints(parent.getPlayerPoints() + 20);
					a.getArrowImage().setColor(0, 1f, 0, 1f);
					a.setActive(false);
					hit = true;
					addComboCounter();
				} else if (mGood.contains(centerX, centerY) && a.isActive()) {
					GameScreen parent = (GameScreen) getParent();
					parent.setSugarBar(parent.getSugarBarValue() + 0.025f);
					parent.setPlayerPoints(parent.getPlayerPoints() + 10);
					a.getArrowImage().setColor(1f, 0.5f, 0, 1f);
					a.setActive(false);
					hit = true;
					addComboCounter();
				} else if (mBad.contains(centerX, centerY) && a.isActive()) {
					GameScreen parent = (GameScreen) getParent();
					parent.setSugarBar(parent.getSugarBarValue() + 0.025f);
					parent.setPlayerPoints(parent.getPlayerPoints() + 5);
					a.getArrowImage().setColor(1f, 0, 0, 1f);
					a.setActive(false);
					hit = true;
					addComboCounter();
				}
			}
		}
		if (!hit) {
			GameScreen parent = (GameScreen) getParent();
			parent.setSugarBar(parent.getSugarBarValue() - 0.10f);
			if (parent.getPlayerPoints() - 5 >= 0) {
				parent.setPlayerPoints(parent.getPlayerPoints() - 5);
			}
		}
	}
}
