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
	public Camera(Vector position, double rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	public Camera(Vector position, double rotation, double zoom)
	{
		this.position = position;
		this.rotation = rotation;
		this.zoom = zoom;
	}

	public void moveRelativeX(double x)
	{
		if (shouldRotate())
		{
			double cos = Math.cos(-rotation);
			double sin = Math.sin(-rotation);
			position.x += x * cos;
			position.y += x * sin;
		}
		else
		{
			position.x += x;
		}
	}
	public void moveRelativeY(double y)
	{
		if (shouldRotate())
		{
			double cos = Math.cos(-rotation);
			double sin = Math.sin(-rotation);
			position.x += -y * sin;
			position.y += y * cos;
		}
		else
		{
			position.y += y;
		}
	}
	public void moveRelative(double x, double y)
	{
		if (shouldRotate())
		{
			double cos = Math.cos(rotation);
			double sin = Math.sin(rotation);
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
	public double rotation = 0;
	public double zoom = 1;
}
