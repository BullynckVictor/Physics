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
    public void add(float x, float y) { //1 of 2 variablen?
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

    // basis bewerkingen met vectoren
    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void mul(Vector v) {
        this.x *= v.x;
        this.y *= v.y;
    }

    public void div(Vector v) {
        this.x /= v.x;
        this.y /= v.y;
    }

    // extra's
    public float lengthSQ(Vector v) {
        return  v.x * v.x + v.y * v.y;
    }

    public float length(Vector v) {
        return (float) Math.sqrt(lengthSQ(v));
    }

    // speciale vector bewerkingen
    public float dot(Vector v) {
        return v.x * v.x + this.y * v.y; // niet zeker hierover
    }

    public void norm(Vector v) {
        float l = length(v);
        this.x /= l;
        this.y /= l;
    }
}

