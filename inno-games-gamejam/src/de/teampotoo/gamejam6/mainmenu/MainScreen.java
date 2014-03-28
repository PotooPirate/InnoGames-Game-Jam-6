package de.teampotoo.gamejam6.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.helper.ResourceLoader;

public class MainScreen extends Group {

	private GameJam6 mGameJam6;
	
	public MainScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;
		createMenu();
	}
	
	
	private void createMenu() {		
		Table table = new Table();
		
		TextButton startButton = new TextButton("Starte Spiel", ResourceLoader.SKIN);
		startButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				mGameJam6.startGame();
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

		table.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/1.5f);
		this.addActor(table);
	}

}
