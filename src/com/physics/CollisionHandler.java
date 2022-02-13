package com.physics;

public class CollisionHandler {

	private static boolean collideCircleCircle(PhysicsObject objectA, PhysicsObject objectB) {
		Circle circleA = (Circle)objectA.collider;
		Circle circleB = (Circle)objectB.collider;
		float distanceSQ = Vector.distanceSQ(objectA.position, objectB.position);
		float radiusSQ = circleA.radius * circleA.radius  + 2 * circleA.radius * circleB.radius + circleB.radius * circleB.radius;
		return distanceSQ <= radiusSQ;
	}

	private static boolean collideCircleAAB(PhysicsObject objectA, PhysicsObject objectB) {
		Circle circle = (Circle) objectA.collider;
		AAB box = (AAB) objectB.collider;

		float minx = objectB.position.x - box.width / 2;
		float maxx = objectB.position.x + box.width / 2;
		float miny = objectB.position.y - box.height / 2;
		float maxy = objectB.position.y + box.height / 2;

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
		float minxA = objectA.position.x - boxA.width / 2;
		float maxxA = objectA.position.x + boxA.width / 2;
		float minyA = objectA.position.y - boxA.height / 2;
		float maxyA = objectA.position.y + boxA.height / 2;

		float minxB = objectB.position.x - boxB.width / 2;
		float maxxB = objectB.position.x + boxB.width / 2;
		float minyB = objectB.position.y - boxB.height / 2;
		float maxyB = objectB.position.y + boxB.height / 2;

		return minxA < maxxB && minxB <= maxxA && minyA <= maxyB && minyB < maxyA;

		/*
		Vector from = Vector.sub(objectA.position, objectB.position);

		if(Math.abs(from.x) <= boxA.width/2 + boxB.width/2)
			return Math.abs(from.y) <= boxA.height/2 + boxB.height/2;

		kdenk da dit efficienter is
		* */
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

		float dt = (-from.length() + (circleA.radius + circleB.radius))/vfrom.length();

		objectA.position = Vector.sub(objectA.position, Vector.mul(objectA.velocity, dt));
		objectB.position = Vector.sub(objectB.position, Vector.mul(objectB.velocity, dt));
	}

	private static void resolveCircleAAB(PhysicsObject objectA, PhysicsObject objectB) {
		Circle circle = (Circle)objectA.collider;
		AAB box = (AAB)objectB.collider;

		Vector from = Vector.sub(objectB.position, objectA.position);
		Vector vfrom = Vector.sub(objectB.velocity, objectA.velocity);;
		float m = from.y/from.x;
		float rbox;

		if(Math.abs(m)> box.height/box.width || from.x == 0) {		// cirkel boven/onder
			rbox = (float) Math.sqrt((1+1/(m*m)))*box.height/2;
		} else {		// cirkel links/rechts
			rbox = (float) Math.sqrt((1+m*m))*box.width/2;
		}

		float dt = (-from.length() + (circle.radius + rbox))/ vfrom.length();

		// if(edgecase) { groot probleem }

		objectA.position = Vector.sub(objectA.position, Vector.mul(objectA.velocity, dt));
		objectB.position = Vector.sub(objectB.position, Vector.mul(objectB.velocity, dt));
	}

	private static void resolveAABAAB(PhysicsObject objectA, PhysicsObject objectB) {
		AAB boxA = (AAB) objectA.collider;
		AAB boxB = (AAB)objectB.collider;

		Vector from = Vector.sub(objectB.position, objectA.position);
		Vector vfrom = Vector.sub(objectB.velocity, objectA.velocity);;
		float m = from.y/from.x;
		float d;
		float dt;

		if(Math.abs(m)> boxA.height/boxA.width || from.x == 0) {		// cirkel boven/onder
			d = boxA.height/2 + boxB.height/2;
			dt = (-from.y + d)/vfrom.y;
		} else {		// cirkel links/rechts
			d = boxA.width/2 + boxB.width/2;
			dt = (-from.x + d)/vfrom.x;
		}

		objectA.position = Vector.sub(objectA.position, Vector.mul(objectA.velocity, dt));
		objectB.position = Vector.sub(objectB.position, Vector.mul(objectB.velocity, dt));
	}

	public static Vector normal(PhysicsObject objectA, PhysicsObject objectB) {
		Vector normal = new Vector();
		Vector from = Vector.sub(objectB.position, objectA.position);
		float m = from.y/ from.x;

		switch (objectA.collider.getType()) {
			case CIRCLE:
				switch (objectB.collider.getType()) {
					case CIRCLE -> {
						if(m==0) {
							normal.add(0,1);
						} else {
							float n = -1 / m;
							normal.add(n, 1);
							normal.normalise();
						}
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
