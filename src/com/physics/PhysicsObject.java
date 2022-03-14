package com.physics;

public class PhysicsObject
{
	public PhysicsObject(Collider collider)
	{
		position = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		force = new Vector();
		mass = collider.area();
		this.collider = collider;
	}
	public PhysicsObject(Collider collider, float density)
	{
		position = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		force = new Vector();
		this.mass = collider.area() * density;
		this.collider = collider;
	}

	public float mass;
	public Vector position;
	public Vector velocity;
	public Vector acceleration;
	public Vector force;
	public Collider collider;
	public boolean movable = true;
}
