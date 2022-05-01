package com.physics;

public class CollisionHandler {

	private static boolean collideCircleCircle(PhysicsObject objectA, PhysicsObject objectB) {
		Circle circleA = (Circle)objectA.collider;
		Circle circleB = (Circle)objectB.collider;
		double distanceSQ = Vector.distanceSQ(objectA.position, objectB.position);
		double radiusSQ = circleA.radius * circleA.radius  + 2 * circleA.radius * circleB.radius + circleB.radius * circleB.radius;
		return distanceSQ <= radiusSQ+1e-9;
	}

	private static boolean collideCircleAAB(PhysicsObject objectA, PhysicsObject objectB) {
		Circle circle = (Circle) objectA.collider;
		AAB box = (AAB) objectB.collider;

		double minx = objectB.position.x - box.width / 2;
		double maxx = objectB.position.x + box.width / 2;
		double miny = objectB.position.y - box.height / 2;
		double maxy = objectB.position.y + box.height / 2;

		Vector closestPointCircle = new Vector(objectA.position);

		if (closestPointCircle.x < minx)
			closestPointCircle.x = minx;

		else if (closestPointCircle.x > maxx)
			closestPointCircle.x = maxx;

		if (closestPointCircle.y < miny)
			closestPointCircle.y = miny;

		else if (closestPointCircle.y > maxy)
			closestPointCircle.y = maxy;

		return Vector.distanceSQ(objectA.position, closestPointCircle) <= circle.radius * circle.radius;
	}

	private static boolean collideAABAAB(PhysicsObject objectA, PhysicsObject objectB) {
		AAB boxA = (AAB) objectA.collider;
		AAB boxB = (AAB) objectB.collider;
		double minxA = objectA.position.x - boxA.width / 2;
		double maxxA = objectA.position.x + boxA.width / 2;
		double minyA = objectA.position.y - boxA.height / 2;
		double maxyA = objectA.position.y + boxA.height / 2;

		double minxB = objectB.position.x - boxB.width / 2;
		double maxxB = objectB.position.x + boxB.width / 2;
		double minyB = objectB.position.y - boxB.height / 2;
		double maxyB = objectB.position.y + boxB.height / 2;

		return minxA < maxxB && minxB <= maxxA && minyA <= maxyB && minyB < maxyA;
	}

	public static boolean collide(PhysicsObject a, PhysicsObject b) {
		return switch (a.collider.getType()) {
			case CIRCLE -> switch (b.collider.getType()) {
				case CIRCLE -> collideCircleCircle(a, b);
				case AAB -> collideCircleAAB(a, b);
			};
			case AAB -> switch (b.collider.getType()) {
				case CIRCLE -> collideCircleAAB(b, a);
				case AAB -> collideAABAAB(a, b);
			};
		};
	}


	public static void resolve(PhysicsObject a, PhysicsObject b) {
		switch (a.collider.getType()) {
			case CIRCLE:
				switch (b.collider.getType()) {
					case CIRCLE -> resolveCircleCircle(a, b);
					case AAB -> resolveCircleAAB(a, b);
				}
				break;
			case AAB:
				switch (b.collider.getType()) {
					case CIRCLE -> resolveCircleAAB(b, a);
					case AAB -> resolveAABAAB(a, b);
				}
				break;
		}
	}

	private static void resolveCircleCircle(PhysicsObject objectA, PhysicsObject objectB) {
		Circle circleA = (Circle)objectA.collider;
		Circle circleB = (Circle)objectB.collider;

		Vector from = Vector.sub(objectB.position, objectA.position);
		Vector vfrom = Vector.sub(objectB.velocity, objectA.velocity);

		double dt0 = (-from.length() + (circleA.radius + circleB.radius))/vfrom.length();
		double dt1 = (-from.length() - (circleA.radius + circleB.radius))/vfrom.length();
		double dt = Math.max(dt0,dt1);

		objectA.position = Vector.sub(objectA.position, Vector.mul(objectA.velocity, dt));
		objectB.position = Vector.sub(objectB.position, Vector.mul(objectB.velocity, dt));
	}

	private static void resolveCircleAAB(PhysicsObject objectA, PhysicsObject objectB) {
		Circle circle = (Circle)objectA.collider;
		AAB box = (AAB)objectB.collider;

		Vector from = Vector.sub(objectB.position, objectA.position);
		Vector vfrom = Vector.sub(objectB.velocity, objectA.velocity);

		double minx = objectB.position.x - box.width / 2;
		double maxx = objectB.position.x + box.width / 2;
		double miny = objectB.position.y - box.height / 2;
		double maxy = objectB.position.y + box.height / 2;

		Vector closestPointCircle = new Vector(objectA.position);

		if (closestPointCircle.x < minx)
			closestPointCircle.x = minx;

		else if (closestPointCircle.x > maxx)
			closestPointCircle.x = maxx;

		if (closestPointCircle.y < miny)
			closestPointCircle.y = miny;

		else if (closestPointCircle.y > maxy)
			closestPointCircle.y = maxy;

		double rbox = Vector.sub(objectB.position, closestPointCircle).length();

		double dt = (-from.length() + (circle.radius + rbox))/ vfrom.length();

		objectA.position = Vector.sub(objectA.position, Vector.mul(objectA.velocity, dt));
		objectB.position = Vector.sub(objectB.position, Vector.mul(objectB.velocity, dt));
	}

	private static void resolveAABAAB(PhysicsObject objectA, PhysicsObject objectB) {
		AAB boxA = (AAB) objectA.collider;
		AAB boxB = (AAB)objectB.collider;

		Vector from = Vector.sub(objectB.position, objectA.position);
		Vector vfrom = Vector.sub(objectB.velocity, objectA.velocity);;
		double m = from.y/from.x;
		double d;
		double dt;

		/*if(from.y - boxA.height/2 - boxB.height/2 < from.x - boxA.width/2 - boxB.width/2) {
			d = boxA.height/2 + boxB.height/2;
		} else {
			d = boxA.width/2 + boxB.width/2;
		}*/



		if(Math.abs(m)> boxA.height/boxA.width || from.x == 0) {		// cirkel boven/onder
			d = boxA.height/2 + boxB.height/2;
		} else {		// cirkel links/rechts
			d = boxA.width/2 + boxB.width/2;
		}
			dt = (-from.length() + d) / vfrom.length();
		objectA.position = Vector.sub(objectA.position, Vector.mul(objectA.velocity, dt));
		objectB.position = Vector.sub(objectB.position, Vector.mul(objectB.velocity, dt));
	}

	public static Vector normal(PhysicsObject objectA, PhysicsObject objectB) {
		Vector normal = new Vector();
		Vector from = Vector.sub(objectB.position, objectA.position);
		double m = from.y/ from.x;

		switch (objectA.collider.getType()) {
			case CIRCLE:
				switch (objectB.collider.getType()) {
					case CIRCLE -> {
						from.normalise();
						normal.set(from);
					}
					case AAB -> {
						AAB box = (AAB) objectB.collider;
						if (Math.abs(m) > box.height / box.width || from.x == 0) { // boven of onder
							normal.y = 1;
						} else {
							normal.x = 1;
						}
					}
				}
				break;
			case AAB:
				AAB box = (AAB) objectA.collider;
				if (Math.abs(m) > box.height / box.width || from.x == 0) { // boven of onder
					normal.y = 1;
				} else {
					normal.x = 1;
				}
				break;
		}
		normal.normalise();
		return normal;
	}

}
