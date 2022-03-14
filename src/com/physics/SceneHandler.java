package com.physics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SceneHandler {

	public SceneHandler()
	{
		scenes = new TreeMap<>();
		sceneList = new ArrayList<>();
		names = new ArrayList<>();
		activeScene = null;
		renderer = null;
	}
	public SceneHandler(Renderer renderer)
	{
		scenes = new TreeMap<>();
		sceneList = new ArrayList<>();
		names = new ArrayList<>();
		activeScene = null;
		this.renderer = renderer;
	}

	public void addScene(String name, Scene scene) {
		scenes.put(name, scene);
		sceneList.add(scene);
		names.add(name);
		scene.setRenderer(renderer);
	}
	public void setActiveScene(String name) throws Exception
	{
		unloadActiveScene();
		activeScene = scenes.get(name);
		if (activeScene == null)
			throw new IllegalArgumentException("Scene \"" + name + "\" not found in SceneMap");
		renderer.getWindow().setTitle(name);
		activeScene.load();
	}
	public void setActiveScene(Scene scene) throws Exception
	{
		unloadActiveScene();
		activeScene = scene;
		boolean found = false;

		if (!scenes.containsValue(scene))
			throw new IllegalArgumentException("Scene \"" + scene.toString() + "\" not found in SceneMap");
		renderer.getWindow().setTitle(names.get(sceneList.lastIndexOf(scene)));
		activeScene.load();
	}

	public void advanceScene() throws Exception
	{
		setActiveScene(sceneList.get((sceneList.lastIndexOf(activeScene) + 1) % sceneList.size()));
	}
	public void rollbackScene() throws Exception
	{
		int index = sceneList.lastIndexOf(activeScene) - 1;
		if (index == -1)
			index = sceneList.size() - 1;
		setActiveScene(sceneList.get(index));
	}

	public Scene getActiveScene()
	{
		if (activeScene == null)
			throw new NullPointerException("Active Scene was null, try adding a scene before calling getActiveScene()");
		return activeScene;
	}

	public void unloadActiveScene()
	{
		if (activeScene != null)
			activeScene.unload();
	}

	private final TreeMap<String, Scene> scenes;
	private final ArrayList<Scene> sceneList;
	private final ArrayList<String> names;
	private Scene activeScene;
	private Renderer renderer;
}
