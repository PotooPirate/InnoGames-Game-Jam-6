package de.teampotoo.gamejam6.credits;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.game.SugarRocket;
import de.teampotoo.gamejam6.game.gui.Player;
import de.teampotoo.gamejam6.game.gui.Player.DanceStyle;
import de.teampotoo.gamejam6.helper.ResourceLoader;

public class CreditsScreen extends Group {

	/****************************************************************************
	 * variables
	 ****************************************************************************/
	private static final float FONT_SCALE = 0.5f;
	
	private Player mPlayer1;
	private Player mPlayer2;

	private GameJam6 mGameJam6;

	private Image mLowerBackground;

	private Actor mUpperBackground;

	/****************************************************************************
	 * constructor
	 ****************************************************************************/

	public CreditsScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;
		
		mLowerBackground = new Image(ResourceLoader.sGameLowerBackground);
		mLowerBackground.setBounds(0, 0, mLowerBackground.getWidth(),
				mLowerBackground.getHeight());
		addActor(mLowerBackground);

		SugarRocket rocket = new SugarRocket(200, 20);
		addActor(rocket);
		SugarRocket rocket2 = new SugarRocket(400, 10);
		addActor(rocket2);
		SugarRocket rocket3 = new SugarRocket(500, 15);
		addActor(rocket3);
		SugarRocket rocket4 = new SugarRocket(700, 5);
		addActor(rocket4);
		SugarRocket rocket5 = new SugarRocket(800, 7);
		addActor(rocket5);
		SugarRocket rocket6 = new SugarRocket(100, 3);
		addActor(rocket6);
		
		mUpperBackground = new Image(ResourceLoader.sGameUpperBackground);
		mUpperBackground.setBounds(0, 0, mUpperBackground.getWidth(),
				mUpperBackground.getHeight());
		addActor(mUpperBackground);
		
		addBackButton();
		addCredits();
		
		mPlayer1 = new Player();
		mPlayer1.create();
		mPlayer1.setPosition(Gdx.graphics.getWidth()  / 5, Gdx.graphics.getHeight() / 14);
		mPlayer1.setState(DanceStyle.level3);
		mPlayer2 = new Player();
		mPlayer2.create();
		mPlayer2.setPosition(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()  / 5, Gdx.graphics.getHeight() / 14);
		mPlayer2.setState(DanceStyle.level5);
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	private void addBackButton() {
		TextButton backButton = new TextButton("Back", ResourceLoader.SKIN);
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				mGameJam6.startMainMenu();
			}
		});
		backButton.setPosition(0, Gdx.graphics.getHeight() - backButton.getHeight());
		addActor(backButton);
	}

	private Label createCreditLabel(String text, Skin skin) {
		Label result = new Label(text, skin);
		result.setFontScale(FONT_SCALE);
		
		return result;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		mPlayer1.render();
		mPlayer2.render();
	}

	private void addCredits() {
		Table tabelle = new Table();
		
		Label headline = createCreditLabel("Kim Jong Dance: Revolution!!!", ResourceLoader.SKIN);
		Label copyright = createCreditLabel("Copyright 2014. All rights reserved.", ResourceLoader.SKIN);
		Label filler1 = createCreditLabel("\n", ResourceLoader.SKIN); 

		Label topicLabel2 = createCreditLabel("Design:\n", ResourceLoader.SKIN);
		Label nameLabel6 = createCreditLabel("Annika Baas\n", ResourceLoader.SKIN);
		Label nameLabel7 = createCreditLabel("Florine Vollbrecht\n",
				ResourceLoader.SKIN);
		Label filler2 = createCreditLabel("\n", ResourceLoader.SKIN); 
		Label topicLabel1 = createCreditLabel("Programmierung:\n", ResourceLoader.SKIN);
		Label nameLabel1 = createCreditLabel("Joschka Schulz\n", ResourceLoader.SKIN);
		Label nameLabel2 = createCreditLabel("Raimund Wege\n", ResourceLoader.SKIN);
		Label nameLabel3 = createCreditLabel("Frederik Klauss\n", ResourceLoader.SKIN);
		Label nameLabel4 = createCreditLabel("Tobias Hassenkloever\n",
				ResourceLoader.SKIN);
		Label filler4 = createCreditLabel("\n", ResourceLoader.SKIN);
		Label topicLabel3 = createCreditLabel("Sound:\n", ResourceLoader.SKIN);
		Label nameLabel8 = createCreditLabel("\"Jason\" Florian Jahrstorfer\n", ResourceLoader.SKIN);

		tabelle.add(headline).row();
		tabelle.add(copyright).row();
		tabelle.add(filler1).row();
		tabelle.add(topicLabel2).row();
		tabelle.add(nameLabel6).row();
		tabelle.add(nameLabel7).row();
		tabelle.add(filler2).row();
		tabelle.add(topicLabel1).row();
		tabelle.add(nameLabel1).row();
		tabelle.add(nameLabel2).row();
		tabelle.add(nameLabel3).row();
		tabelle.add(nameLabel4).row(); 
		tabelle.add(filler4).row();
		tabelle.add(topicLabel3).row();
		tabelle.add(nameLabel8).row();

		tabelle.setPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
		addActor(tabelle);

	}
}
