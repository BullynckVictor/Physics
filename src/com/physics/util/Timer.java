package com.physics.util;

import java.time.LocalDateTime;
import java.time.Duration;

public class Timer
{
	public Timer()
	{
		reset();
	}

	void reset()
	{
		lastTime = now();
	}

	public Duration mark()
	{
		LocalDateTime temp = LocalDateTime.from(lastTime);
		reset();
		return Duration.between(temp, lastTime);
	}
	public Duration peek()
	{
		return Duration.between(lastTime, now());
	}

	public static float toFloat(Duration duration)
	{
		return (float)duration.toNanos() * 1e-9f;
	}

	public static double toDouble(Duration duration)
	{
		return (double)duration.toNanos() * 1e-9;
	}

	private static LocalDateTime now()
	{
		return LocalDateTime.now();
	}

	private LocalDateTime lastTime = LocalDateTime.now();
}
