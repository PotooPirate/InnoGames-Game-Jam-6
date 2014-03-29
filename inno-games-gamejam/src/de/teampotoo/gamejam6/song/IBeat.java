package de.teampotoo.gamejam6.song;

public interface IBeat {

	public enum BeatType {
		none, easy, medium, hard, ridiculus
	}
	
	public float getTimestamp();
	
	public float getBlurIntensity();
	
	public float getBrightIntensity();
	
	public float getRetroIntensity();	
	
	public BeatType getType();
}
