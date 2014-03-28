package de.teampotoo.gamejam6.song;

import de.teampotoo.gamejam6.song.IStep.StepType;

public class Step implements IStep {

	private final StepType mType;
	private final float mFireTime;
	private final float mTargetTime;
	
	private Step(StepType type, float fireTime, float targetTime) {
		mType = type;
		mFireTime = fireTime;
		mTargetTime = targetTime;
	}
	
	public static IStep newInstance(StepType type, float fireTime, float targetTime) {
		return new Step(type, fireTime, targetTime);
	}
	
	public static IStep newInstance(IStep step, float time) {
		float fireTime = time;
		float difference = (fireTime - step.getFireTime());
		float targetTime = step.getTargetTime() - difference;
		return newInstance(step.getType(), fireTime, targetTime);
	}
	
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
