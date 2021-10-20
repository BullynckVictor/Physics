package com.physics;

import java.awt.event.KeyEvent;
import java.time.Duration;

public class MainApp extends Application
{
	public MainApp()
	{
		super("Hello world", 600, 400);
	}

	@Override
	protected void update(Duration dt)
	{
		int speed = 1;
		if (keyboard.keyPressed(KeyEvent.VK_ESCAPE))
			close();

		if (keyboard.keyPressed('z') || keyboard.keyPressed(KeyEvent.VK_UP))
			y -= speed;
		else if (keyboard.keyPressed('s') || keyboard.keyPressed(KeyEvent.VK_DOWN))
			y += speed;
	}

	@Override
	public void render()
	{
		renderer.getGraphics().drawString("Isaac", 50, 50);
		renderer.getGraphics().drawLine(0, 0, 600, 400);
		renderer.getGraphics().drawString("hello", 0, y);
	}

	int y = 0;
}
