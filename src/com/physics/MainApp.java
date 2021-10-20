package com.physics;

import java.awt.event.KeyEvent;
import java.time.Duration;

public class MainApp extends Application
{
	public MainApp()
	{
		super("Hello world", 600, 400);
		isaacApp = new IsaacApp(renderer, keyboard, mouse);
	}

	@Override
	protected void update(Duration dt)
	{
		isaacApp.update(dt);
	}

	@Override
	public void render()
	{
		isaacApp.render();
	}

	private final IsaacApp isaacApp;
	int y = 0;
}
