package com.physics;

public class Circle extends Collider
{
	public Circle()
	{
		super(ColliderType.CIRCLE);
		radius = 0;
	}
	public Circle(double radius)
	{
		super(ColliderType.CIRCLE);
		this.radius = radius;
	}

	@Override
	public double area()
	{
		return radius * radius * Math.PI;
	}

	public double radius;
}
