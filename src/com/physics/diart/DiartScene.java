package com.physics.diart;

import com.physics.Renderer;
import com.physics.Scene;
import com.physics.SceneHandler;
import com.physics.util.DeltaTime;

public class DiartScene extends Scene {

	public DiartScene(Renderer renderer, SceneHandler sceneHandler)
	{
		super(renderer, sceneHandler);
	}

	@Override
	public void update(DeltaTime dt) throws Exception
	{
		updateScene();
	}

	@Override
	public void render()
	{
	}
}
