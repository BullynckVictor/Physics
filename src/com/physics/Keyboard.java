package com.physics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Keyboard implements KeyListener
{
	public Keyboard()
	{
		pressedKeys = new boolean[256];
		pressedKeyMap = new TreeMap<>();
		input = new StringBuffer();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		input.append(e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setPressedKey(e.getKeyChar(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		setPressedKey(e.getKeyChar(), false);
	}

	public String getInput()
	{
		String ret = input.toString();
		input.setLength(0);
		return ret;
	}
	public String peekInput()
	{
		return input.toString();
	}

	public boolean keyPressed(int key)
	{
		return getPressedKey(key);
	}

	private void setPressedKey(int key, boolean value)
	{
		if (key < 256)
			pressedKeys[key] = value;
		else if (key != KeyEvent.CHAR_UNDEFINED)
			pressedKeyMap.put(key, value);
	}

	private boolean getPressedKey(int key)
	{
		if (key < 256)
			return pressedKeys[key];
		else if (key != KeyEvent.CHAR_UNDEFINED)
			return pressedKeyMap.get(key);
		return false;
	}

	private final boolean[] pressedKeys;
	private final TreeMap<Integer, Boolean> pressedKeyMap;
	private final StringBuffer input;
}
