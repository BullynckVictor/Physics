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
		//translate
		float tx = x - camera.x;
		float ty = y - camera.y;

		//scale
		tx *= camera.zoom;
		ty *= camera.zoom;

		//rotate
		float cos = (float)Math.cos(-camera.rotation);
		float sin = (float)Math.sin(-camera.rotation);
		float rx = tx * cos - ty * sin;
		float ry = tx * sin + ty * cos;

		//map
		ry *= -1;
		Dimension size = getSize();
		float min = (float)Math.min(size.width, size.height) / 2;
		rx *= min;
		ry *= min;
		rx += (float)size.width / 2;
		ry += (float)size.height / 2;

		return new Point((int)rx, (int)ry);
	}

	public void drawCircle(float x, float y, float radius, Color color)
	{
		Point p = transform(x, y);
		getGraphics().setColor(color);
		Dimension size = getSize();
		float min = (float)Math.min(size.width, size.height) / 2;
		int r = (int)(radius * min * camera.zoom);
		getGraphics().drawOval(p.x - r, p.y - r, r * 2, r * 2);
	}
	public void fillCircle(float x, float y, float radius, Color color)
	{
		Point p = transform(x, y);
		getGraphics().setColor(color);
		Dimension size = getSize();
		float min = (float)Math.min(size.width, size.height) / 2;
		int r = (int)(radius * min * camera.zoom);
		getGraphics().fillOval(p.x - r, p.y - r, r * 2, r * 2);
	}
	public void drawRectangle(float x, float y, float width, float height, Color color)
	{
		Point p1 = transform(x - width / 2f, y + height / 2f);
		Point p2 = transform(x + width / 2f, y + height / 2f);
		Point p3 = transform(x + width / 2f, y - height / 2f);
		Point p4 = transform(x - width / 2f, y - height / 2f);
		getGraphics().setColor(color);
		getGraphics().drawPolygon( new Polygon(
				new int[]{ p1.x, p2.x, p3.x, p4.x },
				new int[]{ p1.y, p2.y, p3.y, p4.y },
				4
		));
	}
	public void fillRectangle(float x, float y, float width, float height, Color color)
	{
		Point p1 = transform(x - width / 2f, y + height / 2f);
		Point p2 = transform(x + width / 2f, y + height / 2f);
		Point p3 = transform(x + width / 2f, y - height / 2f);
		Point p4 = transform(x - width / 2f, y - height / 2f);
		getGraphics().setColor(color);
		getGraphics().fillPolygon( new Polygon(
				new int[]{ p1.x, p2.x, p3.x, p4.x },
				new int[]{ p1.y, p2.y, p3.y, p4.y },
				4
		));
	}
	void drawLine(float x1, float y1, float x2, float y2, Color color)
	{
		Point p1 = transform(x1, y1);
		Point p2 = transform(x2, y2);
		getGraphics().setColor(color);
		getGraphics().drawLine(p1.x, p1.y, p2.x, p2.y);
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
