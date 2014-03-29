package de.teampotoo.gamejam6.song;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Group;

import de.teampotoo.gamejam6.game.IGameScreen;
import de.teampotoo.gamejam6.song.IStep.StepType;

public class Song implements ISong {

	private final List<IStep> mSteps;
	private final IGameScreen mGameScreen;
	
	private float time = 0f;
	private int index = 0;
	private Music songMusic;
	
	private Song(IGameScreen gameScreen, List<IStep> steps, String musicPath) {
		mGameScreen = gameScreen;
		mSteps = steps;
		songMusic = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
	}
	
	public static ISong newInstance(IGameScreen gameScreen, List<IStep> steps, String musicPath){
		return new Song(gameScreen,steps,musicPath);
	}
	
	@Override
	public boolean start() {
		if (mSteps.size() < 1)
			return false;
		
		time = 0f;
		index = 0;
		
		songMusic.play();
		return true;
	}

	@Override
	public void stop() {
		System.out.println("song stopped");
		songMusic.stop();
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
				if (!(index == mSteps.size()))
					step = mSteps.get(index);
			} else {
				loop = false;
			}
		}
	}
}
