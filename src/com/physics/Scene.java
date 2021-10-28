package com.physics;

import com.physics.util.DeltaTime;

import java.io.IOException;

public class Scene {
	public Scene() {}
	public Scene(Renderer renderer)
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

	public void load() throws Exception {
	}
	public void unload()
	{
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
