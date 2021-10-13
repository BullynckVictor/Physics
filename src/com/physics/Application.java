package com.physics;
import java.awt.*;

import com.physics.util.Timer;
import com.physics.util.Time;

public class Application
{
	public Application()
	{
		timer = new Timer();
	}

	protected void update(Time dt) {}
	protected void render() {}

	public void run()
	{
		int i = 0;
		while(running)
		{
			update(timer.mark());
			render();
			if (i == 5)
				running = false;
			++i;
		}
	}

	private Timer timer;
	protected boolean running = true;
}