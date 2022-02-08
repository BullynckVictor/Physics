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
				boolean isaacMethod = false;
				if (isaacMethod)
				{
					float rsqrd = (object.position.x - objects.get(i).position.x) * (object.position.x - objects.get(i).position.x) +
							(object.position.y - objects.get(i).position.y) * (object.position.y - objects.get(i).position.y);
					float UniversalG = constant * object.mass * objects.get(i).mass / rsqrd;
					object.force.add(UniversalG*(object.position.x - objects.get(i).position.x)/(float) Math.sqrt(rsqrd), UniversalG*(object.position.y - objects.get(i).position.y)/(float) Math.sqrt(rsqrd));
				}
				else
				{
					PhysicsObject other = objects.get(i);									// het andere object
					Vector from = Vector.sub(other.position, object.position);				// de vector die van object naar other gaat
					float force = constant * object.mass * other.mass / from.lengthSQ();	// rsqrd = de lengte² van het verschil ('from')
					from.normalise();														// from wordt herleid naar een richting, zie https://www.khanacademy.org/computing/computer-programming/programming-natural-simulations/programming-vectors/a/vector-magnitude-normalization
					from.mul(force);														// dit is nu de Fz in object, hij wijst naar het ander object en heeft de grootte van Fz
					object.force.add(from);													// et voila
					// kan denk ik geoptimaliseerd worden door slechts de helft te loopen en dan voor beide objecten zetten
					// bv.: other.sub(from); (Fz aftrekken)
				}
			}
		}
	}

	public float constant;
}
