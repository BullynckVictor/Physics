package com.physics;

import com.physics.util.DeltaTime;
import com.physics.util.OptionsReader;
import com.physics.util.Timer;

import java.awt.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;

public class MainScene extends Scene {
	MainScene(Renderer renderer) {
		super(renderer);
		objects = new ArrayList<>();
		colors = new ArrayList<>();
		Debug.setRenderer(renderer);
		Debug.setColor(Color.GREEN);
		timer = new Timer();
		fps = 0;
		frames = 0;

		OptionsReader options = new OptionsReader("Developer.txt");
		options.addDefault("engine update delta", "16");
		try {
			engine.dt.set(Integer.parseInt(options.getValue("engine update delta")), ChronoUnit.MILLIS);
		} catch (NumberFormatException e) {
			engine.dt.set(16, ChronoUnit.MILLIS);
		}
	}

	@Override
	public void load() {
		objects.add(new PhysicsObject(new Circle(.1f), .2f));
		objects.add(new PhysicsObject(new Circle(.2f), .2f));
		objects.add(new PhysicsObject(new Circle(.1f), .2f));
		objects.add(new PhysicsObject(new Circle(.3f), .2f));
		//objects.add(new PhysicsObject(new AAB(.1f, 0.3f), .2f));
		//objects.add(new PhysicsObject(new AAB(.25f), .25f));
		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.YELLOW);
		colors.add(Color.RED);

		engine.forceCalculator = new UniversalGravityCalculator();

		for (PhysicsObject object : objects)
		{
			object.position = Vector.random();
			//object.velocity.sub(object.position);
			//object.velocity.normalise();
			//object.velocity.div(3);
			engine.addObject(object);
		}

		timer.reset();
		frames = 0;
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
		engine.compute(dt);
		controlCamera(dt.seconds());

		++frames;
		int seconds = (int)timer.peek().toSeconds();
		if (seconds > 0)
		{
			fps = frames / seconds;
			frames = 0;
			timer.reset();
		}
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

		Vector relative = renderer.getRelativeSize();
		renderer.drawStringUI("FPS: " + fps, -relative.x + 0.05f, relative.y - 0.05f, Color.BLACK);
	}

	private final ArrayList<PhysicsObject> objects;
	private final ArrayList<Color> colors;
	private final Timer timer;
	private int fps;
	private int frames;
}