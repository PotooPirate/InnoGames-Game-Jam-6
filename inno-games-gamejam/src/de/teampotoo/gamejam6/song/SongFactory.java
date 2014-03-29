package de.teampotoo.gamejam6.song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.MathUtils;

import de.teampotoo.gamejam6.game.IGameScreen;
import de.teampotoo.gamejam6.game.IGameScreen.Difficulty;
import de.teampotoo.gamejam6.song.IBeat.BeatType;
import de.teampotoo.gamejam6.song.IStep.StepType;

public class SongFactory {

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	public static ISong createSong1(IGameScreen gameScreen,
			Difficulty difficulty) {
		List<IBeat> beats = new ArrayList<IBeat>();
		int bpm = 132;
		float beatLength = (60f / bpm);
		int beatCount = 0;
		beats.add(Beat.newInstance(BeatType.none, beatCount * beatLength * 4, 0f, 0f));
		beatCount++;
		for (int i = 0; i < 7; i++) {
			beats.add(Beat.newInstance(BeatType.easy, beatCount * beatLength * 4, 0f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 7; i++) {
			beats.add(Beat.newInstance(BeatType.medium, beatCount * beatLength * 4, 0f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 1; i++) {
			beats.add(Beat.newInstance(BeatType.hard, beatCount * beatLength * 4, 0f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 8; i++) {
			beats.add(Beat.newInstance(BeatType.medium, beatCount * beatLength * 4, 0f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 8; i++) {
			beats.add(Beat.newInstance(BeatType.hard, beatCount * beatLength * 4, 0.2f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 8; i++) {
			beats.add(Beat.newInstance(BeatType.hard, beatCount * beatLength * 4, 0.2f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 4; i++) {
			beats.add(Beat.newInstance(BeatType.easy, beatCount * beatLength * 4, 0f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 2; i++) {
			beats.add(Beat.newInstance(BeatType.medium, beatCount * beatLength * 4, 0f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 5; i++) {
			beats.add(Beat.newInstance(BeatType.easy, beatCount * beatLength * 4, 0f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 8; i++) {
			beats.add(Beat.newInstance(BeatType.hard, beatCount * beatLength * 4, 0.5f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 6; i++) {
			beats.add(Beat.newInstance(BeatType.easy, beatCount * beatLength * 4, 0f, 0f));
			beatCount++;
		}
		for (int i = 0; i < 2; i++) {
			beats.add(Beat.newInstance(BeatType.ridiculus, beatCount * beatLength * 4, 0.3f, 0.3f));
			beatCount++;
		}
		return createSong(gameScreen, beats, beatLength,
				"data/music/Crazy_Gameboy.wav", difficulty);
	}

	public static ISong createSong(IGameScreen gameScreen,
			List<IBeat> beats, float beatLength, String path, Difficulty difficulty) {
		List<IStep> steps = new ArrayList<IStep>();
		Map<StepType, List<Float>> stepsPerBeat = new HashMap<IStep.StepType, List<Float>>();
		IBeat currentBeat = null;
		IBeat lastBeat = null;
		for (int i = 0; i < beats.size(); i++) {
			currentBeat = beats.get(i);
			
			// Update beat layout if another beat occurs
			if (lastBeat == null || currentBeat.getType() != lastBeat.getType()) {
				stepsPerBeat.clear();
				
				// Set timestamps for steps per beat
				List<Float> offset = new ArrayList<Float>();
				if (currentBeat.getType() == BeatType.easy) {
					offset.add(1f * beatLength);
				} else if (currentBeat.getType() == BeatType.medium) {
					offset.add(1f * beatLength);
					offset.add(3f * beatLength);
				} else if (currentBeat.getType() == BeatType.hard) {
					offset.add(1f * beatLength);
					offset.add(2f * beatLength);
					offset.add(3f * beatLength);
					offset.add(4f * beatLength);
				} else if (currentBeat.getType() == BeatType.ridiculus) {
					offset.add(0.5f * beatLength);
					offset.add(1f * beatLength);
					offset.add(1.5f * beatLength);
					offset.add(2f * beatLength);
					offset.add(2.5f * beatLength);
					offset.add(3f * beatLength);
					offset.add(3.5f * beatLength);
					offset.add(4f * beatLength);
				}
				
				// Set step types
//				if (MathUtils.randomBoolean()) {
					stepsPerBeat.put(StepType.random, offset);
//				} else {
//					stepsPerBeat.put(MathUtils.randomBoolean() ? StepType.left : StepType.right, offset);
//				}
			}
			
			// Add current beat layout for this beat
			for (Map.Entry<StepType, List<Float>> entry : stepsPerBeat.entrySet()) {
				for (Float timestamp : entry.getValue()) {
					steps.add(Step.newInstance(entry.getKey(), currentBeat.getTimestamp() + timestamp, difficulty));
				}
			}
			
			// Set the current beat as the last beat
			lastBeat = currentBeat;
		}
		System.out.println(steps.toString());
		return Song.newInstance(gameScreen, beats, steps, path);
	}

	public static ISong createSong2(IGameScreen gameScreen) {
		return null;
	}

	public static ISong createSong3(IGameScreen gameScreen) {
		return null;
	}

	/*public static ISong createTestSong(IGameScreen gameScreen) {
		List<IStep> steps = new ArrayList<IStep>();
		steps.add(Step.newInstance(StepType.up, 1f, 1f));
		steps.add(Step.newInstance(StepType.left, 2f, 1f));
		steps.add(Step.newInstance(StepType.right, 3f, 2f));
		steps.add(Step.newInstance(StepType.left, 4f, 1f));
		steps.add(Step.newInstance(StepType.right, 5f, 1f));
		steps.add(Step.newInstance(StepType.special, 6f, 2.5f));
		return Song.newInstance(gameScreen, steps, "data/music/TestSong.mp3");
	}*/
}
