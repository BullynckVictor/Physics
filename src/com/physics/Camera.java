package com.physics;

public class Camera
{
	public Camera()
	{
		position = new Vector();
	}

	public Camera(Vector position)
	{
		this.position = position;
	}
	public Camera(Vector position, float rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	public Camera(Vector position, float rotation, float zoom)
	{
		this.position = position;
		this.rotation = rotation;
		this.zoom = zoom;
	}

	public void moveRelativeX(float x)
	{
		if (shouldRotate())
		{
			float cos = (float) Math.cos(-rotation);
			float sin = (float) Math.sin(-rotation);
			position.x += x * cos;
			position.y += x * sin;
		}
		else
		{
			position.x += x;
		}
	}
	public void moveRelativeY(float y)
	{
		if (shouldRotate())
		{
			float cos = (float) Math.cos(-rotation);
			float sin = (float) Math.sin(-rotation);
			position.x += -y * sin;
			position.y += y * cos;
		}
		else
		{
			position.y += y;
		}
	}
	public void moveRelative(float x, float y)
	{
		if (shouldRotate())
		{
			float cos = (float)Math.cos(rotation);
			float sin = (float)Math.sin(rotation);
			position.x += x * cos - y * sin;
			position.y += x * sin + y * cos;
		}
		else
		{
			position.x += x;
			position.y += y;
		}
	}
	public void moveRelative(Vector xy)
	{
		moveRelative(xy.x, xy.y);
	}

	public boolean shouldRotate()
	{
		return rotation != 0f;
	}

	public final Vector position;
	public float rotation = 0;
	public float zoom = 1;
}
