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
		for (PhysicsObject object : objects) {
			Vector ResultantForce = new Vector();

			//gravity
			float yGravity = object.mass*9.81f;
			Vector Gravity = new Vector(0f,-yGravity);

			//total force
			ResultantForce.add(Gravity);
			ResultantForce.add(object.force);


			//acceleration
			object.acceleration = Vector.div(ResultantForce, object.mass);

			//position
			object.position = Vector.add(object.position, Vector.add(Vector.mul(object.acceleration, dt.seconds()*dt.seconds()/2), Vector.mul(object.velocity, dt.seconds())));

			//velocity
			object.velocity = Vector.add(object.velocity, Vector.mul(object.acceleration, dt.seconds()));

			object.force = Vector.sub(object.force,object.force); //waarschijnlijk is er een beter manier ma kheb geen zin om meet na te denken
			ResultantForce = null;
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
