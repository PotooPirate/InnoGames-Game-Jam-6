package de.teampotoo.gamejam6.song;

public interface IStep {

	public enum StepType {
		left, right, up, down, special, random
	}
	
	public StepType getType();
	
	public float getFireTime();
	
	public float getTargetTime();
	
}
