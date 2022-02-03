package com.physics.victor;

import com.physics.*;
import com.physics.util.DeltaTime;

import java.awt.*;
import java.awt.event.KeyEvent;

public class VictorScene extends Scene {

	public VictorScene(Renderer renderer)
	{
		super(renderer);
		test = new PhysicsObject(0.1f);
	}

	@Override
	public void load()
	{
		test = new PhysicsObject(0.1f);
		engine.addObject(test);
	}

	@Override
	public void update(DeltaTime dt)
	{
		engine.tick(dt);
	}

	@Override
	public void render()
	{
		renderer.fillCircle(test.position, test.mass, Color.BLUE);
		renderer.drawCircle(test.position, test.mass, Color.BLACK);
		renderer.drawStringUI(
				"Velocity: " + test.velocity.length(),
				-renderer.getRelativeSize().x + .05f, renderer.getRelativeSize().y - 0.05f,
				Color.BLACK
		);
		renderer.drawStringUI(
				"Depth fallen: " + Math.abs(test.position.y),
				-renderer.getRelativeSize().x + .05f, renderer.getRelativeSize().y - 0.05f - 0.085f,
				Color.BLACK
		);
	}

	PhysicsObject test;
}
