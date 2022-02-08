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
		for(int i=0; i < objects.size(); i++) {
			if(index != i) {
				float rsqrd = (object.position.x - objects.get(i).position.x) * (object.position.x - objects.get(i).position.x) +
						(object.position.y - objects.get(i).position.y) * (object.position.y - objects.get(i).position.y);
				float UniversalG = constant * object.mass * objects.get(i).mass / rsqrd;
				object.force.add(UniversalG*Math.abs((object.position.x - objects.get(i).position.x))/(float) Math.sqrt(rsqrd), UniversalG*Math.abs((object.position.y - objects.get(i).position.y))/(float) Math.sqrt(rsqrd));
			}
		}

	}

	public float constant;
}
