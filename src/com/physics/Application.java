package com.physics;

import com.physics.util.DeltaTime;
import com.physics.util.Timer;


public class Application
{
	public Application(String title, int width, int height, boolean resize)
	{
		timer = new Timer();
		renderer = new Renderer(title, width, height, resize);
		keyboard = new Keyboard();
		mouse = renderer.createMouse();
		renderer.addKeyboard(keyboard);
	}

	protected void update(DeltaTime dt) {}

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
		while (renderer.open()) {
			update(new DeltaTime(timer.mark()));
			renderer.clear();
			render();
			renderer.present();
		}
		renderer.dispose();
	}

	public void dispose()
	{
		renderer.dispose();
	}

	private final Timer timer;
	protected Renderer renderer;
	protected Keyboard keyboard;
	protected Mouse mouse;
}