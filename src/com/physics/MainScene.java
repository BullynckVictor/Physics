package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;

public class MainScene extends Scene {
	MainScene(Renderer renderer) {
		super(renderer);
	}

	@Override
	public void load() {
		test = new PhysicsObject(0.1f);
		test2 = new PhysicsObject(0.2f);
		engine.addObject(test);
		engine.addObject(test2);
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
				"Velocity: " + test.velocity.length() + "m/s",
				-renderer.getRelativeSize().x + .05f, renderer.getRelativeSize().y - 0.05f,
				Color.BLACK
		);
		renderer.drawStringUI(
				"Depth fallen: " + Math.abs(test.position.y) + "m",
				-renderer.getRelativeSize().x + .05f, renderer.getRelativeSize().y - 0.05f - 0.085f,
				Color.BLACK
		);
	}

	private PhysicsObject test;
	private PhysicsObject test2;

}
