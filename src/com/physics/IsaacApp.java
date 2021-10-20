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
	}

	protected Renderer renderer;
	protected Keyboard keyboard;
	protected Mouse mouse;
}
