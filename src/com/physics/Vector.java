package com.physics;

public class Vector {

	public float x;
	public float y;

	public Vector(Vector other) {
		x = other.x;
		y = other.y;
	}
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public Vector(float value) {
		x = value;
		y = value;
	}
	public Vector() {
		x = 0;
		y = 0;
	}

	// bewerkingen met vaste waarde
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	public void sub(float x, float y) {
		this.x -= x;
		this.y -= y;
	}
	public void mul(float x, float y) {
		this.x *= x;
		this.y *= y;
	}
	public void div(float x, float y) {
		this.x /= x;
		this.y /= y;
	}

	public void add(float value) {
		this.x += value;
		this.y += value;
	}
	public void sub(float value) {
		this.x -= value;
		this.y -= value;
	}
	public void mul(float value) {
		this.x *= value;
		this.y *= value;

	}
	public void div(float value) {
		this.x /= value;
		this.y /= value;
	}

	// basis bewerkingen met vectoren
	public void add(Vector v) {
		add(v.x, v.y);
	}
	public void sub(Vector v) {
		sub(v.x, v.y);
	}
	public void mul(Vector v) {
		mul(v.x, v.y);
	}
	public void div(Vector v) {
		div(v.x, v.y);
	}

	// static bewerkingen
	// bewerkingen met vaste waarde
	public static Vector add(Vector vector, float x, float y) {
		Vector vec = new Vector(vector);
		vec.x += x;
		vec.y += y;
		return vec;
	}
	public static Vector sub(Vector vector, float x, float y) {
		Vector vec = new Vector(vector);
		vec.x -= x;
		vec.y -= y;
		return vec;
	}
	public static Vector mul(Vector vector, float x, float y) {
		Vector vec = new Vector(vector);
		vec.x *= x;
		vec.y *= y;
		return vec;
	}
	public static Vector div(Vector vector, float x, float y) {
		Vector vec = new Vector(vector);
		vec.x /= x;
		vec.y /= y;
		return vec;
	}

	public static Vector add(Vector vector, float value) {
		Vector vec = new Vector(vector);
		vec.x += value;
		vec.y += value;
		return vec;
	}
	public static Vector sub(Vector vector, float value) {
		Vector vec = new Vector(vector);
		vec.x -= value;
		vec.y -= value;
		return vec;
	}
	public static Vector mul(Vector vector, float value) {
		Vector vec = new Vector(vector);
		vec.x *= value;
		vec.y *= value;
		return vec;
	}
	public static Vector div(Vector vector, float value) {
		Vector vec = new Vector(vector);
		vec.x /= value;
		vec.y /= value;
		return vec;
	}

	// basis bewerkingen met vectoren
	public static Vector add(Vector lhs, Vector rhs) {
		return Vector.add(lhs, rhs.x, rhs.y);
	}
	public static Vector sub(Vector lhs, Vector rhs) {
		return Vector.sub(lhs, rhs.x, rhs.y);
	}
	public static Vector mul(Vector lhs, Vector rhs) {
		return Vector.mul(lhs, rhs.x, rhs.y);
	}
	public static Vector div(Vector lhs, Vector rhs) {
		return Vector.mul(lhs, rhs.x, rhs.y);
	}

	// extra's
	public float lengthSQ() {
		return x * x + y * y;
	}

	public float length() {
		return (float) Math.sqrt(lengthSQ());
	}

	// speciale vector bewerkingen
	public float dot(Vector v) {
		return x * v.x + y * v.y;
	}

	public void normalise() {
		float l = length();
		if (l != 0) {
			this.x /= l;
			this.y /= l;
		}
	}
	static Vector normalise(Vector vector) {
		Vector vec = new Vector(vector);
		vec.normalise();
		return vec;
	}




}