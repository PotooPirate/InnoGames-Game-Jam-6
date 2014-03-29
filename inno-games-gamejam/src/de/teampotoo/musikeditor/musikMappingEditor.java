package de.teampotoo.musikeditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class musikMappingEditor extends Group {
	private float time = 0;
	
	public musikMappingEditor() {
		
		setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				
				
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
	}
	
	

}
