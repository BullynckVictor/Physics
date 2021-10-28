package com.physics;

import com.physics.diart.DiartScene;
import com.physics.isaac.IsaacScene;
import com.physics.util.DeltaTime;
import com.physics.victor.VictorScene;

public class MainApp extends Application
{
	public MainApp() throws Exception {
		super("Hello world", 600, 400, false);

		addScene("Main Development Scene", new MainScene());
		addScene("Isaac Development Scene", new IsaacScene());
		addScene("Diart Development Scene", new DiartScene());
		addScene("Victor Development Scene", new VictorScene());
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
