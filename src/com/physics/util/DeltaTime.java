package com.physics.util;
import java.time.Duration;

public class DeltaTime {

	public DeltaTime(Duration duration)
	{
		this.duration = duration;
	}

	public float secondsLong()
	{
		return duration.toSeconds();
	}
	public float secondsFloat()
	{
		return (float)nanosLong() / 1000000000.0f;
	}
	public double secondsDouble()
	{
		return (double)nanosLong() / 1000000000.0d;
	}
	public float seconds()
	{
		return secondsFloat();
	}

	public long nanosLong()
	{
		return duration.toNanos();
	}
	public float nanosFloat()
	{
		return (float)nanosLong();
	}
	public double nanosDouble()
	{
		return (double)nanosLong();
	}
	public long nanos()
	{
		return nanosLong();
	}

	private Duration duration;
}
