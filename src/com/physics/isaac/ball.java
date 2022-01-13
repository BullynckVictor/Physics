package com.physics.isaac;

public class ball {
    public float x;
    public float y;
    public double xVelocity;
    public double yVelocity;

    public ball(float x, float y, double xv, double yv) {
        this.x = x;
        this.y = y;
        this.xVelocity = xv;
        this.yVelocity = yv;
    }

    public void Move() {
        this.x += xVelocity;
        this.y += yVelocity;
    }

    public void CollisionY() {
        if(this.y > 1)
            this.yVelocity *= -1;
        if(this.y < -1)
            this.yVelocity *= -1;
    }

    public void CollisionX(float x1, float y1 , float x2, float y2) {
        if(this.x < x1 + 0.05 && this.y > y1 - 0.4/2 && this.y < y1 + 0.4/2)
            this.xVelocity *= -1;
        if(this.x > x2 - 0.05 && this.y > y2 - 0.4/2 && this.y < y2 + 0.4/2)
            this.xVelocity *= -1;
    }

    public void Reset(float x1, float y1, int Score1, float x2, float y2, int Score2) {
        if (this.x < x1 - 0.05) {
            this.x = 0;
            this.y = 0;
            this.xVelocity = -0.005 + Math.random()*(0.01);
            this.yVelocity = -0.005 + Math.random()*(0.01);
            return;
        }
        if (this.x > x2 + 0.05) {
            this.x = 0;
            this.y = 0;
            this.xVelocity = -0.005 + Math.random()*(0.01);
            this.yVelocity = -0.005 + Math.random()*(0.01);
            return;
        }

    }

    public void Score() {

    }
}
