package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;
import java.util.ArrayList;

public class MainScene extends Scene {
	MainScene(Renderer renderer) {
		super(renderer);
		gravity = new GravityCalculator();
		universalGravity = new UniversalGravityCalculator(1f);
		objects = new ArrayList<>();
		colors = new ArrayList<>();
	}

	@Override
	public void load() {
//		engine.forceCalculator = universalGravity;

		PhysicsObject circle1 = new PhysicsObject(new Circle(.1f), .1f);
		PhysicsObject circle2 = new PhysicsObject(new Circle(.2f), .2f);
		PhysicsObject box1 = new PhysicsObject(new AAB(.1f, 0.3f), .3f);
		PhysicsObject box2 = new PhysicsObject(new AAB(.25f), .25f);

		objects.add(circle1);
		objects.add(circle2);
		objects.add(box1);
		objects.add(box2);
		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.YELLOW);
		colors.add(Color.RED);

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
	public void unload()
	{
		colors.clear();
		objects.clear();
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
		for (int i = 0; i < objects.size(); ++i) {
			boolean collides = false;
			for (int j = 0; j < objects.size() && !collides; ++j)
				if (i != j)
					if (CollisionHandler.collide(objects.get(i), objects.get(j)))
						collides = true;
			renderer.drawObject(objects.get(i), collides ? Color.WHITE : colors.get(i));
		}
	}

	private final GravityCalculator gravity;
	private final UniversalGravityCalculator universalGravity;
	private final ArrayList<PhysicsObject> objects;
	private final ArrayList<Color> colors;
}