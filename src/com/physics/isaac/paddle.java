package com.physics.isaac;

public class paddle {
    public float x;
    public float y;
    public boolean EenOfTwee; //Paddel1 = false en Paddle2 = true
    public int Score;

    public paddle(float x, float y, boolean EenOfTwee, int Score) {
        this.x = x;
        this.y = y;
        this.EenOfTwee = EenOfTwee;
        this.Score = Score;
    }
    public void MoveUp() {
        this.y += 0.005;
    }
    public void MoveDown() {
        this.y -= 0.005;
    }
    public void Reset() {
        if(EenOfTwee)
            this.x = 1.4f;
        if(!EenOfTwee)
            this.x = -1.4f;
        this.y = 0;
    }
    public void Win(float x) {
        if (!this.EenOfTwee) {
            if(x + 0.05 < this.x) {
                this.Score++;

                this.y = 0;
            }
            return;
        }
        if (this.EenOfTwee) {
            if(x - 0.05 > this.x) {
                this.Score++;
                this.y = 0;
            }
            return;
        }
    }
}
