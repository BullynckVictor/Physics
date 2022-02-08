package com.physics;

import com.physics.util.DeltaTime;

import java.util.ArrayList;

public class Engine
{
	public Engine()
	{
		objects = new ArrayList<>();
	}
	public Engine(ResultantForceCalculator calculator)
	{
		objects = new ArrayList<>();
		forceCalculator = calculator;
	}

	public void tick(DeltaTime dt)
	{
		for (int index = 0; index < objects.size(); index++)
		{
			PhysicsObject object = objects.get(index);

			if (forceCalculator != null)
				forceCalculator.calculateResultantForce(object, objects, index);

			//acceleration
			object.acceleration = Vector.div(object.force, object.mass);

			//position
			object.position = Vector.add(object.position, Vector.add(Vector.mul(object.acceleration, dt.seconds()*dt.seconds()/2), Vector.mul(object.velocity, dt.seconds())));

			//velocity
			object.velocity = Vector.add(object.velocity, Vector.mul(object.acceleration, dt.seconds()));

			object.force.x = 0;
			object.force.y = 0;
		}
	}

	public void addObject(PhysicsObject object)
	{
		objects.add(object);
	}
	public void removeObject(PhysicsObject object)
	{
		objects.remove(object);
	}
	public void clear()
	{
		objects.clear();
	}


	private final ArrayList<PhysicsObject> objects;
	ResultantForceCalculator forceCalculator;
}
