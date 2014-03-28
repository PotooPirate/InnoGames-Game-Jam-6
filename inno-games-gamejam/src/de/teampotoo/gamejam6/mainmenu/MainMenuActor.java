package de.teampotoo.gamejam6.mainmenu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import de.teampotoo.gamejam6.helper.ResourceLoader;

public class MainMenuActor extends Group{
	public MainMenuActor() {
		createMenu();
	}
	
	
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}



	private void createMenu() {		
		Table table = new Table();
		
		TextButton startButton = new TextButton("Starte Spiel", ResourceLoader.SKIN);
		startButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}
		});

		TextButton creditsButton = new TextButton("Credits", ResourceLoader.SKIN);	
		creditsButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}
		});

		TextButton endButton = new TextButton("Highscore", ResourceLoader.SKIN);
		endButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}
		});

		table.add(startButton).row().pad(15f);
		table.add(creditsButton).row();
		table.add(endButton);

		table.setPosition(1280/2, 720/1.5f);
		addActor(table);
	}
}
