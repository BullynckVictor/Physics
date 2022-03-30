package com.physics;

public class Transform
{
	public Transform()
	{
		position = new Vector();
		rotation = 0;
		scale = new Vector(1);
	}
	public Transform(Vector position)
	{
		this.position = position;
	}
	public Transform(Vector position, double rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	public Transform(Vector position, double rotation, Vector scale)
	{
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	public Transform(Transform transform)
	{
		position = transform.position;
		rotation = transform.rotation;
		scale = transform.scale;
	}

	public static Transform rotation(double rotation) {
		Transform ret = new Transform();
		ret.rotation = rotation;
		return ret;
	}

	public static Transform position(Vector position)
	{
		return translation(position);
	}
	public static Transform translation(Vector translation)
	{
		return translation(translation.x, translation.y);
	}
	public static Transform position(double x, double y)
	{
		Transform ret = new Transform();
		ret.position.x = x;
		ret.position.y = y;
		return ret;
	}
	public static Transform translation(double x, double y)
	{
		return position(x, y);
	}

	public static Transform scale(Vector scale)
	{
		return scale(scale.x, scale.y);
	}
	public static Transform scale(double x, double y)
	{
		Transform ret = new Transform();
		ret.scale.x = x;
		ret.scale.y = y;
		return ret;
	}

	public Vector position;
	public double rotation;
	public Vector scale;
}
