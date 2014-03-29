package de.teampotoo.gamejam6.game;

import de.teampotoo.gamejam6.song.IStep;

public interface IGameScreen {

	void fireStep(IStep step);
	
	void songEnd();
	
}
