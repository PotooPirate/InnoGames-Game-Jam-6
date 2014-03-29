package de.teampotoo.gamejam6.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class SoundEffectPlayer {
	/****************************************************************************
	 * enum
	 ****************************************************************************/

	public enum Effect{
		cheering,awesome,brilliant,cccombobreaker,combobreaker,extreme,fantastic,fatality,great,
		greatjob,highscore,kimjungundancerevolution,kimjungun,looser,potoo,ridiculus,youareawesome,
		youbeatthehighscore
	}
	
	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private static Sound cheering = getSound("Applause.mp3");
	private static Sound awesome = getSound("Awesome.mp3");
	private static Sound brilliant = getSound("Brilliant.mp3");
	private static Sound cccombobreaker = getSound("CoCoCoComboBreaker.mp3");
	private static Sound combobreaker = getSound("ComboBreaker.mp3");
	private static Sound extreme = getSound("Extreme.mp3");
	private static Sound fantastic = getSound("Fantastic.mp3");
	private static Sound fatality = getSound("Fatality.mp3");
	private static Sound great = getSound("Great.mp3");
	private static Sound greatjob = getSound("GreatJob.mp3");
	private static Sound highscore = getSound("Highscore.mp3");
	private static Sound kimjungundancerevolution = getSound("KimJungDanceRevolution.mp3");
	private static Sound kimjungun = getSound("KimJungUn.mp3");
	private static Sound looser = getSound("Looser.mp3");
	private static Sound potoo = getSound("Potoo.mp3");
	private static Sound ridiculus = getSound("Ridiculus.mp3");
	private static Sound youareawesome = getSound("YouAreAwesome.mp3");
	private static Sound youbeatthehighscore = getSound("YouBeatTheHighscore.mp3");
	

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
	
	
	
	

}
