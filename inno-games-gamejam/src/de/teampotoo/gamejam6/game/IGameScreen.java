package de.teampotoo.gamejam6.game;

import de.teampotoo.gamejam6.song.IBeat;
import de.teampotoo.gamejam6.song.IStep;

public interface IGameScreen {

	public enum Difficulty {easy,medium,hard,ridiculus};
	
	void fireStep(IStep step);
	
	void fireBeat(IBeat beat);
	
	void songEnd();
	
}
