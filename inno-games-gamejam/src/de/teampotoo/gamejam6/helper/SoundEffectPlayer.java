package de.teampotoo.gamejam6.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class SoundEffectPlayer {
	/****************************************************************************
	 * enum
	 ****************************************************************************/

	public enum Effect{
		awesome,brilliant,cccombobreaker,combobreaker,extreme,fantastic,fatality,great,
		greatjob,highscore,kimjungundancerevolution,kimjungun,looser,potoo,ridiculus,youareawesome,
		youbeatthehighscore
	}
	
	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private static Sound awesome ;
	private static Sound brilliant;
	private static Sound cccombobreaker;
	private static Sound combobreaker;
	private static Sound extreme;
	private static Sound fantastic;
	private static Sound fatality;
	private static Sound great;
	private static Sound greatjob;
	private static Sound highscore;
	private static Sound kimjungundancerevolution;
	private static Sound kimjungun;
	private static Sound looser;
	private static Sound potoo;
	private static Sound ridiculus;
	private static Sound youareawesome;
	private static Sound youbeatthehighscore;
	

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	private static Sound getSound(String fileName){
		return  Gdx.audio.newSound(Gdx.files.internal("data/soundEffects/" + fileName));
	}
			
	public static void Play(Effect playEffect) {
		switch(playEffect){
		case awesome:
			awesome.play();
			break;
		case brilliant:
			brilliant.play();
			break;
		case cccombobreaker:
			cccombobreaker.play();
			break;
		case combobreaker:
			combobreaker.play();
			break;
		case extreme:
			extreme.play();
			break;
		case fantastic:
			fantastic.play();
			break;
		case fatality:
			fatality.play();
			break;
		case great:
			great.play();
			break;
		case greatjob:
			greatjob.play();
			break;
		case highscore:
			highscore.play();
			break;
		case kimjungundancerevolution:
			kimjungundancerevolution.play();
			break;
		case kimjungun:
			kimjungun.play();
			break;
		case looser:
			looser.play();
			break;
		case potoo:
			potoo.play();
			break;
		case ridiculus:
			ridiculus.play();
			break;
		case youareawesome:
			youareawesome.play();
			break;
		case youbeatthehighscore:
			youbeatthehighscore.play();
			break;
		}
		
	}

	public static void loadSounds() {
		awesome = getSound("Awesome.mp3");
		brilliant = getSound("Brilliant.mp3");
		cccombobreaker = getSound("CoCoCoComboBreaker.mp3");
		combobreaker = getSound("ComboBreaker.mp3");
		extreme = getSound("Extreme.mp3");
		fantastic = getSound("Fantastic.mp3");
		fatality = getSound("Fatalaty.mp3");
		great = getSound("Great.mp3");
		greatjob = getSound("GreatJob.mp3");
		highscore = getSound("Highscore.mp3");
		kimjungundancerevolution = getSound("Intro.mp3");
		kimjungun = getSound("KimJungUn.mp3");
		looser = getSound("Looser.mp3");
		potoo = getSound("Potoo.mp3");
		ridiculus = getSound("Ridiculus.mp3");
		youareawesome = getSound("YouAreAwesome.mp3");
		youbeatthehighscore = getSound("YouBeatTheHighscore.mp3");
		
	}
	
	public static Sound getSoundRef(Effect playEffect){
		switch(playEffect){
		case awesome:
			return awesome;
		case brilliant:
			return brilliant;
		case cccombobreaker:
			return cccombobreaker;
		case combobreaker:
			return combobreaker;
		case extreme:
			return extreme;
		case fantastic:
			return fantastic;
		case fatality:
			return fatality;
		case great:
			return great;
		case greatjob:
			return greatjob;
		case highscore:
			return highscore;
		case kimjungundancerevolution:
			return kimjungundancerevolution;
		case kimjungun:
			return kimjungun;
		case looser:
			return looser;
		case potoo:
			return potoo;
		case ridiculus:
			return ridiculus;
		case youareawesome:
			return youareawesome;
		case youbeatthehighscore:
			return youbeatthehighscore;
		default:
			return null;
		}
	}
	
	
	
	

}
