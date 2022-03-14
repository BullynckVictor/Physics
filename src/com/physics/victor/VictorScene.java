package com.physics.victor;

import com.physics.*;
import com.physics.util.DeltaTime;

import java.awt.*;
import java.awt.event.KeyEvent;

public class VictorScene extends Scene {

	public VictorScene(Renderer renderer, SceneHandler sceneHandler)
	{
		super(renderer, sceneHandler);
	}

	@Override
	public void load()
	{
	}

	@Override
	public void update(DeltaTime dt) throws Exception
	{
		engine.compute(dt);
		updateScene();
	}

	@Override
	public void render()
	{
	}
}
