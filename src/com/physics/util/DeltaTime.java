package com.physics.util;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class DeltaTime {

	public DeltaTime()
	{
		this.duration = Duration.of(0, ChronoUnit.SECONDS);
	}
	public DeltaTime(Duration duration)
	{
		this.duration = duration;
	}
	public DeltaTime(long duration, TemporalUnit unit)
	{
		this.duration = Duration.of(duration, unit);
	}

	public void set(long duration, TemporalUnit unit)
	{
		this.duration = Duration.of(duration, unit);
	}

	public long secondsLong()
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
