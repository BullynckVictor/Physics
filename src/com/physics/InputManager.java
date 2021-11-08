package com.physics;

import java.util.ArrayList;
import java.util.TreeMap;

public class InputManager {

	public InputManager()
	{
		renderer = null;
		keyboard = new Keyboard();
		mouse = new Mouse();
		actions = new ArrayList<>();
		keyToActionMap = new TreeMap<>();
	}

	public InputManager(Renderer renderer)
	{
		this.renderer = renderer;
		mouse = renderer.createMouse();
		keyboard = new Keyboard();
		renderer.addKeyboard(keyboard);
		actions = new ArrayList<>();
		keyToActionMap = new TreeMap<>();
	}

	Mouse getMouse() { return mouse; }
	Keyboard getKeyboard() { return keyboard; }

	public void handleInput()
	{
		ArrayList<Integer> pressedKeys = keyboard.pressedKeys();
		for (int k : pressedKeys)
		{
			int action = keyToActionMap.getOrDefault(k, -1);
			if (action != -1 && action < keyToActionMap.size())
				actions.set(action, true);
		}
	}

	public int newAction()
	{
		actions.add(false);
		return actionCount++;
	}

	public void addToAction(int action, int... keys)
	{
		for (int k : keys)
			keyToActionMap.put(k, action);
	}

	public boolean getAction(int action)
	{
		if (action >= actions.size() || action < 0)
			return false;
		boolean a = actions.get(action);
		actions.set(action, false);
		return a;
	}

	private final Renderer renderer;
	private final Keyboard keyboard;
	private final Mouse mouse;
	private int actionCount = 0;
	final TreeMap<Integer, Integer> keyToActionMap;
	final ArrayList<Boolean> actions;
}
