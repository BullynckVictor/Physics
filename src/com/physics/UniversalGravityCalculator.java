package com.physics;

import java.util.List;

public class UniversalGravityCalculator implements ResultantForceCalculator {

	public UniversalGravityCalculator() {
		constant = (float)6.674e-1;
	}
	public UniversalGravityCalculator(float constant) {
		this.constant = constant;
	}

	@Override
	public void calculateResultantForce(PhysicsObject object, List<PhysicsObject> objects, int index) {
		for(int i = index + 1; i < objects.size(); i++) {
			PhysicsObject other = objects.get(i);									// het andere object
			Vector from = Vector.sub(other.position, object.position);				// de vector die van object naar other gaat
			float force = constant * object.mass * other.mass / from.lengthSQ();	// rsqrd = de lengte² van het verschil ('from')
			from.normalise();														// from wordt herleid naar een richting, zie https://www.khanacademy.org/computing/computer-programming/programming-natural-simulations/programming-vectors/a/vector-magnitude-normalization
			from.mul(force);														// dit is nu de Fz in object, hij wijst naar het ander object en heeft de grootte van Fz
			object.force.add(from);													// et voila
			other.force.sub(from);
			// kan denk ik geoptimaliseerd worden door slechts de helft te loopen en dan voor beide objecten zetten
			// bv.: other.sub(from); (Fz aftrekken)
		}
	}

	public float constant;
}
