package com.physics;
import java.awt.Graphics2D;
import java.time.Duration;

import com.physics.util.Timer;


public class Application
{
	public Application(String title, int width, int height)
	{
		timer = new Timer();
		renderer = new Renderer(title, width, height);
		keyboard = new Keyboard();
		renderer.addKeyListener(keyboard);
	}

	protected void update(Duration dt) {}
	protected void render() {}

	public void close()
	{
		renderer.close();
	}

	public void run()
	{
		int i = 0;
		while(renderer.open())
		{
			update(timer.mark());
			render();
		}
		System.out.println("done");
		renderer.dispose();
	}

	protected Graphics2D graphics()
	{
		return renderer.graphics();
	}

	private Timer timer;
	protected Renderer renderer;
	protected Keyboard keyboard;
}