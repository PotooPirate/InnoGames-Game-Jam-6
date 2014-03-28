package de.teampotoo.gamejam6.song;

public interface IStep {

	enum StepType {
		left, right, up, down, special
	}
	
	public StepType getStep();
	
	public float getFireTime();
	
	public float getTargetTime();
	
}
