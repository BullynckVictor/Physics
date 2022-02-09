package com.physics;

public class AAB extends Collider
{
	public AAB()
	{
		super(ColliderType.AAB);
		width = 0;
		height = 0;
	}
	public AAB(float side)
	{
		super(ColliderType.AAB);
		width = side;
		height = side;
	}
	public AAB(float width, float height)
	{
		super(ColliderType.AAB);
		this.width = width;
		this.height = height;
	}

	public float width;
	public float height;
}
