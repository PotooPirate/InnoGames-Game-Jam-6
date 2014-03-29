package de.teampotoo.gamejam6.credits;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.helper.ResourceLoader;

public class CreditsScreen extends Group {
	private GameJam6 mGameJam6;
	
	public CreditsScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;
		addBackButton();
		addCredits();
		
		
	}
	
	private void addBackButton() {
		Image backButton = new Image(ResourceLoader.BUTTON);
		backButton.setWidth(100);
		backButton.setHeight(50);
		backButton.setPosition(0, Gdx.graphics.getHeight() - backButton.getHeight());
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				mGameJam6.startMainMenu();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		addActor(backButton);
	}
	
	private void addCredits(){
		Table tabelle = new Table();
		Label topicLabel1 = new Label("Programmierung:\n",ResourceLoader.SKIN);
		Label nameLabel1 = new Label("Joschka Schulz\n",ResourceLoader.SKIN);
		Label nameLabel2 = new Label("Raimund Wege\n",ResourceLoader.SKIN);
		Label nameLabel3 = new Label("Frederik Klauß\n",ResourceLoader.SKIN);
		Label nameLabel4 = new Label("Tobias Hassenklöve\n",ResourceLoader.SKIN);
		Label nameLabel5 = new Label("r\n",ResourceLoader.SKIN);
//		Label filler = new Label("\n",ResourceLoader.SKIN);
		Label filler2 = new Label("\n",ResourceLoader.SKIN);
//		Label filler3 = new Label("\n",ResourceLoader.SKIN);
		Label filler4 = new Label("\n",ResourceLoader.SKIN);
		Label topicLabel2 = new Label("Design:\n",ResourceLoader.SKIN);
		Label nameLabel6 = new Label("Annika Baas\n",ResourceLoader.SKIN);
		Label nameLabel7 = new Label("Florine Vollbrecht\n",ResourceLoader.SKIN);
		Label topicLabel3 = new Label ("Sound:\n", ResourceLoader.SKIN);
		Label nameLabel8 = new Label("Jason\n", ResourceLoader.SKIN);
		
		tabelle.add(topicLabel1).row();
		tabelle.add(nameLabel1).row();
		tabelle.add(nameLabel2).row();
		tabelle.add(nameLabel3).row();
		tabelle.add(nameLabel4).row();
		tabelle.add(nameLabel5).row();
		tabelle.add(filler2).row();
		tabelle.add(topicLabel2).row();
		tabelle.add(nameLabel6).row();
		tabelle.add(nameLabel7).row();
		tabelle.add(filler4).row();
		tabelle.add(topicLabel3).row();
		tabelle.add(nameLabel8).row();
		
		tabelle.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		addActor(tabelle);
		
		
	}
}
