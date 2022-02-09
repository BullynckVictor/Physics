package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;

public class MainScene extends Scene {
	MainScene(Renderer renderer) {
		super(renderer);
		gravity = new GravityCalculator();
		universalGravity = new UniversalGravityCalculator(1f);
	}

	@Override
	public void load() {
		engine.forceCalculator = universalGravity;

		test1 = new PhysicsObject(new Circle(.1f), .1f);
		test2 = new PhysicsObject(new AAB(.1f), .2f);

		test1.position.x = -0.75f;
		test2.position.x =  0.75f;
		test1.force.y += 0.07f;

		engine.addObject(test1);
		engine.addObject(test2);
	}

	@Override
	public void update(DeltaTime dt)
	{
		engine.tick(dt);
		controlCamera(dt.seconds());
	}

	@Override
	public void render()
	{
		renderer.drawObject(test2, Color.GREEN);
		renderer.drawObject(test1, Color.BLUE);
	}

	private PhysicsObject test1;
	private PhysicsObject test2;
	private final GravityCalculator gravity;
	private final UniversalGravityCalculator universalGravity;
}