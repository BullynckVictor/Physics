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
		Debug.setRenderer(renderer);
		Debug.setColor(Color.GREEN);
	}

	@Override
	public void load() {
//		engine.forceCalculator = universalGravity;

		objects.add(new PhysicsObject(new Circle(.1f), .1f));
		objects.add(new PhysicsObject(new Circle(.2f), .2f));
		objects.add(new PhysicsObject(new AAB(.1f, 0.3f), .3f));
		objects.add(new PhysicsObject(new AAB(.25f), .25f));
		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.YELLOW);
		colors.add(Color.RED);

		for (PhysicsObject object : objects)
		{
			object.position = Vector.random();
			object.velocity.sub(object.position);
			object.velocity.normalise();
			object.velocity.div(3);
			engine.addObject(object);
		}
	}
	@Override
	public void unload()
	{
		super.unload();
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
			PhysicsObject object = objects.get(i);
			boolean collides = false;
			for (int j = 0; j < objects.size() && !collides; ++j)
				if (i != j)
					if (CollisionHandler.collide(object, objects.get(j)))
						collides = true;
			renderer.drawObject(object, collides ? Color.WHITE : colors.get(i));
			Debug.drawVector(object.velocity, object.position);
		}
		if (Debug.enabled())
			for (PhysicsObject object : objects)
				Debug.drawVector(object.velocity, object.position);
	}

	private final GravityCalculator gravity;
	private final UniversalGravityCalculator universalGravity;
	private final ArrayList<PhysicsObject> objects;
	private final ArrayList<Color> colors;
}