package com.physics;

import java.awt.*;

public class Debug {

	public Debug() {
		color = Color.GREEN;
	}
	public Debug(Renderer renderer) {
		Debug.renderer = renderer;
		color = Color.GREEN;
	}

	static void setRenderer(Renderer renderer) {
		Debug.renderer = renderer;
	}
	static void setColor(Color color) {
		Debug.color = color;
	}

	static boolean enabled() {
		return renderer != null;
	}

	static void drawCollider(PhysicsObject object)
	{
		if (!enabled())
			return;

		switch (object.collider.getType())
		{
			case AAB -> {
				AAB box = (AAB)object.collider;
				renderer.drawRectangle(object.position.x, object.position.y, box.width, box.height, color);
			}
			case CIRCLE -> {
				Circle circle = (Circle)object.collider;
				renderer.drawCircle(object.position.x, object.position.y, circle.radius, color);
			}
		}
	}

	static void drawVector(Vector vector, Vector origin)
	{
		if (!enabled())
			return;

		Vector point = Vector.add(origin, vector);
		renderer.drawLine(origin, point, color);
	}

	public static Renderer renderer;
	public static Color color;
}
