package com.physics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener
{
	Mouse()
	{
		position = new Point();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			System.out.println("left pressed");
		else if (e.getButton() == MouseEvent.BUTTON2)
			System.out.println("right pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			System.out.println("left released");
		else if (e.getButton() == MouseEvent.BUTTON2)
			System.out.println("right released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		inWindow = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		inWindow = false;
	}

	public boolean inWindow()
	{
		return inWindow;
	}

	private final Point position;
	private boolean inWindow;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
}
