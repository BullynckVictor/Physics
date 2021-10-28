package com.physics;

import com.physics.diart.DiartScene;
import com.physics.isaac.IsaacScene;
import com.physics.util.DeltaTime;
import com.physics.util.OptionsReader;
import com.physics.victor.VictorScene;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainApp extends Application
{
	public MainApp() throws Exception {
		super("Hello world", 600, 400, false);

		addScene("Main Development Scene", new MainScene());
		addScene("Isaac Development Scene", new IsaacScene());
		addScene("Diart Development Scene", new DiartScene());
		addScene("Victor Development Scene", new VictorScene());

		OptionsReader options;

		try {
			options = new OptionsReader("Developer.txt");
		}
		catch (IOException e) {
			options = new OptionsReader();
		}

		options.addDefault("starting scene", "Main Development Scene");
		setActiveScene(options.getValue("starting scene"));
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
