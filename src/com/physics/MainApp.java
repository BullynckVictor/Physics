package com.physics;

import com.physics.util.DeltaTime;

public class MainApp extends Application
{
	public MainApp()
	{
		super("Hello world", 600, 400, false);

		addScene("Main Development Scene", new MainScene());
		addScene("Isaac Development Scene", new IsaacScene());
		setActiveScene("Main Development Scene");
	}

	@Override
	protected void update(DeltaTime dt)
	{
		getActiveScene().update(dt);
	}

	@Override
	public void render()
	{
		getActiveScene().render();
	}
}
