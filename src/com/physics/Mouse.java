package com.physics;

import java.awt.Dimension;
import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener
{
	Mouse()
	{
		position = new Point();
	}
	Mouse(Point position, Dimension size)
	{
		this.position = new Point(position);
		inWindow =
				position.x >= 0 &&
				position.x < size.width &&
				position.y >= 0 &&
				position.y < size.height;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			System.out.println("left pressed");
		else if (e.getButton() == MouseEvent.BUTTON2)
			System.out.println("middle pressed");
		else if (e.getButton() == MouseEvent.BUTTON3)
			System.out.println("right pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			System.out.println("left released");
		else if (e.getButton() == MouseEvent.BUTTON2)
			System.out.println("middle released");
		else if (e.getButton() == MouseEvent.BUTTON3)
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

	@Override
	public void mouseDragged(MouseEvent e) {
		position.x = e.getX();
		position.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		position.x = e.getX();
		position.y = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
			wheelDelta += e.getUnitsToScroll();
	}

	public boolean inWindow()
	{
		return inWindow;
	}
	public Point getPosition() { return new Point(position); }

	public int getScroll()
	{
		int scroll = wheelDelta;
		wheelDelta = 0;
		return scroll;
	}
	public int peekScroll()
	{
		return wheelDelta;
	}
	public int getScrollNormal()
	{
		int scroll = getScroll();
		return scroll / Math.abs(scroll);
	}
	public int peekScrollNormal()
	{
		int scroll = peekScroll();
		return scroll / Math.abs(scroll);
	}

	private final Point position;
	private boolean inWindow = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private int wheelDelta = 0;
}
