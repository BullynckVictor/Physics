package com.physics;

import java.util.List;

public class GravityCalculator implements ResultantForceCalculator
{
	public GravityCalculator() {
		constant = 9.80665;
		direction = new Vector(0, -constant);
	}
	public GravityCalculator(double constant) {
		this.constant = constant;
		direction = new Vector(0, -constant);
	}
	public GravityCalculator(Vector direction) {
		constant = 9.80665;
		this.direction = direction;
	}
	public GravityCalculator(double constant, Vector direction) {
		this.constant = constant;
		this.direction = Vector.mul(Vector.normalise(direction), constant);
	}

	@Override
	public void calculateResultantForce(PhysicsObject object, List<PhysicsObject> objects, int index)
	{
		object.force.add(Vector.mul(direction, object.mass));
	}

	public void setConstant(double constant) {
		this.constant = constant;
	}
	public void setDirection(Vector direction) {
		this.direction = new Vector(direction);
		this.direction.normalise();
		this.direction.mul(constant);
	}
	public double getConstant() {
		return constant;
	}
	public Vector getDirection() {
		Vector dir = new Vector(direction);
		dir.normalise();
		return dir;
	}

	private double constant;
	private Vector direction;
}
