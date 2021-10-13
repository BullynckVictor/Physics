package com.physics;

import com.physics.util.Time;

public class MainApp extends Application
{
	@Override
	protected void update(Time dt)
	{
		System.out.println(Long.toString(dt.millis()) + "ms");
	}

	@Override
	protected void render()
	{

	}
}
