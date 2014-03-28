package de.teampotoo.gamejam6.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffectPlayer {
	
	private static Sound cheering = Gdx.audio.newSound(Gdx.files.internal("data/soundEffects/Applaus128.mp3"));
	
	
	public static void PlayApplaus(){
		cheering.play();
	}
	
}
