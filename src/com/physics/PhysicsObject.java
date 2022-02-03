package com.physics;

public class PhysicsObject
{
	public PhysicsObject()
	{
		position = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		force = new Vector();
		mass = 0f;
	}
	public PhysicsObject(float mass)
	{
		position = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		force = new Vector();
		this.mass = mass;
	}

	public float mass;
	public Vector position;
	public Vector velocity;
	public Vector acceleration;
	public Vector force;
}
