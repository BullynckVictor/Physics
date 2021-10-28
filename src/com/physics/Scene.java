package com.physics;

import com.physics.util.DeltaTime;

public class Scene {
	Scene() {}
	Scene(Renderer renderer)
	{
		setRenderer(renderer);
	}

	void setRenderer(Renderer renderer)
	{
		this.renderer = renderer;
		mouse = renderer.createMouse();
		keyboard = new Keyboard();
		renderer.addKeyboard(keyboard);
	}

	public void update(DeltaTime dt)
	{
	}

	public void render()
	{
	}

	protected Renderer renderer;
	protected Keyboard keyboard;
	protected Mouse mouse;
}
