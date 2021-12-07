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
		MOVE_UP = input.newAction();
		MOVE_RIGHT = input.newAction();
		MOVE_DOWN = input.newAction();
		ROTATE_LEFT = input.newAction();
		ROTATE_RIGHT = input.newAction();

		input.addToAction(MOVE_LEFT, 'Q', KeyEvent.VK_LEFT);
		input.addToAction(MOVE_UP, 'Z', KeyEvent.VK_UP);
		input.addToAction(MOVE_RIGHT, 'D', KeyEvent.VK_RIGHT);
		input.addToAction(MOVE_DOWN, 'S', KeyEvent.VK_DOWN);
		input.addToAction(ROTATE_LEFT, 'A');
		input.addToAction(ROTATE_RIGHT, 'E');

		transform.rotation = (float)Math.PI / 4f;
	}

	@Override
	public void load()
	{
	}

	@Override
	public void update(DeltaTime dt)
	{
		Vector delta = new Vector();

		if (input.getAction(MOVE_LEFT))
			delta.x -= 1;
		if (input.getAction(MOVE_UP))
			delta.y += 1;
		if (input.getAction(MOVE_RIGHT))
			delta.x += 1;
		if (input.getAction(MOVE_DOWN))
			delta.y -= 1;
		if (input.getAction(ROTATE_LEFT))
			renderer.camera.rotation += dt.seconds();
		if (input.getAction(ROTATE_RIGHT))
			renderer.camera.rotation -= dt.seconds();
		if (input.keyboard.keyPressed(KeyEvent.VK_SPACE))
			renderer.setSize(500, 900);

		delta.normalise();
		delta.mul(dt.seconds());
		renderer.camera.moveRelative(delta);
	}

	@Override
	public void render()
	{
		renderer.setFont("Consolas", Font.BOLD, 12);
		renderer.drawRectangle(0, 0, .5f, .5f, Color.DARK_GRAY, transform);
		renderer.drawString("Hello renderer.drawString()!", 0, 0, Color.BLUE);
		renderer.drawStringUI("UI string", -renderer.getRelativeSize().x + .03f, .9f, Color.BLACK);
	}

	Transform transform;
	int MOVE_LEFT;
	int MOVE_UP;
	int MOVE_RIGHT;
	int MOVE_DOWN;
	int ROTATE_LEFT;
	int ROTATE_RIGHT;
}
