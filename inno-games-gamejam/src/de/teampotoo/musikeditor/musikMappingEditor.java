package de.teampotoo.musikeditor;

import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;


public class musikMappingEditor extends Group {
	private float time = 0f;
	
	public musikMappingEditor() throws IOException {
		time = 0f;
		
		setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
					System.out.println(String.format("steps.add(Step.newInstance(StepType.up, %S, %S));", time-0.5f, 0.5f));
				
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
	}

	@Override
	public void act(float delta) {
		time += delta;
		super.act(delta);
	}
	
	public void start()
	{
		time = 0f;
	}
	
	
	
	

}
