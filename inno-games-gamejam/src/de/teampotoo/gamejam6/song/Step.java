package de.teampotoo.gamejam6.song;

import com.badlogic.gdx.math.MathUtils;

import de.teampotoo.gamejam6.game.IGameScreen.Difficulty;

public class Step implements IStep {

	/****************************************************************************
	 * variables
	 ****************************************************************************/
	
	private final StepType mType;
	private final float mFireTime;
	private final float mTargetTime;
	
	private static final float EASY_SUBSTRACT = 3.0f;
	private static final float MEDIUM_SUBSTRACT = 2.0f;
	private static final float HARD_SUBSTRACT = 1.0f;
	private static final float REDIC_SUBSTRACT = 0.6f;
	
	/****************************************************************************
	 * constructor
	 ****************************************************************************/
	
	private Step(StepType type, float fireTime, float targetTime) {
		mType = type;
		mFireTime = fireTime;
		mTargetTime = targetTime;
	}
	
	public static IStep newInstance(StepType type, float timestamp, Difficulty difficulty) {
		float targetTime;
		if (difficulty == Difficulty.easy) {
			targetTime = EASY_SUBSTRACT;
		} else if (difficulty == Difficulty.medium) {
			targetTime = MEDIUM_SUBSTRACT;
		} else if (difficulty == Difficulty.hard) {
			targetTime = HARD_SUBSTRACT;
		} else {
			targetTime = REDIC_SUBSTRACT;
		}
		return newInstance(type, timestamp - targetTime, targetTime);
	}
	
	public static IStep newInstance(StepType type, float fireTime, float targetTime) {
		if (type == StepType.random)
		{
			int rand = MathUtils.random(4);
			switch (rand)
			{
			case 0: 
				return new Step(StepType.left, fireTime, targetTime);
	        case 1: 
	        	return new Step(StepType.up, fireTime, targetTime);
	        case 2: 
	        	return new Step(StepType.right, fireTime, targetTime);
	        case 3: 
	        	return new Step(StepType.down, fireTime, targetTime);
	        case 4:
	        	return new Step(StepType.special,fireTime, targetTime);
			}
		}
		return new Step(type, fireTime, targetTime);
	}
	
	public static IStep newInstance(IStep step, float time) {
		float fireTime = time;
		float difference = (fireTime - step.getFireTime());
		float targetTime = step.getTargetTime() - difference;
		return newInstance(step.getType(), fireTime, targetTime);
	}
	
	/****************************************************************************
	 * getter and setter
	 ****************************************************************************/
	
	@Override
	public StepType getType() {
		return mType;
	}

	@Override
	public float getFireTime() {
		return mFireTime;
	}

	@Override
	public float getTargetTime() {
		return mTargetTime;
	}
}
