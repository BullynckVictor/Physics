package com.physics;

import com.physics.util.DeltaTime;
import com.physics.Vector;


import java.util.ArrayList;

public class Engine
{
	public Engine()
	{
		objects = new ArrayList<>();
	}

	public void tick(DeltaTime dt)
	{
		for (PhysicsObject object : objects)
		{
		float yGravity = object.mass*9.81f;
		Vector Gravity = new Vector(0f,yGravity);
		Vector ResultantForce = new Vector();
		ResultantForce.add(Gravity);
		object.acceleration = ResultantForce.div(object.mass);
		object.velocity = object.acceleration.mul(dt.seconds());
		object.position =object.velocity.mul(dt.seconds());



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

}
