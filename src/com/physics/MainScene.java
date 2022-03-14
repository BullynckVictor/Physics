package com.physics;

import com.physics.util.DeltaTime;
import com.physics.util.OptionsReader;
import com.physics.util.Timer;

import java.awt.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;

public class MainScene extends Scene {
	MainScene(Renderer renderer, SceneHandler sceneHandler) {
		super(renderer, sceneHandler);
		objects = new ArrayList<>();
		colors = new ArrayList<>();
		Debug.setRenderer(renderer);
		Debug.setColor(Color.GREEN);
		timer = new Timer();
		fps = 0;
		frames = 0;
	}

	@Override
	public void load() {
		super.load();

		objects.add(new PhysicsObject(new Circle(.1f)));
		objects.add(new PhysicsObject(new Circle(.2f)));
		objects.add(new PhysicsObject(new Circle(.1f)));
		objects.add(new PhysicsObject(new Circle(.3f)));
		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.YELLOW);
		colors.add(Color.RED);

		engine.forceCalculator = new UniversalGravityCalculator(1);

		for (PhysicsObject object : objects)
		{
			object.position = Vector.random();
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
	public void update(DeltaTime dt) throws Exception
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

		updateScene();
	}

	@Override
	public void render()
	{
		for (int i = 0; i < objects.size(); ++i) {
			PhysicsObject object = objects.get(i);
			renderer.drawObject(object, colors.get(i));
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