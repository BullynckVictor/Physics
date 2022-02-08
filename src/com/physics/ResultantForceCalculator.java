package com.physics;

import java.util.List;

public interface ResultantForceCalculator {
	void calculateResultantForce(PhysicsObject object, List<PhysicsObject> objects, int index);
}
