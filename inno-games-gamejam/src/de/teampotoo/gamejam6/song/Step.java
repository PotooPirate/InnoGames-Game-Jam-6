package de.teampotoo.gamejam6.song;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import de.teampotoo.gamejam6.game.GameScreen;
import de.teampotoo.gamejam6.game.IGameScreen;
import de.teampotoo.gamejam6.game.IGameScreen.difficulty;
import de.teampotoo.gamejam6.song.IStep.StepType;

public class Step implements IStep {

	private final StepType mType;
	private final float mFireTime;
	private final float mTargetTime;
	
	private static final float EASY_SUBSTRACT = 1.5f;
	private static final float MEDIUM_SUBSTRACT = 1.0f;
	private static final float HARD_SUBSTRACT = 0.5f;
	private static final float REDIC_SUBSTRACT = 0.3f;
	

	private Step(StepType type, float fireTime, float targetTime) {
		mType = type;
		mFireTime = fireTime;
		mTargetTime = targetTime;
	}
	
	public static IStep newInstance(StepType type, float targetTime, IGameScreen.difficulty difficulty)
	{
		float fireTime;
		switch(difficulty)
		{
		case easy:
			fireTime = targetTime - EASY_SUBSTRACT;
			if (fireTime < 0f)
				fireTime = 0f;
			break;
		case medium:
			fireTime = targetTime - MEDIUM_SUBSTRACT;
			if (fireTime < 0f)
				fireTime = 0f;
			break;
		case hard:
			fireTime = targetTime - HARD_SUBSTRACT;
			if (fireTime < 0f)
				fireTime = 0f;
			break;
		case ridiculus:
			fireTime = targetTime - REDIC_SUBSTRACT;
			if (fireTime < 0f)
				fireTime = 0f;
			break;
		default:
			fireTime = targetTime - MEDIUM_SUBSTRACT;
			if (fireTime < 0f)
				fireTime = 0f;
			break;			
		}
			
	   return newInstance(type, fireTime, targetTime);
	}
	
	public static IStep newInstance(StepType type, float fireTime, float targetTime) {
		if (type == StepType.random)
		{
			int rand = MathUtils.random(4);
			switch (rand)
			{
			case 0: 
				return new Step(StepType.left, fireTime, targetTime);
	        case 1: 
	        	return new Step(StepType.up, fireTime, targetTime);
	        case 2: 
	        	return new Step(StepType.right, fireTime, targetTime);
	        case 3: 
	        	return new Step(StepType.down, fireTime, targetTime);
	        case 4:
	        	return new Step(StepType.special,fireTime, targetTime);
			}
		}
		return new Step(type, fireTime, targetTime);
	}
	
	public static IStep newInstance(IStep step, float time) {
		float fireTime = time;
		float difference = (fireTime - step.getFireTime());
		float targetTime = step.getTargetTime() - difference;
		return newInstance(step.getType(), fireTime, targetTime);
	}
	
	@Override
	public StepType getType() {
		return mType;
	}

	@Override
	public float getFireTime() {
		return mFireTime;
	}

	@Override
	public float getTargetTime() {
		return mTargetTime;
	}
}
