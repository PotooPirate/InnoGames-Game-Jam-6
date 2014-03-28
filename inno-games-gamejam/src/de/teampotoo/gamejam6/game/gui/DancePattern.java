package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.teampotoo.gamejam6.helper.ResourceLoader;

public class DancePattern extends Group {
	private Image mBackground;
	
	public DancePattern() {
		mBackground = new Image(ResourceLoader.sDancePatternBackground);
		addActor(mBackground);
		
		addAction(Actions.alpha(0.75f));
	}
	
	
}
