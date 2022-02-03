package com.physics;

import com.physics.util.DeltaTime;

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
