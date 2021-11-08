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
	public Transform(Vector position, float rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	public Transform(Vector position, float rotation, Vector scale)
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

	public static Transform rotation(float rotation) {
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
	public static Transform position(float x, float y)
	{
		Transform ret = new Transform();
		ret.position.x = x;
		ret.position.y = y;
		return ret;
	}
	public static Transform translation(float x, float y)
	{
		return position(x, y);
	}

	public static Transform scale(Vector scale)
	{
		return scale(scale.x, scale.y);
	}
	public static Transform scale(float x, float y)
	{
		Transform ret = new Transform();
		ret.scale.x = x;
		ret.scale.y = y;
		return ret;
	}

	public Vector position;
	public float rotation;
	public Vector scale;
}
