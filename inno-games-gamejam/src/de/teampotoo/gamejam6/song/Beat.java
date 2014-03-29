package de.teampotoo.gamejam6.song;

public class Beat implements IBeat {

	private final BeatType mType;
	private final float mTimestamp;
	private final float mRetroIntensity;
	private final float mBlurIntensity;
	
	private Beat(BeatType type, float timestamp, float retroIntensity, float blurIntensity) {
		mType = type;
		mTimestamp = timestamp;
		mRetroIntensity = retroIntensity;
		mBlurIntensity = blurIntensity;
	}
	
	public static IBeat newInstance(BeatType type, float timestamp, float retroIntensity, float blurIntensity) {
		return new Beat(type, timestamp, retroIntensity, blurIntensity);
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
}
