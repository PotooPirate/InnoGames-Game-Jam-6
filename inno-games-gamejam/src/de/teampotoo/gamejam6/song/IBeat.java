package de.teampotoo.gamejam6.song;

public interface IBeat {

	public enum BeatType {
		none, easy, medium, hard, ridiculus
	}
	public float getIntervalLength();
	
	public int getInterval();
	
	public float getRetroIntensity();
	
	public float getBlurIntensity();
	
	public float getTimestamp();
	
	public BeatType getType();
}
