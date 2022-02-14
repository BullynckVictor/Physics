package com.physics.victor;

import com.physics.*;
import com.physics.util.DeltaTime;

import java.awt.*;
import java.awt.event.KeyEvent;

public class VictorScene extends Scene {

	public VictorScene(Renderer renderer)
	{
		super(renderer);
	}

	@Override
	public void load()
	{
	}

	@Override
	public void update(DeltaTime dt)
	{
		engine.compute(dt);
	}

	@Override
	public void render()
	{
	}
}
