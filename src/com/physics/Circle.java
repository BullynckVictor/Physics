package com.physics;

public class Circle extends Collider
{
	public Circle()
	{
		super(ColliderType.CIRCLE);
		radius = 0;
	}
	public Circle(float radius)
	{
		super(ColliderType.CIRCLE);
		this.radius = radius;
	}

	@Override
	public float area()
	{
		return radius * radius * (float)Math.PI;
	}

	public float radius;
}
