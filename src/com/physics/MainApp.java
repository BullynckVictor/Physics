package com.physics;

import java.awt.event.KeyEvent;
import java.time.Duration;

public class MainApp extends Application
{
	public MainApp()
	{
		super("Hello world", 600, 400);
		isaacApp = new IsaacApp(renderer, keyboard, mouse);
		point = new Point();
	}

	@Override
	protected void update(Duration dt)
	{
		isaacApp.update(dt);
		StringBuilder string = new StringBuilder();
		Point pos = mouse.getPosition();
		string.append("x: ");
		string.append(pos.x);
		string.append(", y: ");
		string.append(pos.y);
		renderer.getWindow().setTitle(string.toString());

		if (keyboard.keyPressed('z') || keyboard.keyPressed(KeyEvent.VK_UP))
			point.y -= 1;
		if (keyboard.keyPressed('s') || keyboard.keyPressed(KeyEvent.VK_DOWN))
			point.y += 1;
		if (keyboard.keyPressed('q') || keyboard.keyPressed(KeyEvent.VK_LEFT))
			point.x -= 1;
		if (keyboard.keyPressed('d') || keyboard.keyPressed(KeyEvent.VK_RIGHT))
			point.x += 1;

		point.y += mouse.getScrollNormal();
	}

	@Override
	public void render()
	{
		isaacApp.render();
		Point mousePosition = mouse.getPosition();
		renderer.getGraphics().drawString("Hello world", mousePosition.x, mousePosition.y);
		renderer.getGraphics().drawString("Hello world", point.x, point.y);
	}

	private final IsaacApp isaacApp;
	Point point;
}
