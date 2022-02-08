package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;

public class MainScene extends Scene {
	MainScene(Renderer renderer) {
		super(renderer);
		gravity = new GravityCalculator();
	}

	@Override
	public void load() {
		engine.forceCalculator = gravity;

		test = new PhysicsObject(0.1f);
		test2 = new PhysicsObject(0.2f);

		engine.addObject(test);
		engine.addObject(test2);
		renderer.camera.zoom = 1.0f / 3f;
	}

	@Override
	public void update(DeltaTime dt)
	{
		engine.tick(dt);
	}

	private void renderObject(PhysicsObject object, Color color)
	{
		renderer.fillCircle(object.position, object.mass, color);
		renderer.drawCircle(object.position, object.mass, Color.BLACK);
	}

	@Override
	public void render()
	{
		renderObject(test2, Color.GREEN);
		renderObject(test, Color.BLUE);

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
	private GravityCalculator gravity;
}