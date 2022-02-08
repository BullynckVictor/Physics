package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;

public class MainScene extends Scene {
	MainScene(Renderer renderer) {
		super(renderer);
		gravity = new GravityCalculator();
		universalGravity = new UniversalGravityCalculator(0.5f);
	}

	@Override
	public void load() {
		engine.forceCalculator = universalGravity;

		test1 = new PhysicsObject(0.1f);
		test2 = new PhysicsObject(0.2f);


		test1.position.x = -0.75f;
		test2.position.x =  0.75f;

		engine.addObject(test1);
		engine.addObject(test2);
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
		renderObject(test1, Color.BLUE);

		renderer.drawStringUI(
				"Velocity: " + test1.velocity.length() + "m/s",
				-renderer.getRelativeSize().x + .05f, renderer.getRelativeSize().y - 0.05f,
				Color.BLACK
		);
		renderer.drawStringUI(
				"Depth fallen: " + Math.abs(test1.position.y) + "m",
				-renderer.getRelativeSize().x + .05f, renderer.getRelativeSize().y - 0.05f - 0.085f,
				Color.BLACK
		);
	}

	private PhysicsObject test1;
	private PhysicsObject test2;
	private final GravityCalculator gravity;
	private final UniversalGravityCalculator universalGravity;
}