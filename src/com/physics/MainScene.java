package com.physics;

import com.physics.util.DeltaTime;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class MainScene extends Scene {
	MainScene() {
	}

	@Override
	public void load() throws Exception {
		sprite = ImageIO.read(new File("assets/test.png"));
	}

	@Override
	public void update(DeltaTime dt)
	{
		float speed = 1.0f;
		float rotateSpeed = 1.0f;

		float dx = 0;
		float dy = 0;

		if (keyboard.keyPressed('z') || keyboard.keyPressed(KeyEvent.VK_UP))
			dy += speed * dt.seconds();
		if (keyboard.keyPressed('s') || keyboard.keyPressed(KeyEvent.VK_DOWN))
			dy -= speed * dt.seconds();
		if (keyboard.keyPressed('q') || keyboard.keyPressed(KeyEvent.VK_LEFT))
			dx -= speed * dt.seconds();
		if (keyboard.keyPressed('d') || keyboard.keyPressed(KeyEvent.VK_RIGHT))
			dx += speed * dt.seconds();
		if (keyboard.keyPressed('a'))
			renderer.camera.rotation += rotateSpeed * dt.seconds();
		if (keyboard.keyPressed('e'))
			renderer.camera.rotation -= rotateSpeed * dt.seconds();

		renderer.camera.moveRelative(dx, dy);

		float e = 0.5772156649f;
		renderer.camera.zoom *= Math.pow(e, (float)mouse.getScrollNormal());
	}

	@Override
	public void render()
	{
		final float pi = 3.14159265359f;
		renderer.getGraphics().setColor(Color.BLACK);

		renderer.background = Color.LIGHT_GRAY;
		renderer.fillRectangle(0, 0, 1f, 1f, Color.GREEN);
		renderer.drawRectangle(0, 0, 1f, 1f, Color.BLACK);
		renderer.fillCircle(0, 0, .5f, Color.LIGHT_GRAY);
		renderer.drawCircle(0, 0, .5f, Color.GRAY);
		renderer.drawLine(-.1f, 0, .1f, 0, Color.BLUE);
		renderer.drawLine(0, -.1f, 0, .1f, Color.BLUE);

		renderer.getGraphics().drawImage(sprite, 300, 200, null);

		renderer.getGraphics().setColor(Color.BLACK);
		renderer.getGraphics().drawString("camera", 2, 12);
		renderer.getGraphics().drawString("position: (" + renderer.camera.x + ", " + renderer.camera.y + ")", 14, 24);
		renderer.getGraphics().drawString("rotation: " + (int)(renderer.camera.rotation * 180f / pi) + "°", 14, 36);
		renderer.getGraphics().drawString("zoom: " + renderer.camera.zoom, 14, 48);
	}

	Image sprite;
}
