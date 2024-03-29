package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.game.GameScreen;
import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.song.IStep;

public class Arrow extends Group {

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private Image mArrow;
	private IStep.StepType mStepType;
	private int mId;
	private boolean mActive;
	
	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	public Arrow(IStep.StepType dir, float targetTime, int id) {
		mActive = true;
		mStepType = dir;
		mId = id;

		switch (dir) {
		case left:
			mArrow = new Image(ResourceLoader.sArrowLeft);
			setPosition(0, -mArrow.getHeight()); 
			break;
		case up:
			mArrow = new Image(ResourceLoader.sArrowUp);
			setPosition(100, -mArrow.getHeight()); 
			break;
		case down:
			mArrow = new Image(ResourceLoader.sArrowDown);
			setPosition(200, -mArrow.getHeight()); 
			break;
		case right:
			mArrow = new Image(ResourceLoader.sArrowRight);
			setPosition(300, -mArrow.getHeight()); 
			break;

		default:
			System.out.println("This should not happen, when building arrows");
			break;
		}

		addActor(mArrow);
		
		SequenceAction seq = new SequenceAction(Actions.moveBy(0, 700,
				targetTime), Actions.run(new Runnable() {
			@Override
			public void run() {
				if (isActive()) {
					GameScreen parent = (GameScreen) (getParent().getParent());
					parent.setSugarBar(parent.getSugarBarValue() - 0.05f);
					((DancePattern)getParent()).resetComboCounter();
				}
				mActive = false;
			}
		}), Actions.parallel(Actions.moveBy(0, 150, 1f), Actions.fadeOut(1f)),
				Actions.run(new Runnable() {
					@Override
					public void run() {
					deleteArrow();
					}
				}));
		addAction(seq);
	}

	/****************************************************************************
	 * getter and setter
	 ****************************************************************************/

	public boolean isActive() {
		return mActive;
	}

	public void setActive(boolean mActive) {
		this.mActive = mActive;
	}

	public Image getArrowImage() {
		return mArrow;
	}

	public IStep.StepType getStepType() {
		return mStepType;
	}

	public float getCenterX() {
		return getX() + 50;
	}

	public float getCenterY() {
		return getY() + 50;
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arrow other = (Arrow) obj;
		if (mId != other.mId)
			return false;
		return true;
	}

	private void deleteArrow() {
		((DancePattern) getParent()).removeArrow(this);
		getParent().removeActor(this);
	}

	private ShapeRenderer sr;

	public void setShapeRenderer(ShapeRenderer sr) {
		this.sr = sr;
	}

	/*
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (sr != null) {
			batch.end();
			sr.begin(ShapeType.Line);
			sr.setColor(1f, 0f, 0f, 1f);
			sr.circle(getCenterX() + getParent().getX(), getCenterY()
					+ getParent().getY(), 15);
			sr.end();
			batch.begin();
		}
	}
	*/
}
