package com.physics.util;

public class Timer
{
	public Timer()
	{
		reset();
	}

	void reset()
	{
		lastTime.setNow();
	}

	public Time mark()
	{
		Time temp = new Time(lastTime);
		reset();
		return Time.duration(lastTime, temp);
	}
	public Time peek()
	{
		return  Time.duration(new Time(), lastTime);
	}

	private static long now()
	{
		return System.currentTimeMillis();
	}

	private Time lastTime = new Time();
}
