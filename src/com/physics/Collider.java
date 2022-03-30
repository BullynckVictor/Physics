package com.physics;

public class Collider
{
	public Collider() { type = null; }
	protected Collider(ColliderType type) { this.type = type; }

	public ColliderType getType() {
		return type;
	}

	public double area() {
		return 0;
	}

	private final ColliderType type;
}
