package de.teampotoo.gamejam6.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationState.AnimationStateListener;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

public class Player {

	/****************************************************************************
	 * enums
	 ****************************************************************************/

	public enum DanceStyle {
		light, middle, crazy
	}

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private DanceStyle currentState;

	private SkeletonRenderer renderer;
	private Texture atlasTexture;

	private AnimationState state;
	private SkeletonData skeletonData;
	private Skeleton skeleton;
	private AnimationStateData stateData;

	boolean useShadow = true;
	boolean useNormals = true;
	boolean flipY = false;

	private SpriteBatch batch = new SpriteBatch();

	/****************************************************************************
	 * getter and setter
	 ****************************************************************************/

	public void setPosition(float x, float y) {
		skeleton.setX(x);
		skeleton.setY(y);
	}

	public void setState(DanceStyle newDance) {

		if (currentState == newDance) {
			return;
		} else {
			currentState = newDance;

			if (currentState == DanceStyle.light) {

				state.setAnimation(0, "move1", true);
			} else if (currentState == DanceStyle.middle) {

				state.setAnimation(0, "move2", false);
			} else if (currentState == DanceStyle.crazy) {

				state.setAnimation(0, "move3", true);
			}
		}
	}

	public DanceStyle getState() {
		return currentState;
	}

	/****************************************************************************
	 * methods
	 ****************************************************************************/

	public void create() {
		renderer = new SkeletonRenderer();

		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/player/player1.atlas"));
		atlasTexture = atlas.getRegions().first().getTexture();

		SkeletonJson json = new SkeletonJson(atlas);
		json.setScale(0.65f);
		skeletonData = json.readSkeletonData(Gdx.files
				.internal("data/player/player1.json"));

		skeleton = new Skeleton(skeletonData);
		stateData = new AnimationStateData(skeletonData);

		state = new AnimationState(stateData);
		state.addListener(new AnimationStateListener() {
			public void event(int trackIndex, Event event) {

			}

			public void complete(int trackIndex, int loopCount) {
			}

			public void start(int trackIndex) {
			}

			public void end(int trackIndex) {
			}
		});
		setState(DanceStyle.light);

		skeleton.setToSetupPose();
		skeleton = new Skeleton(skeleton);
		skeleton.updateWorldTransform();
	}

	public void render() {
		batch.begin();

		state.update(Gdx.graphics.getDeltaTime());
		state.apply(skeleton);

		skeleton.updateWorldTransform();
		skeleton.update(Gdx.graphics.getDeltaTime());

		renderer.draw(batch, skeleton);

		batch.end();
	}

	public void dispose() {
		atlasTexture.dispose();
	}
}
