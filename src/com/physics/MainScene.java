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
//		engine.forceCalculator = universalGravity;

		circle1 = new PhysicsObject(new Circle(.1f), .1f);
		circle2 = new PhysicsObject(new Circle(.2f), .2f);
		box1 = new PhysicsObject(new AAB(.1f, 0.3f), .3f);
		box2 = new PhysicsObject(new AAB(.25f), .25f);

		circle1.position.x = ((float)Math.random() * 2) - 1;
		circle1.position.y = ((float)Math.random() * 2) - 1;
		circle2.position.x = ((float)Math.random() * 2) - 1;
		circle2.position.y = ((float)Math.random() * 2) - 1;
		box1.position.x = ((float)Math.random() * 2) - 1;
		box1.position.y = ((float)Math.random() * 2) - 1;
		box2.position.x = ((float)Math.random() * 2) - 1;
		box2.position.y = ((float)Math.random() * 2) - 1;

		circle1.velocity.sub(circle1.position);
		circle2.velocity.sub(circle2.position);
		box1.velocity.sub(box1.position);
		box2.velocity.sub(box2.position);
		circle1.velocity.normalise();
		circle2.velocity.normalise();
		box1.velocity.normalise();
		box2.velocity.normalise();
		circle1.velocity.div(3);
		circle2.velocity.div(3);
		box1.velocity.div(3);
		box2.velocity.div(3);

		engine.addObject(circle1);
		engine.addObject(circle2);
		engine.addObject(box1);
		engine.addObject(box2);
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
		renderer.drawObject(circle1, Color.GREEN);
		renderer.drawObject(circle2, Color.RED);
		renderer.drawObject(box1, Color.BLUE);
		renderer.drawObject(box2, Color.YELLOW);
	}

	private PhysicsObject circle1;
	private PhysicsObject circle2;
	private PhysicsObject box1;
	private PhysicsObject box2;
	private final GravityCalculator gravity;
	private final UniversalGravityCalculator universalGravity;
}