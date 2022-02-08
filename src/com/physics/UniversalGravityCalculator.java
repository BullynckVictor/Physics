package com.physics;

import java.util.List;

public class UniversalGravityCalculator implements ResultantForceCalculator {

	public UniversalGravityCalculator() {
		constant = (float)6.674e-11;
	}
	public UniversalGravityCalculator(float constant) {
		this.constant = constant;
	}

	@Override
	public void calculateResultantForce(PhysicsObject object, List<PhysicsObject> objects, int index) {

	}

	public float constant;
}
