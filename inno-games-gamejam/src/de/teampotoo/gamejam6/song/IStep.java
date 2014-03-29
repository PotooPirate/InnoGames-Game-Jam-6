package de.teampotoo.gamejam6.song;

public interface IStep {

	public enum StepType {
		none, left, right, up, down, special, random
	}
	
	public StepType getType();
	
	public float getFireTime();
	
	public float getTargetTime();
	
}
