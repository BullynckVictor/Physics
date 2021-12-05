package com.physics.victor;

import com.physics.Renderer;
import com.physics.Scene;
import com.physics.Transform;
import com.physics.Vector;
import com.physics.util.DeltaTime;

import java.awt.*;
import java.awt.event.KeyEvent;

public class VictorScene extends Scene {

	public VictorScene(Renderer renderer)
	{
		super(renderer);
		transform = new Transform();

		MOVE_LEFT = input.newAction();
		input.addToAction(MOVE_LEFT, 'Q', KeyEvent.VK_LEFT);
	}

	@Override
	public void load()
	{
	}

	@Override
	public void update(DeltaTime dt)
	{
		if (input.getAction(MOVE_LEFT))
			System.out.println("MOVED LEFT");
	}

	@Override
	public void render()
	{
		float pi = 3.14159f;
		renderer.fillRectangle(0, 0, .5f, .5f, Color.DARK_GRAY, Transform.rotation(pi / 4f));
	}

	Transform transform;
	int MOVE_LEFT;
}
