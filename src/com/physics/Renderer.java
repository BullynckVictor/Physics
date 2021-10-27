package com.physics;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class Renderer
{
	Renderer(String title, int width, int height, boolean resize)
	{
		camera = new Camera();
		canvas = new Canvas();
		Dimension size = new Dimension(width, height);
		canvas.setPreferredSize(size);

		frame = new JFrame(title);
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(resize);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

		frame.addWindowListener(
				new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						opened = false;
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSED));
					}
				}
		);

		background = Color.WHITE;
	}

	public Point transform(float x, float y)
	{
		float rx = x;
		float ry = y;

		//scale
		rx *= camera.zoom;
		ry *= camera.zoom;

		//rotate
		float cos = (float)Math.cos(-camera.rotation);
		float sin = (float)Math.sin(-camera.rotation);
		rx = rx * cos - ry * sin;
		ry = rx * sin + ry * cos;

		//translate
		ry *= -1;
		rx -= camera.x;
		ry -= camera.y;

		//map
		Dimension size = getSize();
		float min = (float)Math.min(size.width, size.height) / 2;
		rx *= min;
		ry *= min;
		rx += (float)size.width / 2;
		ry += (float)size.height / 2;

		return new Point((int)rx, (int)ry);
	}

	public void clear()
	{
		graphics.setBackground(background);
		graphics.clearRect(0, 0, getSize().width, getSize().height);
	}
	public void present() { bufferStrategy.show(); }

	public void addKeyboard(Keyboard kbd) { frame.addKeyListener(kbd); }
	public void addMouse(Mouse mouse)
	{
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		canvas.addMouseWheelListener(mouse);
	}
	public Mouse createMouse()
	{
		java.awt.Point jp = canvas.getMousePosition();
		Mouse mouse;
		if (jp != null) {
			Point p = new Point(jp.x, jp.y);
			mouse = new Mouse(p, getSize());
		}
		else {
			mouse = new Mouse();
		}
		addMouse(mouse);
		return mouse;
	}
	public JFrame getWindow() { return frame; }

	public boolean open() { return opened; }
	public void close() { frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); }

	public Graphics2D getGraphics() { return graphics; }

	public Dimension getSize() { return canvas.getSize(); }

	public void dispose() { frame.dispose(); }

	public Color background;
	public Camera camera;
	private final JFrame frame;
	private final Canvas canvas;
	private final BufferStrategy bufferStrategy;
	private final Graphics2D graphics;
	private boolean opened = true;
}
