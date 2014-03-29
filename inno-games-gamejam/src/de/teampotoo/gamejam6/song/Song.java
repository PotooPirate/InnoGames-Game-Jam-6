package de.teampotoo.gamejam6.song;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import de.teampotoo.gamejam6.game.IGameScreen;

public class Song implements ISong {

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private final List<IBeat> mBeats;
	private final List<IStep> mSteps;
	private final IGameScreen mGameScreen;

	private Music mSongMusic;

	private float mTime = 0f;
	private int mStepIndex = 0;
	private int mBeatIndex = 0;

	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	private Song(IGameScreen gameScreen, List<IBeat> beats, List<IStep> steps, String musicPath) {
		mGameScreen = gameScreen;
		mBeats = beats;
		mSteps = steps;
		mSongMusic = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
	}

	public static ISong newInstance(IGameScreen gameScreen, List<IBeat> beats, List<IStep> steps, String musicPath) {
		return new Song(gameScreen, beats, steps, musicPath);
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	@Override
	public boolean start() {
		if (mSteps.size() < 1 || mBeats.size() < 1)
			return false;

		mTime = 0f;
		mBeatIndex = 0;
		mStepIndex = 0;

		mSongMusic.play();
		return true;
	}

	@Override
	public void stop() {
		mSongMusic.stop();
	}

	@Override
	public void update(float deltaTime) {
		mTime += deltaTime;

		boolean loop = true;
		while (mBeatIndex < mSteps.size() && loop) {
			IBeat beat = mBeats.get(mBeatIndex);
			if (beat.getTimestamp() <= mTime) {
				mGameScreen.fireBeat(beat);
				mBeatIndex++;
			} else {
				loop = false;
			}
		}
		
		loop = true;
		while (mStepIndex < mSteps.size() && loop) {
			IStep step = mSteps.get(mStepIndex);
			if (step.getFireTime() <= mTime) {
				mGameScreen.fireStep(Step.newInstance(step, mTime));
				mStepIndex++;
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

		if (!mSongMusic.isPlaying()) {
			mGameScreen.songEnd();
		}
	}
}
