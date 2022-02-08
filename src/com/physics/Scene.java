package com.physics;

import com.physics.util.DeltaTime;

import java.awt.event.KeyEvent;

public class Scene {
	public Scene()
	{
		engine = new Engine();
	}
	public Scene(Renderer renderer)
	{
		setRenderer(renderer);
		engine = new Engine();
	}

	void setRenderer(Renderer renderer)
	{
		this.renderer = renderer;
		input = new InputManager(renderer);
		loadActions();
	}

	public void load() throws Exception {
	}
	public void unload()
	{
		engine.clear();
	}

	public void update(DeltaTime dt)
	{
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
	public DeltaTime updateTime = new DeltaTime();

	private int A_MOVE_LEFT;
	private int A_MOVE_RIGHT;
	private int A_MOVE_UP;
	private int A_MOVE_DOWN;
}
