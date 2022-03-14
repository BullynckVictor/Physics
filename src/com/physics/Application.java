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
		sceneHandler = new SceneHandler(renderer);
	}

	protected void update(DeltaTime dt) throws Exception {}
	public void close()
	{
		renderer.close();
	}
	public void render()
	{
	}

	public void run() throws Exception
	{
		while (renderer.open()) {
			tick();
		}
		renderer.dispose();
	}

	private void tick() throws Exception
	{
		renderer.clear();
		update(new DeltaTime(timer.mark()));
		render();
		renderer.present();
	}

	public void dispose()
	{
		renderer.dispose();
	}



	private final Timer timer;
	protected Renderer renderer;
	protected Keyboard keyboard;
	protected Mouse mouse;
	protected final SceneHandler sceneHandler;
}