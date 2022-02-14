package com.physics;

import com.physics.util.DeltaTime;
import com.physics.util.Timer;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Engine
{
	public Engine()
	{
		objects = new ArrayList<>();
		dt = new DeltaTime(16, ChronoUnit.MILLIS);
		delta = new DeltaTime();
	}
	public Engine(ResultantForceCalculator calculator)
	{
		objects = new ArrayList<>();
		forceCalculator = calculator;
	}

	public void compute(DeltaTime dt)
	{
		delta.add(dt);
		int n = (int)(delta.seconds() / this.dt.seconds());
		for (int i = 0; i < n; ++i) {
			tick();
			delta.sub(this.dt);
		}
	}

	public void tick()
	{
		for (int index = 0; index < objects.size(); ++index)
		{
			PhysicsObject object = objects.get(index);

			if (forceCalculator != null) {
				forceCalculator.calculateResultantForce(object, objects, index);
				//System.out.println(true);
			}
			System.out.println(object.force);

			//acceleration
			object.acceleration = Vector.div(object.force, object.mass);

			//position
			object.position = Vector.add(object.position, Vector.add(Vector.mul(object.acceleration, dt.seconds()*dt.seconds()/2), Vector.mul(object.velocity, dt.seconds())));

			//velocity
			object.velocity = Vector.add(object.velocity, Vector.mul(object.acceleration, dt.seconds()));

			object.force.x = 0;
			object.force.y = 0;

		}

		for (int i = 0; i < objects.size(); ++i) {
			PhysicsObject objectA = objects.get(i);
			for (int j = i + 1; j < objects.size(); ++j) {
				PhysicsObject objectB = objects.get(j);

				Debug.drawVector(Vector.sub(objectB.position, objectA.position), objectA.position);

				if (CollisionHandler.collide(objectA, objectB)) {
					CollisionHandler.resolve(objectA, objectB);
					Vector normal1 = CollisionHandler.normal(objectA, objectB);
					Vector normal2 = Vector.mul(normal1, -1);
					Vector r1 = Vector.sub(objectA.velocity, Vector.mul(normal1, 2*objectA.velocity.dot(normal1)));
					Vector r2 = Vector.sub(objectB.velocity, Vector.mul(normal2, 2*objectB.velocity.dot(normal2)));
					r1.normalise();
					r2.normalise();

					float v1 = (objectA.mass - objectB.mass) / (objectA.mass + objectB.mass) * objectA.velocity.length() + (2* objectB.mass) / (objectA.mass + objectB.mass) * objectB.velocity.length();
					float v2 = (2*objectA.mass) / (objectA.mass + objectB.mass) * objectA.velocity.length() - (objectA.mass - objectB.mass) / (objectA.mass + objectB.mass) * objectB.velocity.length();

					objectA.velocity = Vector.mul(r1, v1);
					objectB.velocity = Vector.mul(r2, v2);
				}
			}
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

	public DeltaTime dt;
	private DeltaTime delta;

	private final ArrayList<PhysicsObject> objects;
	ResultantForceCalculator forceCalculator;
}
