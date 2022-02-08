package com.physics;

import java.util.List;

public class GravityCalculator implements ResultantForceCalculator
{
	public GravityCalculator() {
		constant = 9.80665f;
		direction = new Vector(0, -constant);
	}

	@Override
	public void calculateResultantForce(PhysicsObject object, List<PhysicsObject> objects, int index)
	{
		object.force.add(Vector.mul(direction, object.mass));
	}

	public void setConstant(float constant) {
		this.constant = constant;
	}
	public void setDirection(Vector direction) {
		this.direction = new Vector(direction);
		this.direction.normalise();
		this.direction.mul(constant);
	}
	public float getConstant() {
		return constant;
	}
	public Vector getDirection() {
		Vector dir = new Vector(direction);
		dir.normalise();
		return dir;
	}

	private float constant;
	private Vector direction;
}
