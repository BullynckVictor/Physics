package com.physics;

public class AAB extends Collider
{
	public AAB()
	{
		super(ColliderType.AAB);
		width = 0;
		height = 0;
	}
	public AAB(double side)
	{
		super(ColliderType.AAB);
		width = side;
		height = side;
	}
	public AAB(double width, double height)
	{
		super(ColliderType.AAB);
		this.width = width;
		this.height = height;
	}

	@Override
	public double area()
	{
		return width * height;
	}

	public double width;
	public double height;
}
