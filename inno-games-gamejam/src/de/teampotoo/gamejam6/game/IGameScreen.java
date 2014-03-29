package de.teampotoo.gamejam6.game;

import de.teampotoo.gamejam6.song.IStep;

public interface IGameScreen {

	public enum difficulty {easy,medium,hard,ridiculus};
	
	void fireStep(IStep step);
	
	void songEnd();
	
}
