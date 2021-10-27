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

		if (keyboard.keyPressed('z') || keyboard.keyPressed(KeyEvent.VK_UP))
			renderer.camera.y -= speed * dt.seconds();
		if (keyboard.keyPressed('s') || keyboard.keyPressed(KeyEvent.VK_DOWN))
			renderer.camera.y += speed * dt.seconds();
		if (keyboard.keyPressed('q') || keyboard.keyPressed(KeyEvent.VK_LEFT))
			renderer.camera.x -= speed * dt.seconds();
		if (keyboard.keyPressed('d') || keyboard.keyPressed(KeyEvent.VK_RIGHT))
			renderer.camera.x += speed * dt.seconds();
		if (keyboard.keyPressed('a'))
			renderer.camera.rotation += rotateSpeed * dt.seconds();
		if (keyboard.keyPressed('e'))
			renderer.camera.rotation -= rotateSpeed * dt.seconds();

		float e = 0.5772156649f;
		renderer.camera.zoom *= Math.pow(e, (float)mouse.getScrollNormal());
	}

	@Override
	public void render()
	{
		isaacApp.render();
		Point mousePosition = mouse.getPosition();
		renderer.getGraphics().drawString("time since startup: " + new DeltaTime(timer.peek()).seconds(), 0, 50);
		renderer.getGraphics().drawString("zoom: " + renderer.camera.zoom, 0, 60);

		renderer.background = Color.LIGHT_GRAY;
		Point p1 = renderer.transform(-1f, 1f);
		Point p2 = renderer.transform( 1f, 1f);
		Point p3 = renderer.transform( 1f,-1f);
		Point p4 = renderer.transform(-1f,-1f);
		renderer.getGraphics().drawPolygon( new Polygon(
					new int[]{ p1.x, p2.x, p3.x, p4.x },
					new int[]{ p1.y, p2.y, p3.y, p4.y },
					4
		));
		renderer.getGraphics().drawLine(p1.x, p1.y, p3.x, p3.y);
	}

	private final IsaacApp isaacApp;
	Timer timer = new Timer();
}
