package com.physics;

import com.physics.util.Timer;

import java.awt.event.KeyEvent;
import java.time.Duration;

public class MainApp extends Application
{
	public MainApp()
	{
		super("Hello world", 600, 400);
		System.out.println(renderer.getPanelSize());
	}

	@Override
	protected void update(Duration dt)
	{
		String input = keyboard.peekInput();
		if (lastLength != input.length())
		{
			renderer.setTitle(input);
			lastLength = input.length();
		}
		if (keyboard.keyPressed(KeyEvent.VK_ESCAPE))
			close();
	}

	@Override
	protected void render()
	{
		graphics().drawLine(0, 0, 600, 400);
		graphics().drawString("hello", 0, 0);
	}

	int lastLength = 0;
}
