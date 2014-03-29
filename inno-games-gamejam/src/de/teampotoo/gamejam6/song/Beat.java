package de.teampotoo.gamejam6.song;

public class Beat implements IBeat {

	private final BeatType mType;
	private final float mTimestamp;
	private final float mRetroIntensity;
	private final float mBlurIntensity;
	private final float mIntervallLength;
	private final int mIntervall;
	
	private Beat(BeatType type, float timestamp, float retroIntensity, float blurIntensity,int intervall, float intervallLength) {
		mType = type;
		mTimestamp = timestamp;
		mRetroIntensity = retroIntensity;
		mBlurIntensity = blurIntensity;
		mIntervall = intervall;
		mIntervallLength = intervallLength;
	}
	
	public static IBeat newInstance(BeatType type, float timestamp, float retroIntensity, float blurIntensity, int intervall, float intervallLength) {
		return new Beat(type, timestamp, retroIntensity, blurIntensity,intervall,intervallLength);
	}

	@Override
	public BeatType getType() {
		return mType;
	}
	
	@Override
	public float getTimestamp() {
		return mTimestamp;
	}
	
	@Override
	public float getRetroIntensity() {
		return mRetroIntensity;
	}

	@Override
	public float getBlurIntensity() {
		return mBlurIntensity;
	}

	@Override
	public float getIntervalLength() {
		return mIntervallLength;
	}

	@Override
	public int getInterval() {
		return mIntervall;
	}
}
