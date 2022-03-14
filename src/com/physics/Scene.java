package com.physics;

import com.physics.util.DeltaTime;
import com.physics.util.OptionsReader;

import java.awt.event.KeyEvent;
import java.time.temporal.ChronoUnit;

public class Scene {
	public Scene()
	{
		engine = new Engine();
	}
	public Scene(Renderer renderer, SceneHandler sceneHandler)
	{
		setRenderer(renderer);
		this.sceneHandler = sceneHandler;
		engine = new Engine();
		OptionsReader options = new OptionsReader("Developer.txt");
		options.addDefault("engine update delta", "16");
		try {
			engine.dt.set(Integer.parseInt(options.getValue("engine update delta")), ChronoUnit.MILLIS);
		} catch (NumberFormatException e) {
			engine.dt.set(16, ChronoUnit.MILLIS);
		}
	}

	public void setRenderer(Renderer renderer)
	{
		if (this.renderer != renderer) {
			this.renderer = renderer;
			input = new InputManager(renderer);
			loadActions();
		}
	}

	public void load() {
		input.keyboard.clear();
	}
	public void unload()
	{
		engine.clear();
	}

	public void update(DeltaTime dt) throws Exception {
	}
	public void render()
	{
	}

	protected void controlCamera(float speed)
	{
		Vector delta = new Vector();

		if (input.getAction(A_MOVE_LEFT))
			delta.x -= 1;
		if (input.getAction(A_MOVE_RIGHT))
			delta.x += 1;
		if (input.getAction(A_MOVE_UP))
			delta.y += 1;
		if (input.getAction(A_MOVE_DOWN))
			delta.y -= 1;

		delta.normalise();
		delta.mul(speed);

		renderer.camera.moveRelative(delta);
	}

	protected void updateScene() throws Exception
	{
		if (input.keyboard.keyFlagged(KeyEvent.VK_BACK_SPACE) != 0)
			sceneHandler.rollbackScene();
		if (input.keyboard.keyFlagged(KeyEvent.VK_SHIFT) != 0)
			sceneHandler.advanceScene();
	}

	private void loadActions()
	{
		A_MOVE_LEFT = input.newAction();
		A_MOVE_RIGHT = input.newAction();
		A_MOVE_UP = input.newAction();
		A_MOVE_DOWN = input.newAction();

		input.addToAction(A_MOVE_LEFT, 'Q', KeyEvent.VK_LEFT);
		input.addToAction(A_MOVE_RIGHT, 'D', KeyEvent.VK_RIGHT);
		input.addToAction(A_MOVE_UP, 'Z', KeyEvent.VK_UP);
		input.addToAction(A_MOVE_DOWN, 'S', KeyEvent.VK_DOWN);
	}

	protected Renderer renderer;
	protected InputManager input;
	protected final Engine engine;
	protected SceneHandler sceneHandler;
	public DeltaTime updateTime = new DeltaTime();

	private int A_MOVE_LEFT;
	private int A_MOVE_RIGHT;
	private int A_MOVE_UP;
	private int A_MOVE_DOWN;
}
