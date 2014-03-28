package de.teampotoo.gamejam6.song;

public interface ISong {
	
	public enum Step {
		left, right, up, down 
	}
	
	public void start();
	
	public void stop();
	
	public void update(float deltaTime);
	
}