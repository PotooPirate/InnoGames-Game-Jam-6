package de.teampotoo.gamejam6.song;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;

import de.teampotoo.gamejam6.game.IGameScreen;
import de.teampotoo.gamejam6.game.IGameScreen.Difficulty;
import de.teampotoo.gamejam6.song.IStep.StepType;

public class SongFactory {

	/****************************************************************************
	 * enum
	 ****************************************************************************/
	public enum BeatType {
		none, flat, slow, normal, fast, ridiculus
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	public static ISong createSong1(IGameScreen gameScreen,
			Difficulty difficulty) {
		List<BeatType> beats = new ArrayList<BeatType>();
		for (int i = 0; i < 8; i++)
			beats.add(BeatType.flat);
		for (int i = 0; i < 8; i++)
			beats.add(BeatType.slow);
		for (int i = 0; i < 8; i++)
			beats.add(BeatType.normal);
		for (int i = 0; i < 8; i++)
			beats.add(BeatType.fast);
		for (int i = 0; i < 8; i++)
			beats.add(BeatType.normal);
		for (int i = 0; i < 8; i++)
			beats.add(BeatType.slow);
		for (int i = 0; i < 8; i++)
			beats.add(BeatType.flat);
		return createSong(gameScreen, beats, 132,
				"data/music/Crazy_Gameboy.wav", difficulty);
	}

	public static ISong createSong(IGameScreen gameScreen,
			List<BeatType> beats, int bpm, String path, Difficulty difficulty) {
		List<IStep> steps = new ArrayList<IStep>();
		float beatLength = (60f / bpm);
		BeatType lastBeat = BeatType.none;
		StepType[][] beatStep = new StepType[4][2];
		for (int i = 0; i < beats.size(); i++) {
			if (beats.get(i) != lastBeat) {
				lastBeat = beats.get(i);
				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < 2; k++) {
						beatStep[j][k] = StepType.none;
					}
				}
				if (lastBeat == BeatType.flat || lastBeat == BeatType.slow
						|| lastBeat == BeatType.normal
						|| lastBeat == BeatType.fast
						|| lastBeat == BeatType.ridiculus) {
					beatStep[3][0] = MathUtils.randomBoolean() ? StepType.up
							: MathUtils.randomBoolean() ? StepType.down
									: StepType.none;
					beatStep[3][1] = MathUtils.randomBoolean() ? StepType.left
							: MathUtils.randomBoolean() ? StepType.right
									: StepType.none;
				}

				if (lastBeat == BeatType.slow || lastBeat == BeatType.normal
						|| lastBeat == BeatType.fast
						|| lastBeat == BeatType.ridiculus) {
					beatStep[1][0] = MathUtils.randomBoolean() ? StepType.up
							: MathUtils.randomBoolean() ? StepType.down
									: StepType.none;
					beatStep[1][1] = MathUtils.randomBoolean() ? StepType.left
							: MathUtils.randomBoolean() ? StepType.right
									: StepType.none;
				}

				if (lastBeat == BeatType.normal || lastBeat == BeatType.fast
						|| lastBeat == BeatType.ridiculus) {
					beatStep[1][0] = MathUtils.randomBoolean() ? StepType.up
							: MathUtils.randomBoolean() ? StepType.down
									: StepType.none;
					beatStep[1][1] = MathUtils.randomBoolean() ? StepType.left
							: MathUtils.randomBoolean() ? StepType.right
									: StepType.none;
				}
			}
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 2; k++) {
					if (beatStep[j][k] != StepType.none) {
						steps.add(Step.newInstance(beatStep[j][k], beatLength
								* ((i * 4) + j), difficulty));
					}
				}
			}
		}
		return Song.newInstance(gameScreen, steps, path);
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
