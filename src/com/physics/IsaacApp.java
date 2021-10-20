package com.physics;

import java.time.Duration;

public class IsaacApp
{
	IsaacApp(Renderer r, Keyboard kbd, Mouse ms)
	{
		renderer = r;
		keyboard = kbd;
		mouse = ms;
	}

	void update(Duration dt)
	{

	}

	void render()
	{
		renderer.getGraphics().drawString("Hello world!", 50, 50);
	}

	protected Renderer renderer;
	protected Keyboard keyboard;
	protected Mouse mouse;
}
