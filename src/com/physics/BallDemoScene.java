package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;

public class BallDemoScene extends Scene {

	public BallDemoScene(Renderer renderer, SceneHandler sceneHandler)
	{
		super(renderer, sceneHandler);
		Debug.setRenderer(renderer);
		Debug.setColor(Color.GREEN);
	}

	@Override
	public void load() {
		super.load();
		engine.forceCalculator = new GravityCalculator(.1f, Vector.UP);

		balls = new PhysicsObject[(int)(Math.random() * 10) + 50];
		colors = new Color[balls.length];

		Vector relative = renderer.getRelativeSize();
		Vector bounds = Vector.mul(relative, 0.75f);
		for (int i = 0; i < balls.length; ++i)
		{
			balls[i] = new PhysicsObject(new Circle((float)Math.random() / 6 + 0.05f));
			boolean collides = true;
			while (collides) {
				balls[i].position = Vector.mul(Vector.random(), relative);
				if (Math.abs(balls[i].position.x) > bounds.x || Math.abs(balls[i].position.y) > bounds.y)
					continue;
				collides = false;
				for (int j = 0; j < i; ++j)
					if (CollisionHandler.collide(balls[i], balls[j]))
					{
						collides = true;
						break;
					}
				((Circle)balls[i].collider).radius *= 0.9f;
			}
			if (balls[i].position.x == Float.NaN)
				balls[i].position.x = 0;
//			balls[i].velocity = Vector.random();
			if (balls[i] != null)
				engine.addObject(balls[i]);
		}
		for (int i = 0; i < colors.length; ++i)
			colors[i] = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));

		float thickness = 0.3f;
		engine.addObject(makeBounds(0, -relative.y - thickness / 2, 2 * relative.x - thickness, thickness));
		engine.addObject(makeBounds(0,  relative.y + thickness / 2, 2 * relative.x - thickness, thickness));
		engine.addObject(makeBounds( relative.x + thickness / 2, 0, thickness, 2 * relative.y - thickness));
		engine.addObject(makeBounds(-relative.x - thickness / 2, 0, thickness, 2 * relative.y - thickness));

		System.out.println("Done");
	}
	@Override
	public void unload()
	{
		super.unload();
	}

	@Override
	public void update(DeltaTime dt) throws Exception
	{
		engine.compute(dt);
		updateScene();
	}

	@Override
	public void render()
	{
		for (int i = 0; i < Math.min(balls.length, colors.length); ++i)
			if (balls[i] != null)
				renderer.drawObject(balls[i], colors[i]);

		Vector relative = renderer.getRelativeSize();
		float thickness = 0.3f;
		renderer.drawObject(makeBounds(0, -relative.y - thickness / 2, 2 * relative.x, thickness), Debug.color);
		renderer.drawObject(makeBounds(0,  relative.y + thickness / 2, 2 * relative.x, thickness), Debug.color);
		renderer.drawObject(makeBounds( relative.x + thickness / 2, 0, thickness, 2 * relative.y), Debug.color);
		renderer.drawObject(makeBounds(-relative.x - thickness / 2, 0, thickness, 2 * relative.y), Debug.color);
	}

	static private PhysicsObject makeBounds(double x, double y, double width, double height)
	{
		PhysicsObject object = new PhysicsObject(new AAB(width, height));
		object.position.set(x, y);
		object.movable = false;
		return object;
	}

	PhysicsObject[] balls;
	Color[] colors;
}
