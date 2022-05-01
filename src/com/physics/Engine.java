package com.physics;

import com.physics.util.DeltaTime;

import javax.xml.datatype.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
		if (this.dt.seconds() == 0)
		{
			this.dt = dt;
			tick();
			this.dt = new DeltaTime(0, ChronoUnit.SECONDS);
		}
		else
		{
			delta.add(dt);
			int n = (int) (delta.seconds() / this.dt.seconds());
			for (int i = 0; i < n; ++i)
			{
				tick();
				delta.sub(this.dt);
			}
		}
	}

	public void tick()
	{
		double energy = 0;


		for (int index = 0; index < objects.size(); ++index)
		{
			PhysicsObject object = objects.get(index);

			if (forceCalculator != null)
				forceCalculator.calculateResultantForce(object, objects, index);

			energy += object.mass*9.81*(object.position.y+3.5) + object.mass*object.velocity.lengthSQ()/2;

			if (object.movable)
			{
				//acceleration
				object.acceleration = Vector.div(object.force, object.mass);

				//position

				object.position = Vector.add(object.position, Vector.add(Vector.mul(object.acceleration, dt.seconds() * dt.seconds() / 2), Vector.mul(object.velocity, dt.seconds())));

				//velocity
				object.velocity = Vector.add(object.velocity, Vector.mul(object.acceleration, dt.seconds()));


				//object.velocity = Vector.mul(object.acceleration, dt.seconds());
				//object.position = Vector.mul(object.velocity, dt.seconds());
			}
			else
			{
				object.acceleration.set(0);
				object.velocity.set(0);
			}
			object.force.set(0);
		}
		System.out.println(energy);

		for (int i = 0; i < objects.size(); ++i) {
			PhysicsObject objectA = objects.get(i);
			for (int j = i + 1; j < objects.size(); ++j) {
				PhysicsObject objectB = objects.get(j);

				if (CollisionHandler.collide(objectA, objectB)) {
					CollisionHandler.resolve(objectA, objectB);
					Vector normal1 = CollisionHandler.normal(objectA, objectB);
					Vector r1 = Vector.sub(objectA.velocity, Vector.mul(normal1,2*objectA.velocity.dot(normal1)));
					Vector r2 = Vector.sub(objectB.velocity, Vector.mul(normal1, 2*objectB.velocity.dot(normal1)));
					r1.normalise();
					r2.normalise();

					System.out.println("botsing");

					Vector v1 = Vector.add(Vector.mul(objectA.velocity, (objectA.mass - objectB.mass) / (objectA.mass + objectB.mass)),Vector.mul(objectB.velocity, (2* objectB.mass) / (objectA.mass + objectB.mass)));
					Vector v2 = Vector.sub(Vector.mul(objectA.velocity, (2*objectA.mass) / (objectA.mass + objectB.mass)),Vector.mul(objectB.velocity, (objectB.mass - objectA.mass) / (objectA.mass + objectB.mass)));
					objectA.velocity = Vector.mul(r1, v1.length()*0.99);
					objectB.velocity = Vector.mul(r2, v2.length()*0.99);

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
