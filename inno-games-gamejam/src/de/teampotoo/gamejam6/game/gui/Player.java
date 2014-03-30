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
		level1, level2, level3, level4, level5, losing, wine, wineing
	}

	/****************************************************************************
	 * variables
	 ****************************************************************************/

	private static final float DEFAULT_SCALE = 0.65f;
	
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
	
	private int height = 800;
	private int width = 300;
	private float scale = 1.0f;

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

			if (currentState == DanceStyle.level1) {

				state.setAnimation(0, "move1", true);
			} else if (currentState == DanceStyle.level2) {

				state.setAnimation(0, "move2", true);
			} else if (currentState == DanceStyle.level3) {

				state.setAnimation(0, "move3", true);
			} else if (currentState == DanceStyle.level4) {

				state.setAnimation(0, "move4", true);
			} else if (currentState == DanceStyle.level5) {

				state.setAnimation(0, "move5", true);
			} else if (currentState == DanceStyle.losing) {

				state.setAnimation(0, "move0", true);
			} else if (currentState == DanceStyle.wine) {

				state.setAnimation(0, "wine", false);
			} else if (currentState == DanceStyle.wineing) {

				state.setAnimation(0, "wineing", true);
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
		scale = DEFAULT_SCALE;
		json.setScale(scale);
		skeletonData = json.readSkeletonData(Gdx.files
				.internal("data/player/player1.json"));

		skeleton = new Skeleton(skeletonData);
		stateData = new AnimationStateData(skeletonData);

		stateData.setMix("move0", "move1", 0.2f);
		stateData.setMix("move1", "move2", 0.2f);
		stateData.setMix("move2", "move3", 0.2f);
		stateData.setMix("move3", "move4", 0.2f);
		stateData.setMix("move4", "move5", 0.2f);
		stateData.setMix("move5", "move4", 0.2f);
		stateData.setMix("move4", "move3", 0.2f);
		stateData.setMix("move3", "move2", 0.2f);
		stateData.setMix("move2", "move1", 0.2f);
		stateData.setMix("move1", "move0", 0.2f);

		state = new AnimationState(stateData);
		state.addListener(new AnimationStateListener() {
			public void event(int trackIndex, Event event) {

			}

			public void complete(int trackIndex, int loopCount) {
				if (currentState == DanceStyle.wine) {
					setState(DanceStyle.wineing);
				}
			}

			public void start(int trackIndex) {
			}

			public void end(int trackIndex) {
			}
		});
		setState(DanceStyle.level1);

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

	public int getHeight() {
		return (int) (height * scale);
	}

	public int getWidth() {
		return (int) (width * scale);
	} 
}
