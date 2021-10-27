package com.physics;

import com.physics.util.DeltaTime;

public class IsaacApp
{
	IsaacApp(Renderer r, Keyboard kbd, Mouse ms)
	{
		renderer = r;
		keyboard = kbd;
		mouse = ms;
	}

	void update(DeltaTime dt)
	{

	}

	void render()
	{
	}

	protected Renderer renderer;
	protected Keyboard keyboard;
	protected Mouse mouse;
}
