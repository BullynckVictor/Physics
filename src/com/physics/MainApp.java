package com.physics;

import com.physics.diart.DiartScene;
import com.physics.isaac.IsaacScene;
import com.physics.util.DeltaTime;
import com.physics.util.OptionsReader;
import com.physics.victor.VictorScene;

import java.time.temporal.ChronoUnit;


public class MainApp extends Application
{
	public MainApp() throws Exception {
		super("Hello world", 600, 400, resizeable());

		sceneHandler.addScene("Main Development Scene", new MainScene(renderer, sceneHandler));
//		sceneHandler.addScene("Isaac Development Scene", new IsaacScene(renderer, sceneHandler));
//		sceneHandler.addScene("Diart Development Scene", new DiartScene(renderer, sceneHandler));
//		sceneHandler.addScene("Victor Development Scene", new VictorScene(renderer, sceneHandler));
		sceneHandler.addScene("Earth Demo Scene", new EarthScene(renderer, sceneHandler));

		OptionsReader options = new OptionsReader("Developer.txt");
		options.addDefault("starting scene", "Main Development Scene");

		sceneHandler.setActiveScene(options.getValue("starting scene"));
	}

	static boolean resizeable()
	{
		OptionsReader options;
		options = new OptionsReader("Developer.txt");
		options.addDefault("resizeable", "false");

		return options.getValue("resizeable").equalsIgnoreCase("true");
	}

	@Override
	protected void update(DeltaTime dt) throws Exception
	{
		sceneHandler.getActiveScene().input.handleInput();
		sceneHandler.getActiveScene().update(dt);
	}

	@Override
	public void render()
	{
		sceneHandler.getActiveScene().render();
	}
}
