package de.teampotoo.gamejam6.mainmenu;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import de.teampotoo.gamejam6.GameJam6;
import de.teampotoo.gamejam6.game.gui.Player;
import de.teampotoo.gamejam6.game.gui.Player.DanceStyle;
import de.teampotoo.gamejam6.helper.ResourceLoader;

public class MainScreen extends Group {

	/****************************************************************************
	 * variables
	 ****************************************************************************/
	
	private GameJam6 mGameJam6;
	
	private Player mPlayer;
	private Label mTitleLabel; 
	private Label mRevolutionLabel; 
	
	private static final float ANIMATION_TIME = 2.0f;
	private float mTimerCounter = 0;
	private Random mRandom = new Random();
	
	private Actor mMovementActor = new Actor();
	private float mInitOffScreenPosition = 0;
	private float mInitTargetPosition = 0;
	
	/****************************************************************************
	 * constructor
	 ****************************************************************************/
	
	public MainScreen(GameJam6 gameJam6) {
		this.mGameJam6 = gameJam6;
		createMenu();
		
		addActor(mMovementActor);
		mMovementActor.setPosition(0, 0);
		
		mPlayer = new Player();
		mPlayer.create();

		mPlayer.setPosition(mMovementActor.getX(), mMovementActor.getY());
		mPlayer.setState(DanceStyle.crazy);
		 
		mInitOffScreenPosition = -mPlayer.getHeight();
		mInitTargetPosition = mPlayer.getHeight() / 2.5f;
		mMovementActor.setPosition(Gdx.graphics.getWidth() / 2, mInitOffScreenPosition + mInitTargetPosition);
		triggerAnimation();
		
		mTitleLabel = new Label("Kim Jong Dance", ResourceLoader.SKIN);
		mTitleLabel.setPosition(Gdx.graphics.getWidth() / 2 - mTitleLabel.getWidth() / 2,
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6);
		mTitleLabel.setFontScale(1.5f);
		
		addActor(mTitleLabel);

		mRevolutionLabel = new Label("Revolution!!!", ResourceLoader.SKIN);
		mRevolutionLabel.setPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6 - Gdx.graphics.getHeight() / 20);
		mRevolutionLabel.setFontScale(1.5f);
		addActor(mRevolutionLabel);
	}
	
	/****************************************************************************
	 * methods
	 ****************************************************************************/

	private void triggerAnimation() {
		int randomX = mRandom.nextInt(Gdx.graphics.getWidth());
		int randomYOffset = mRandom.nextInt(mPlayer.getHeight() / 2);
		 
		mMovementActor.setPosition(randomX, mInitOffScreenPosition);
		mMovementActor.addAction(Actions.sequence(Actions.moveBy(0, mInitTargetPosition + randomYOffset, ANIMATION_TIME / 2.0f), 
				Actions.sequence(Actions.moveBy(0, -(mInitTargetPosition + randomYOffset), ANIMATION_TIME / 2.0f))));
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		mPlayer.setPosition(mMovementActor.getX(), mMovementActor.getY());
		
		mTimerCounter += delta;
		
		if (mTimerCounter > ANIMATION_TIME) {
			mTimerCounter = 0;
			
			triggerAnimation();
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		mPlayer.render();
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
				mGameJam6.startCredits();
			}
		});

		TextButton endButton = new TextButton("Highscore", ResourceLoader.SKIN);
		endButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				mGameJam6.startHighscore();
			}
		});

		table.add(startButton).row().pad(15f);
		table.add(endButton).row();
		table.add(creditsButton);

		table.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2.f);
		this.addActor(table);
	}

}
