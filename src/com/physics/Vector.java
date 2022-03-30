package com.physics;

public class Vector {

	public double x;
	public double y;

	public Vector(Vector other) {
		x = other.x;
		y = other.y;
	}
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Vector(double value) {
		x = value;
		y = value;
	}
	public Vector() {
		x = 0;
		y = 0;
	}

	public void set(double value)
	{
		x = value;
		y = value;
	}
	public void set(Vector other)
	{
		x = other.x;
		y = other.y;
	}

	public void set(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	// bewerkingen met vaste waarde
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	public void sub(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	public void mul(double x, double y) {
		this.x *= x;
		this.y *= y;
	}
	public void div(double x, double y) {
		this.x /= x;
		this.y /= y;
	}

	public void add(double value) {
		this.x += value;
		this.y += value;
	}
	public void sub(double value) {
		this.x -= value;
		this.y -= value;
	}
	public void mul(double value) {
		this.x *= value;
		this.y *= value;

	}
	public void div(double value) {
		this.x /= value;
		this.y /= value;
	}
	public void equals(Vector other) {
		x = other.x;
		y = other.y;
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
	public static Vector add(Vector vector, double x, double y) {
		Vector vec = new Vector(vector);
		vec.x += x;
		vec.y += y;
		return vec;
	}
	public static Vector sub(Vector vector, double x, double y) {
		Vector vec = new Vector(vector);
		vec.x -= x;
		vec.y -= y;
		return vec;
	}
	public static Vector mul(Vector vector, double x, double y) {
		Vector vec = new Vector(vector);
		vec.x *= x;
		vec.y *= y;
		return vec;
	}
	public static Vector div(Vector vector, double x, double y) {
		Vector vec = new Vector(vector);
		vec.x /= x;
		vec.y /= y;
		return vec;
	}

	public static Vector add(Vector vector, double value) {
		Vector vec = new Vector(vector);
		vec.x += value;
		vec.y += value;
		return vec;
	}
	public static Vector sub(Vector vector, double value) {
		Vector vec = new Vector(vector);
		vec.x -= value;
		vec.y -= value;
		return vec;
	}
	public static Vector mul(Vector vector, double value) {
		Vector vec = new Vector(vector);
		vec.x *= value;
		vec.y *= value;
		return vec;
	}
	public static Vector div(Vector vector, double value) {
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
	public double lengthSQ() {
		return x * x + y * y;
	}

	public double length() {
		return Math.sqrt(lengthSQ());
	}

	// speciale vector bewerkingen
	public double dot(Vector v) {
		return x * v.x + y * v.y;
	}

	public void normalise() {
		double l = length();
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

	double distanceSQ(Vector other)
	{
		return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
	}
	double distance(Vector other)
	{
		return Math.sqrt(distanceSQ(other));
	}

	static double distanceSQ(Vector a, Vector b)
	{
		return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	}
	static double distance(Vector a, Vector b)
	{
		return Math.sqrt(distanceSQ(a, b));
	}

	static final Vector ZERO = new Vector(0);
	static final Vector ONE = new Vector(1);
	static final Vector LEFT = new Vector(-1, 0);
	static final Vector UP = new Vector(0, 1);
	static final Vector RIGHT = new Vector(1, 0);
	static final Vector DOWN = new Vector(0, -1);

	static Vector random() {
		return new Vector((Math.random() * 2f) - 1f, (Math.random() * 2f) - 1f);
	}
	static Vector randomNorm() {
		Vector vector = random();
		vector.normalise();
		return vector;
	}

	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}