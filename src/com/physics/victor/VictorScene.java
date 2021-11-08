package com.physics.victor;

import com.physics.InputManager;
import com.physics.Scene;
import com.physics.Transform;
import com.physics.Vector;
import com.physics.util.DeltaTime;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.security.Key;

public class VictorScene extends Scene {

	public VictorScene()
	{
	}

	@Override
	public void load()
	{
		input = new InputManager(renderer);
		A_MOVE_DOWN	 	= input.newAction();
		A_MOVE_RIGHT 	= input.newAction();
		A_MOVE_UP	 	= input.newAction();
		A_MOVE_LEFT	 	= input.newAction();
		A_ROTATE_LEFT	= input.newAction();
		A_ROTATE_RIGHT	= input.newAction();

		input.addToAction(A_MOVE_DOWN, 's', KeyEvent.VK_DOWN);
		input.addToAction(A_MOVE_RIGHT, 'd', KeyEvent.VK_RIGHT);
		input.addToAction(A_MOVE_UP, 'z', KeyEvent.VK_UP);
		input.addToAction(A_MOVE_LEFT, 'q', KeyEvent.VK_LEFT);
		input.addToAction(A_ROTATE_LEFT, 'a');
		input.addToAction(A_ROTATE_RIGHT, 'e');

		t = new Transform();
		t.position.x = .5f;
		t.position.y = .35f;
		t.rotation = .74f;
		t.scale.x = 2;
	}

	@Override
	public void update(DeltaTime dt)
	{
		input.handleInput();

		Vector delta = new Vector();

		float speed = dt.seconds();

		if (input.getAction(A_MOVE_UP))
			delta.add(0, 1);
		if (input.getAction(A_MOVE_RIGHT))
			delta.add(1, 0);
		if (input.getAction(A_MOVE_DOWN))
			delta.add(0, -1);
		if (input.getAction(A_MOVE_LEFT))
			delta.add(-1, 0);
		if (input.getAction(A_ROTATE_LEFT))
			renderer.camera.rotation += dt.seconds();
		if (input.getAction(A_ROTATE_RIGHT))
			renderer.camera.rotation -= dt.seconds();

		delta.normalise();
		delta.mul(speed);

		renderer.camera.moveRelative(delta);

		renderer.camera.zoom = 1;
		s += dt.seconds();
	}

	@Override
	public void render()
	{
		Graphics2D graphics = renderer.getGraphics();

		double pi = 3.14159265359;

		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(10, 10, 10, 10);

		renderer.fillRectangle(0, 0, 2f, 2f, Color.DARK_GRAY);
		renderer.fillCircle(0, 0, 1, Color.LIGHT_GRAY);
		renderer.fillRectangle(0, 0, .1f, .1f, Color.YELLOW, t);

		renderer.drawLine(-1, 1, 1, -1, Color.WHITE);
	}

	InputManager input;
	int A_MOVE_LEFT = -1;
	int A_MOVE_RIGHT = -1;
	int A_MOVE_UP = -1;
	int A_MOVE_DOWN = -1;
	int A_ROTATE_LEFT = -1;
	int A_ROTATE_RIGHT = -1;
	float s = 0;

	Transform t;
}
