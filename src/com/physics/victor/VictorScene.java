package com.physics.victor;

import com.physics.Scene;
import com.physics.Vector;
import com.physics.util.DeltaTime;

import java.awt.*;

public class VictorScene extends Scene {

	public VictorScene()
	{
		pos = new Vector(.5f, .3f);
	}

	@Override
	public void update(DeltaTime dt)
	{
		if (keyboard.keyPressed('z'))
			pos.y += dt.seconds();
	}

	@Override
	public void render()
	{
		renderer.drawRectangle(1.1f, 1, .1f, .1f, Color.BLUE);
	}

	Vector pos;
}
