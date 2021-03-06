package com.physics;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.util.concurrent.locks.ReentrantLock;

public class Renderer
{
	Renderer(String title, int width, int height, boolean resize)
	{
		camera = new Camera();
		canvas = new Canvas();
		Dimension size = new Dimension(width, height);
		canvas.setSize(size);

		frame = new JFrame(title);
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(resize);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		graphics = (Graphics2D)bufferStrategy.getDrawGraphics();

		frame.addWindowListener(
				new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						opened = false;
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSED));
					}
				}
		);
		canvas.addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				onResize(e.getComponent().getSize());
			}
		});

		background = Color.WHITE;

		baseTransform = graphics.getTransform();
		min = Math.min(size.width, size.height) / 2.0f;
		middle = new Point(size.width / 2, size.height / 2);
	}

	public void setSize(int width, int height)
	{
		frame.setSize(width, height);
		canvas.setSize(width, height);
	}

	private void onResize(Dimension size)
	{
		mutex.lock();

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		graphics = (Graphics2D)bufferStrategy.getDrawGraphics();

		baseTransform = graphics.getTransform();
		min = Math.min(size.width, size.height) / 2.0f;
		middle.x = size.width / 2;
		middle.y = size.height / 2;

		mutex.unlock();
	}

	public void drawCircle(double x, double y, double radius, Color color, Transform transform)
	{
		setTransform(transform);
		getGraphics().setColor(color);
		double r = radius * min;
		double rx = x * min - r;
		double ry = y * min - r;
		graphics.drawOval((int)rx, (int)ry, (int)r * 2, (int)r * 2);
	}
	public void drawCircle(double x, double y, double radius, Color color)
	{
		drawCircle(x, y, radius, color, null);
	}
	public void drawCircle(Vector pos, double radius, Color color)
	{
		drawCircle(pos.x, pos.y, radius, color);
	}
	public void drawCircle(Vector pos, double radius, Color color, Transform transform) { drawCircle(pos.x, pos.y, radius, color, transform); }

	public void fillCircle(double x, double y, double radius, Color color, Transform transform)
	{
		setTransform(transform);
		getGraphics().setColor(color);
		double r = radius * min;
		double rx = x * min - r;
		double ry = y * min - r;
		graphics.fillOval((int)rx, (int)ry, (int)r * 2, (int)r * 2);
	}
	public void fillCircle(double x, double y, double radius, Color color)
	{
		fillCircle(x, y, radius, color, null);
	}
	public void fillCircle(Vector pos, double radius, Color color)
	{
		fillCircle(pos.x, pos.y, radius, color);
	}
	public void fillCircle(Vector pos, double radius, Color color, Transform transform) { fillCircle(pos.x, pos.y, radius, color, transform); }

	public void drawRectangle(double x, double y, double width, double height, Color color, Transform transform)
	{
		setTransform(transform);
		getGraphics().setColor(color);
		double rx = x * min - width * min / 2;
		double ry = y * min - height * min / 2;
		graphics.drawRect((int)rx, (int)ry, (int)(width * min), (int)(height * min));
	}
	public void drawRectangle(double x, double y, double width, double height, Color color)
	{
		drawRectangle(x, y, width, height, color, null);
	}
	public void drawRectangle(Vector pos, double width, double height, Color color, Transform transform)
	{
		drawRectangle(pos.x, pos.y, width, height, color, transform);
	}
	public void drawRectangle(Vector pos, double width, double height, Color color)
	{
		drawRectangle(pos.x, pos.y, width, height, color, null);
	}
	public void drawRectangle(Vector pos, Vector size, Color color, Transform transform)
	{
		drawRectangle(pos.x, pos.y, size.x, size.y, color, transform);
	}
	public void drawRectangle(Vector pos, Vector size, Color color)
	{
		drawRectangle(pos.x, pos.y, size.x, size.y, color, null);
	}

	public void fillRectangle(double x, double y, double width, double height, Color color, Transform transform)
	{
		setTransform(transform);
		getGraphics().setColor(color);
		double rx = x * min - width * min / 2;
		double ry = y * min - height * min / 2;
		graphics.fillRect((int)rx, (int)ry, (int)(width * min), (int)(height * min));
	}
	public void fillRectangle(double x, double y, double width, double height, Color color)
	{
		fillRectangle(x, y, width, height, color, null);
	}
	public void fillRectangle(Vector pos, double width, double height, Color color, Transform transform)
	{
		fillRectangle(pos.x, pos.y, width, height, color, transform);
	}
	public void fillRectangle(Vector pos, double width, double height, Color color)
	{
		fillRectangle(pos.x, pos.y, width, height, color, null);
	}
	public void fillRectangle(Vector pos, Vector size, Color color, Transform transform)
	{
		fillRectangle(pos.x, pos.y, size.x, size.y, color, transform);
	}
	public void fillRectangle(Vector pos, Vector size, Color color)
	{
		fillRectangle(pos.x, pos.y, size.x, size.y, color, null);
	}

	public void drawLine(double x1, double y1, double x2, double y2, Color color, Transform transform)
	{
		setTransform(transform);
		getGraphics().setColor(color);
		getGraphics().drawLine((int)(x1 * min), (int)(y1 * min), (int)(x2 * min), (int)(y2 * min));
	}
	public void drawLine(double x1, double y1, double x2, double y2, Color color)
	{
		drawLine(x1, y1, x2, y2, color, null);
	}
	public void drawLine(Vector p1, Vector p2,  Color color, Transform transform)
	{
		drawLine(p1.x, p1.y, p2.x, p2.y, color, transform);
	}
	public void drawLine(Vector p1, double x2, double y2, Color color)
	{
		drawLine(p1.x, p1.y, x2, y2, color);
	}
	public void drawLine(Vector p1, double x2, double y2, Color color, Transform transform)
	{
		drawLine(p1.x, p1.y, x2, y2, color, transform);
	}
	public void drawLine(Vector p1, Vector p2,  Color color)
	{
		drawLine(p1.x, p1.y, p2.x, p2.y, color);
	}

	public void drawString(String string, double x, double y, Color color, Transform transform)
	{
		setTransform(transform);
		getGraphics().scale(1, -1);
		getGraphics().setColor(color);
		getGraphics().drawString(string, (int)(x * min), (int)(y * min));
	}
	public void drawString(String string, double x, double y, Color color)
	{
		drawString(string, x, y, color, null);
	}
	public void drawString(String string, Vector p, Color color, Transform transform)
	{
		drawString(string, p.x, p.y, color, transform);
	}
	public void drawString(String string, Vector p, Color color)
	{
		drawString(string, p, color, null);
	}
	public void drawStringUI(String string, double x, double y, Color color)
	{
		getGraphics().setTransform(baseTransform);
		getGraphics().translate(middle.x, middle.y);
		getGraphics().setColor(color);
		getGraphics().drawString(string, (int)(x * min), (int)(-y * min));
	}
	public void drawStringUI(String string, Vector p, Color color)
	{
		drawStringUI(string, p.x, p.y, color);
	}
	public void setFont(String name, int style, int size)
	{
		graphics.setFont(new Font(name, style, size));
	}
	public void setFont(String name)
	{
		graphics.setFont(new Font(name, Font.PLAIN, 12));
	}

	public void drawObject(PhysicsObject object, Color color)
	{
		switch (object.collider.getType()) {
			case CIRCLE -> {
				Circle circle = (Circle) (object.collider);
				fillCircle(object.position, circle.radius, color);
				drawCircle(object.position, circle.radius, Color.BLACK);
			}
			case AAB -> {
				AAB box = (AAB) (object.collider);
				fillRectangle(object.position, box.width, box.height, color);
				drawRectangle(object.position, box.width, box.height, Color.BLACK);
			}
		}
	}

	public void setTransform()
	{
		graphics.setTransform(baseTransform);
		graphics.scale(1, -1);
		graphics.translate(middle.x, -middle.y);
		graphics.scale(camera.zoom, camera.zoom);
		graphics.rotate(-camera.rotation, -camera.position.x, -camera.position.y);
		graphics.translate(-camera.position.x * min, -camera.position.y * min);
	}
	public void setTransform(Transform transform)
	{
		setTransform();
		if (transform != null)
		{
			graphics.translate(transform.position.x * min, transform.position.y * min);
			graphics.rotate(transform.rotation);
			graphics.scale(transform.scale.x, transform.scale.y);
		}
	}

	public void clear()
	{
		graphics.setBackground(background);
		graphics.setTransform(baseTransform);
		graphics.clearRect(0, 0, getSize().width, getSize().height);
		setTransform();
	}
	public synchronized void present()
	{
		mutex.lock();
		bufferStrategy.show();
		mutex.unlock();
	}

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
	public Vector getRelativeSize()
	{
		Dimension s = getSize();
		Vector size = new Vector(s.width, s.height);
		size.div(min * 2);
		return size;
	}

	public void dispose() { frame.dispose(); }

	public Color background;
	public Camera camera;
	private final JFrame frame;
	private final Canvas canvas;
	private BufferStrategy bufferStrategy;
	private Graphics2D graphics;
	private boolean opened = true;
	private AffineTransform baseTransform;
	private double min;
	private Point middle;
	private ReentrantLock mutex = new ReentrantLock();
}
