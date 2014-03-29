package de.teampotoo.gamejam6.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.helper.ResourceLoader;

public class SugarRocket extends Group {
	private static final float CHANGE_TIME = 0.05f;
	
	private Image mRocket;
	private Image[] mFireArray;
	private int mCurrentFire;
	private float changeCounter = CHANGE_TIME;
	private float mSpeed;
	   
	public SugarRocket(float x, float speed) {
	mRocket = new Image(ResourceLoader.sRockets[MathUtils.random(0, ResourceLoader.sRockets.length-1)]);
		addActor(mRocket);
		mSpeed = speed;
		
		mFireArray = new Image[] {
				new Image(ResourceLoader.sFire[0]),
				new Image(ResourceLoader.sFire[1]),
				new Image(ResourceLoader.sFire[2])
		};
		
		mCurrentFire = 0;
		
		setPosition(x, -mRocket.getHeight());
		
		mFireArray[0].setPosition(0, - mFireArray[0].getHeight());
		addActor(mFireArray[mCurrentFire]);
		addActor(mFireArray[1]);
		
		mFireArray[1].setPosition(0, - mFireArray[1].getHeight());
		mFireArray[1].setVisible(true);
		addActor(mFireArray[2]);
		
		mFireArray[2].setPosition(0, - mFireArray[2].getHeight());
		mFireArray[2].setVisible(true);
		startRocket();
	}
	
	public void startRocket() {
		addAction(Actions.sequence(Actions.moveBy(0, Gdx.graphics.getHeight() + mRocket.getHeight()*2
				, mSpeed, Interpolation.sineIn), Actions.delay(10f), Actions.run(new Runnable() {
					@Override
					public void run() {
						resetRocket();
					}
				})));
	}
	
	public void resetRocket() {
		setPosition((float) (Math.random()*800), -mRocket.getHeight());
		startRocket();
	}

	@Override
	public void act(float delta) {
		changeCounter -= delta;
		if(changeCounter < 0) {
			changeCounter = CHANGE_TIME;
			mFireArray[mCurrentFire].setVisible(false);
			mCurrentFire = com.badlogic.gdx.math.MathUtils.random(0, mFireArray.length-1);
			mFireArray[mCurrentFire].setVisible(true);
		}
		super.act(delta);
	}
	
	
}
