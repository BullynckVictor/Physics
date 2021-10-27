package com.physics;

public class Camera
{
	public Camera()
	{
	}

	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	public Camera(float x, float y, float rotation)
	{
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
	public Camera(float x, float y, float rotation, float zoom)
	{
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.zoom = zoom;
	}

	public float x = 0;
	public float y = 0;
	public float rotation = 0;
	public float zoom = 1;
}
