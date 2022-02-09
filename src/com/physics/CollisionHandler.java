package com.physics;

public class CollisionHandler {

	// diart: Circle x Circle
	private static boolean collideCircleCircle(PhysicsObject objectA, PhysicsObject objectB)
	{
		Circle circleA = (Circle)objectA.collider;
		Circle circleB = (Circle)objectB.collider;
		return false;
	}
	// diart: Circle x AAB
	private static boolean collideCircleAAB(PhysicsObject objectA, PhysicsObject objectB)
	{
		Circle circle = (Circle)objectA.collider;
		AAB box = (AAB)objectB.collider;
		return false;
	}
	// diart: AAB x AAB
	private static boolean collideAABAAB(PhysicsObject objectA, PhysicsObject objectB)
	{
		AAB boxA = (AAB) objectA.collider;
		AAB boxB = (AAB)objectB.collider;
		return false;
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
