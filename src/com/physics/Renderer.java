package com.physics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Window;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Renderer extends JFrame
{
	public Renderer(String title, int width, int height)
	{
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));

		Window w = this;

		add(panel);
		pack();
		setTitle(title);
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(
				new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						opened = false;
						dispatchEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSED));
					}
				}
		);
	}

	boolean open()
	{
		return opened;
	}
	void close()
	{
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	Dimension getPanelSize()
	{
		return panel.getSize();
	}

	public Graphics2D graphics()
	{
		return (Graphics2D)panel.getGraphics();
	}

	private final JPanel panel;
	private boolean opened = true;
}
