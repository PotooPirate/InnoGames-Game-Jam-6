package de.teampotoo.gamejam6.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class SoundEffectPlayer {
	/****************************************************************************
	 * enum
	 ****************************************************************************/

	public enum Effect{
		cheering,good,fatality,cccombobreaker,brilliant,awesome,great
	}
	
	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private static Sound cheering = getSound("Applause.mp3");
	private static Sound good = getSound("Good.mp3");
	private static Sound fatality = getSound("Fatality.mp3");
	private static Sound cccombobreaker = getSound("Cccombobreaker.mp3");
	private static Sound brilliant = getSound("Brilliant.mp3");
	private static Sound awesome = getSound("Awesome.mp3");
	private static Sound great = getSound("Great.mp3");
	
	
	

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	private static Sound getSound(String fileName){
		return  Gdx.audio.newSound(Gdx.files.internal("data/soundEffects/" + fileName));
	}
			
	public static void Play(Effect playEffect) {
		switch(playEffect){
		case cheering:
			cheering.play();
			break;
		case good:
			good.play();
			break;
		case fatality:
			fatality.play();
			break;
		case cccombobreaker:
			cccombobreaker.play();
			break;
		case brilliant:
			brilliant.play();
			break;
		case awesome:
			awesome.play();
			break;
		case great:
			great.play();
		}
		
	}
	
	
	
	

}
