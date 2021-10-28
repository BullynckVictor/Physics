package com.physics;

public class Vector {

	float x;
	float y;

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
	public void add(float x, float y) { //1 of 2 variabelen?
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
		this.x /= l;
		this.y /= l;
	}
}