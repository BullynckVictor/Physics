package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;

public class EarthScene extends Scene {

	public EarthScene(Renderer renderer, SceneHandler sceneHandler)
	{
		super(renderer, sceneHandler);
		Debug.setRenderer(renderer);
		Debug.setColor(Color.LIGHT_GRAY);
	}

	@Override
	public void load() {
		super.load();
		UniversalGravityCalculator gravity = new UniversalGravityCalculator(1);
		engine.forceCalculator = gravity;

		earth = new PhysicsObject(new Circle(0.5f));
		satellite = new PhysicsObject(new Circle(0.125f));

		earth.movable = false;
		double velocity = 1f;
		double theta = Math.random() * Math.PI * 2;
		radius = gravity.constant * earth.mass / (velocity * velocity);
		satellite.position.set(radius * Math.cos(theta), radius * Math.sin(theta));
		satellite.velocity.set(velocity * Math.cos(theta + Math.PI / 2), velocity * Math.sin(theta + Math.PI / 2));

		engine.addObject(earth);
		engine.addObject(satellite);
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
		controlCamera(dt.secondsDouble());
		updateScene();
	}

	@Override
	public void render()
	{
		renderer.drawCircle(0, 0, radius, Debug.color);
		renderer.drawObject(earth, Color.GREEN);
		renderer.drawObject(satellite, Color.GRAY);

		Debug.color = Color.LIGHT_GRAY;
		Debug.drawVector(satellite.velocity, satellite.position);
		Debug.drawVector(satellite.acceleration, satellite.position);

		Vector relative = renderer.getRelativeSize();
		renderer.drawStringUI(Double.toString(satellite.velocity.length()), -relative.x + 0.05f, relative.y - 0.11f, Color.BLACK);
		renderer.drawStringUI(Double.toString(satellite.position.length()), -relative.x + 0.05f, relative.y - 0.17f, Color.BLACK);
	}

	private PhysicsObject earth;
	private PhysicsObject satellite;
	private double radius;
}
