package de.teampotoo.gamejam6.song;

import java.util.ArrayList;
import java.util.List;

import de.teampotoo.gamejam6.game.IGameScreen;
import de.teampotoo.gamejam6.song.IStep.StepType;

public class SongFactory {

	public static ISong createSong1(IGameScreen gameScreen) {
		return null;
	}
	
	public static ISong createSong2(IGameScreen gameScreen) {
		return null;
	}
	
	public static ISong createSong3(IGameScreen gameScreen) {
		return null;
	}
	
	public static ISong createTestSong(IGameScreen gameScreen) {
		List<IStep> steps = new ArrayList<IStep>();
		steps.add(Step.newInstance(StepType.up, 1f, 1f));
		steps.add(Step.newInstance(StepType.left, 2f, 1f));
		steps.add(Step.newInstance(StepType.right, 3f, 2f));
		steps.add(Step.newInstance(StepType.left, 4f, 1f));
		steps.add(Step.newInstance(StepType.right, 5f, 1f));
		steps.add(Step.newInstance(StepType.special, 6f, 2.5f));
		
		return Song.newInstance(gameScreen, steps, "data/music/TestSong.mp3");
	}
}
