package com.physics;

public class CollisionHandler {

	private static boolean collideCircleCircle(PhysicsObject objectA, PhysicsObject objectB)
	{
		Circle circleA = (Circle)objectA.collider;
		Circle circleB = (Circle)objectB.collider;
		return Vector.distance(objectA.position, objectB.position) <= circleA.radius + circleB.radius;
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

		return Vector.distance(objectA.position, closestPointCircle) <= circle.radius;
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
	}

	public static boolean collide(PhysicsObject a, PhysicsObject b)
	{
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


	public static void resolve(PhysicsObject a, PhysicsObject b)
	{
	}
}
