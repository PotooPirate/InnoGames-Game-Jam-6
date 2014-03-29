package de.teampotoo.gamejam6.song;

public class Beat implements IBeat {

	private final BeatType mType;
	private final float mTimestamp;
	private final float mBlurIntensity;
	private final float mRetroIntensity;
	private final float mBrightIntensity;
	
	private Beat(BeatType type, float timestamp, float blurIntensity, float brightIntensity, float retroIntensity) {
		mType = type;
		mTimestamp = timestamp;
		mBlurIntensity = blurIntensity;
		mBrightIntensity = brightIntensity;
		mRetroIntensity = retroIntensity;
	}
	
	public static IBeat newInstance(BeatType type, float timestamp, float blurIntensity, float brightIntensity, float retroIntensity) {
		return new Beat(type, timestamp, blurIntensity, brightIntensity, retroIntensity);
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
	public float getBlurIntensity() {
		return mBlurIntensity;
	}

	@Override
	public float getBrightIntensity() {
		return mBrightIntensity;
	}
	
	@Override
	public float getRetroIntensity() {
		return mRetroIntensity;
	}
}
