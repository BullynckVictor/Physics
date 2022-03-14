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
		float velocity = 1f;
		float radius = gravity.constant * earth.mass / (velocity * velocity);
		float theta = (float)(Math.random() * Math.PI * 2);
		satellite.position.set(radius * (float)Math.cos(theta), radius * (float)Math.sin(theta));
		satellite.velocity.set(velocity * (float)Math.cos(theta + Math.PI / 2), velocity * (float)Math.sin(theta + Math.PI / 2));

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
		controlCamera(dt.seconds());
		updateScene();
	}

	@Override
	public void render()
	{
		renderer.drawObject(earth, Color.GREEN);
		renderer.drawObject(satellite, Color.GRAY);

		Debug.color = Color.LIGHT_GRAY;
		Debug.drawVector(satellite.velocity, satellite.position);
		Debug.drawVector(satellite.acceleration, satellite.position);

		Vector relative = renderer.getRelativeSize();
		renderer.drawStringUI(Float.toString(satellite.velocity.length()), -relative.x + 0.05f, relative.y - 0.11f, Color.BLACK);
		renderer.drawStringUI(Float.toString(satellite.position.length()), -relative.x + 0.05f, relative.y - 0.17f, Color.BLACK);
	}

	private PhysicsObject earth;
	private PhysicsObject satellite;
}
