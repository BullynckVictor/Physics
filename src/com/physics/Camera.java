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

	void moveRelativeX(float x)
	{
		if (shouldRotate())
		{
			float cos = (float) Math.cos(-rotation);
			float sin = (float) Math.sin(-rotation);
			this.x += x * cos;
			this.y += x * sin;
		}
		else
		{
			this.x += x;
			this.y += y;
		}
	}
	void moveRelativeY(float y)
	{
		if (shouldRotate())
		{
			float cos = (float) Math.cos(-rotation);
			float sin = (float) Math.sin(-rotation);
			this.x += -y * sin;
			this.y += y * cos;
		}
		else
		{
			this.x += x;
			this.y += y;
		}
	}
	void moveRelative(float x, float y)
	{
		if (shouldRotate())
		{
			float cos = (float)Math.cos(rotation);
			float sin = (float)Math.sin(rotation);
			this.x += x * cos - y * sin;
			this.y += x * sin + y * cos;
		}
		else
		{
			this.x += x;
			this.y += y;
		}
	}

	public boolean shouldRotate()
	{
		return rotation != 0f;
	}

	public float x = 0;
	public float y = 0;
	public float rotation = 0;
	public float zoom = 1;
}
