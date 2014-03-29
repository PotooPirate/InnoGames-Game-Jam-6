package de.teampotoo.gamejam6.game.gui.particle;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleEffectActor extends Actor {
	/****************************************************************************
	 * variables
	 ****************************************************************************/
	ParticleEffect effect;

	/****************************************************************************
	 * constructor
	 ****************************************************************************/
	public ParticleEffectActor(ParticleEffect effect) {
		this.effect = effect;
	}

	/****************************************************************************
	 * getter and setter
	 ****************************************************************************/
	public ParticleEffect getEffect() {
		return effect;
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/
	public void draw(SpriteBatch batch, float parentAlpha) {
		effect.draw(batch); // define behavior when stage calls Actor.draw()
	}

	public void act(float delta) {
		super.act(delta);
		effect.setPosition(getX(), getY()); // set to whatever x/y you prefer
		effect.update(delta); // update it
		effect.start(); // need to start the particle spawning
	}

}