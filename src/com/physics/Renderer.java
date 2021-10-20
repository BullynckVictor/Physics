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

	public void clear()
	{
		graphics.setBackground(background);
		graphics.clearRect(0, 0, getSize().width, getSize().height);
	}
	public void present() { bufferStrategy.show(); }

	public void addKeyboard(Keyboard kbd) { frame.addKeyListener(kbd); }
	public void addMouse(Mouse mouse)
	{
		frame.addMouseListener(mouse);
	}
	public JFrame getWindow() { return frame; }

	public boolean open() { return opened; }
	public void close() { frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); }

	public Graphics2D getGraphics() { return graphics; }

	public Dimension getSize() { return canvas.getSize(); }

	public void dispose() { frame.dispose(); }

	public final Color background;
	private final JFrame frame;
	private final Canvas canvas;
	private final BufferStrategy bufferStrategy;
	private final Graphics2D graphics;
	private boolean opened = true;
}
