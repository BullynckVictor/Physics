package com.physics;

import com.physics.util.DeltaTime;
import com.physics.util.Timer;

import java.util.TreeMap;


public class Application
{
	public Application(String title, int width, int height, boolean resize)
	{
		timer = new Timer();
		renderer = new Renderer(title, width, height, resize);
		keyboard = new Keyboard();
		mouse = renderer.createMouse();
		renderer.addKeyboard(keyboard);
		scenes = new TreeMap<>();
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

	protected void addScene(String name, Scene scene) throws Exception {
		scenes.put(name, scene);
		scene.setRenderer(renderer);
		setActiveScene(scene);
	}
	protected void setActiveScene(String name) throws Exception
	{
		unloadActiveScene();
		activeScene = scenes.get(name);
		if (activeScene == null)
			throw new IllegalArgumentException("Scene \"" + name + "\" not found in SceneMap");
		renderer.getWindow().setTitle(name);
		activeScene.load();
	}
	protected void setActiveScene(Scene scene) throws Exception
	{
		unloadActiveScene();
		activeScene = scene;
		if (!scenes.containsValue(scene))
			throw new IllegalArgumentException("Scene \"" + scene.toString() + "\" not found in SceneMap");
		renderer.getWindow().setTitle("Unknown Scene");
		activeScene.load();
	}

	protected Scene getActiveScene()
	{
		if (activeScene == null)
			throw new NullPointerException("Active Scene was null, try adding a scene before calling getActiveScene()");
		return activeScene;
	}

	protected void unloadActiveScene()
	{
		if (activeScene != null)
			activeScene.unload();
	}

	private final Timer timer;
	protected Renderer renderer;
	protected Keyboard keyboard;
	protected Mouse mouse;
	private final TreeMap<String, Scene> scenes;
	private Scene activeScene;
}