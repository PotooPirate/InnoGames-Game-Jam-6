package de.teampotoo.gamejam6.song;

import java.util.List;

import de.teampotoo.gamejam6.game.IGameScreen;
import de.teampotoo.gamejam6.song.IStep.StepType;

public class Song implements ISong {

	private final List<IStep> mSteps;
	private final IGameScreen mGameScreen;
	
	private float time = 0f;
	private int index = 0;
	
	private Song(IGameScreen gameScreen, List<IStep> steps) {
		mGameScreen = gameScreen;
		mSteps = steps;
	}
	
	@Override
	public boolean start() {
		if (mSteps.size() < 1)
			return false;
		
		time = 0f;
		index = 0;
		
		return true;
	}

	@Override
	public void stop() {
		System.out.println("song stopped");
	}

	@Override
	public void update(float deltaTime) {
		time += deltaTime;
		
		boolean loop = true;
		while (index < mSteps.size() && loop) {
			IStep step = mSteps.get(index);
			if (step.getFireTime() <= time) {
				mGameScreen.fireStep(Step.newInstance(step, time));
				index++;
				step = mSteps.get(index);
			} else {
				loop = false;
			}
		}
	}
}
