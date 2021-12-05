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
		input = new InputManager(renderer);
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
	protected InputManager input;
}
