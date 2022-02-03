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

		addScene("Main Development Scene", new MainScene(renderer));
		addScene("Isaac Development Scene", new IsaacScene(renderer));
		addScene("Diart Development Scene", new DiartScene(renderer));
		addScene("Victor Development Scene", new VictorScene(renderer));

		OptionsReader options;
		options = new OptionsReader("Developer.txt");
		options.addDefault("starting scene", "Main Development Scene");

		setActiveScene(options.getValue("starting scene"));
	}

	static boolean resizeable()
	{
		OptionsReader options;
		options = new OptionsReader("Developer.txt");
		options.addDefault("resizeable", "false");

		return options.getValue("resizeable").equalsIgnoreCase("true");
	}

	@Override
	protected void update(DeltaTime dt)
	{
		getActiveScene().input.handleInput();
		getActiveScene().update(dt);
	}

	@Override
	public void render()
	{
		getActiveScene().render();
	}
}
