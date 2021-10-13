package com.physics.util;

public class Time
{
	public Time()
	{
		time = now();
	}
	public Time(long time)
	{
		this.time = time;
	}
	public Time(Time rhs)
	{
		time = rhs.time;
	}

	public long millis()
	{
		return millisLong();
	}
	public long millisLong()
	{
		return time;
	}
	public float millisFloat()
	{
		return (float)time;
	}
	public double millisDouble()
	{
		return (double)time;
	}

	public float seconds()
	{
		return secondsFloat();
	}
	public long secondsLong()
	{
		return millisLong() / 1000;
	}
	public float secondsFloat()
	{
		return millisFloat() / 1000.0f;
	}
	public double secondsDouble()
	{
		return millisDouble() / 1000.0;
	}

	public static Time duration(Time a, Time b)
	{
		return new Time(Math.abs(a.millis() - b.millis()));
	}

	public void setNow()
	{
		time = now();
	}
	private static long now()
	{
		return System.currentTimeMillis();
	}

	private long time;
}
