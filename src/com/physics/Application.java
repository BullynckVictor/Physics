package com.physics;
import java.time.Duration;

import com.physics.util.Timer;


public class Application
{
	public Application(String title, int width, int height)
	{
		timer = new Timer();
		renderer = new Renderer(title, width, height, false);
		keyboard = new Keyboard();
		renderer.addKeyboard(keyboard);
	}

	protected void update(Duration dt) {}

	public void close()
	{
		renderer.close();
	}

	public void render()
	{
	}

	public void run()
	{
		int i = 0;
		while(renderer.open())
		{
			update(timer.mark());
			renderer.clear();
			render();
			renderer.present();
		}
		System.out.println("done");
		renderer.dispose();
	}

	private final Timer timer;
	protected Renderer renderer;
	protected Keyboard keyboard;
}