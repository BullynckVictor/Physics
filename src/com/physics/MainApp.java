package com.physics;

import com.physics.util.DeltaTime;
import com.physics.util.Timer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainApp extends Application
{
	public MainApp()
	{
		super("Hello world", 600, 400, false);
		isaacApp = new IsaacApp(renderer, keyboard, mouse);
	}

	@Override
	protected void update(DeltaTime dt)
	{
		float speed = 1.0f;
		float rotateSpeed = 1.0f;

		isaacApp.update(dt);

		float dx = 0;
		float dy = 0;

		if (keyboard.keyPressed('z') || keyboard.keyPressed(KeyEvent.VK_UP))
			dy += speed * dt.seconds();
		if (keyboard.keyPressed('s') || keyboard.keyPressed(KeyEvent.VK_DOWN))
			dy -= speed * dt.seconds();
		if (keyboard.keyPressed('q') || keyboard.keyPressed(KeyEvent.VK_LEFT))
			dx -= speed * dt.seconds();
		if (keyboard.keyPressed('d') || keyboard.keyPressed(KeyEvent.VK_RIGHT))
			dx += speed * dt.seconds();
		if (keyboard.keyPressed('a'))
			renderer.camera.rotation += rotateSpeed * dt.seconds();
		if (keyboard.keyPressed('e'))
			renderer.camera.rotation -= rotateSpeed * dt.seconds();

		renderer.camera.moveRelative(dx, dy);

		float e = 0.5772156649f;
		renderer.camera.zoom *= Math.pow(e, (float)mouse.getScrollNormal());
	}

	@Override
	public void render()
	{
		final float pi = 3.14159265359f;
		renderer.getGraphics().setColor(Color.BLACK);
		isaacApp.render();
		renderer.getGraphics().drawString("zoom: " + renderer.camera.zoom, 0, 60);
		renderer.getGraphics().drawString("angle: " + (int)(renderer.camera.rotation * 180f / pi) % 360 + "°", 0, 48);
		renderer.background = Color.LIGHT_GRAY;
		Point p1 = renderer.transform(-.5f, .5f);
		Point p2 = renderer.transform( .5f, .5f);
		Point p3 = renderer.transform( .5f,-.5f);
		Point p4 = renderer.transform(-.5f,-.5f);
		Point c1 = renderer.transform(-.1f, 0);
		Point c2 = renderer.transform( .1f, 0);
		Point c3 = renderer.transform(   0, .1f);
		Point c4 = renderer.transform(   0,-.1f);
		renderer.getGraphics().drawPolygon( new Polygon(
					new int[]{ p1.x, p2.x, p3.x, p4.x },
					new int[]{ p1.y, p2.y, p3.y, p4.y },
					4
		));
		renderer.getGraphics().drawLine(p1.x, p1.y, p3.x, p3.y);

		renderer.getGraphics().setColor(Color.BLUE);
		renderer.getGraphics().drawLine(c1.x, c1.y, c2.x, c2.y);
		renderer.getGraphics().drawLine(c3.x, c3.y, c4.x, c4.y);
	}

	private final IsaacApp isaacApp;
	Timer timer = new Timer();
}
