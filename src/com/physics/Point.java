package com.physics;

public class Point
{
	Point()
	{
		x = 0;
		y = 0;
	}
	Point(int value)
	{
		x = value;
		y = value;
	}
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	Point(Point p)
	{
		x = p.x;
		y = p.y;
	}

	public int x;
	public int y;
}
