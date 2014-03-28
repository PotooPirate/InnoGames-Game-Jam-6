package de.teampotoo.gamejam6.song;

public class Step implements IStep {

	private final StepType mType;
	
	private Step(StepType type) {
		mType = type;
	}
	
	@Override
	public StepType getStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getFireTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTargetTime() {
		// TODO Auto-generated method stub
		return 0;
	}
}
