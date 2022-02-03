package com.physics;

import com.physics.util.DeltaTime;

public class Scene {
	public Scene() { engine = new Engine(); }
	public Scene(Renderer renderer)
	{
		setRenderer(renderer);
		engine = new Engine();
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
		engine.clear();
	}

	public void update(DeltaTime dt)
	{
	}

	public void render()
	{
	}

	protected Renderer renderer;
	protected InputManager input;
	protected final Engine engine;
	public DeltaTime updateTime = new DeltaTime();
}
