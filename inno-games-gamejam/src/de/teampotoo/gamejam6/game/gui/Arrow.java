package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.helper.ResourceLoader;
import de.teampotoo.gamejam6.song.IStep;

public class Arrow extends Group {
	
	private Image mArrow;
	private IStep.StepType mStepType;
	private int mId;
	
	public Arrow(IStep.StepType dir, float targetTime, int id) {
		mArrow = new Image(ResourceLoader.sArrow);
		mStepType = dir;
		mId = id;
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

	public Image getArrowImage() {
		return mArrow;
	}
	
	public IStep.StepType getStepType() {
		return mStepType;
	}

	private void deleteArrow() {
		((DancePattern)getParent()).removeArrow(this);
		getParent().removeActor(this);
	}
	
	public float getCenterX() {
		return mArrow.getX()+50+ getX();
	}

	public float getCenterY() {
		return mArrow.getY()+50+ getY();
	}
	
	private ShapeRenderer sr;
	public void setShapeRenderer(ShapeRenderer sr) {
		this.sr = sr;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(sr != null) {
			batch.end();
			sr.begin(ShapeType.Line);
			sr.setColor(1f, 0f, 0f, 1f);
			sr.circle(getCenterX()+getParent().getX(), getCenterY()+getParent().getY(), 15);
			sr.end();
			batch.begin();
		}
	}
}
