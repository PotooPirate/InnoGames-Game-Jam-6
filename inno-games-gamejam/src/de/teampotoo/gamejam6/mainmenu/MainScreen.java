package de.teampotoo.gamejam6.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import de.teampotoo.gamejam6.helper.ResourceLoader;

public class MainScreen implements Screen {

	private Stage mStage;
	
	public MainScreen() {
		mStage = new Stage();
		Gdx.input.setInputProcessor(mStage);
		
		Image startButton = new Image(ResourceLoader.BUTTON);
		startButton.setPosition(Gdx.graphics.getWidth()/2 - startButton.getWidth()/2, Gdx.graphics.getHeight()/2 + startButton.getHeight()*2);
		Image creditsButton = new Image(ResourceLoader.BUTTON);
		creditsButton.setPosition(Gdx.graphics.getWidth()/2 - startButton.getWidth()/2, Gdx.graphics.getHeight()/2);
		Image highscoreButton = new Image(ResourceLoader.BUTTON);
		highscoreButton.setPosition(Gdx.graphics.getWidth()/2 - startButton.getWidth()/2, Gdx.graphics.getHeight()/2 - startButton.getHeight()*2);
		
		startButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("START");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		creditsButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("CREDITS");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		highscoreButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("HIGHSCORE");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		mStage.addActor(startButton);
		mStage.addActor(creditsButton);
		mStage.addActor(highscoreButton);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT | Gdx.gl20.GL_DEPTH_BUFFER_BIT);
		
		mStage.act(delta);
		mStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		mStage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		mStage.dispose();
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
		mStage.addActor(table);
	}

}
