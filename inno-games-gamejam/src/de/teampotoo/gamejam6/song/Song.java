package de.teampotoo.gamejam6.song;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import de.teampotoo.gamejam6.game.IGameScreen;

public class Song implements ISong {

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private final List<IStep> mSteps;
	private final IGameScreen mGameScreen;

	private Music mSongMusic;

	private float time = 0f;
	private int index = 0;

	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	private Song(IGameScreen gameScreen, List<IStep> steps, String musicPath) {
		mGameScreen = gameScreen;
		mSteps = steps;
		mSongMusic = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
	}

	public static ISong newInstance(IGameScreen gameScreen, List<IStep> steps,
			String musicPath) {
		return new Song(gameScreen, steps, musicPath);
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	@Override
	public boolean start() {
		if (mSteps.size() < 1)
			return false;

		time = 0f;
		index = 0;

		mSongMusic.play();
		return true;
	}

	@Override
	public void stop() {
		mSongMusic.stop();
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
			} else {
				loop = false;
			}
		}

		// DEBUG
		// IStep lastStap = mSteps.get(mSteps.size()-1);
		// if (time > (lastStap.getFireTime() + lastStap.getTargetTime() +
		// 0.5f)){
		// stop();
		// mGameScreen.songEnd();}

		if (!(mSongMusic.isPlaying()))
			mGameScreen.songEnd();
	}
}
