package com.physics.isaac;

import com.physics.util.DeltaTime;

import java.awt.*;
import java.util.Arrays;

public class Piece {
    public int relativeX;
    public int relativeY;
    public int Npiece; // 0: I 1: O 2: L 3: J 4: z 5: s 6: t
    public Color color;
    public int rotation; // 0 1 2 3
    public int[][] form = { {0,0,0,0},
                            {0,0,0,0},
                            {0,0,0,0},
                            {0,0,0,0}};
    int wait = 0;
    public  boolean TimeForNewPiece;

    public float x = 0.1f * relativeX - 0.5f;
    public float y = -0.05f * relativeY + 1;

    public Piece(int piece) {
        this.relativeX = 3;
        this.relativeY = 0;
        this.Npiece = piece;
        this.TimeForNewPiece = false;
        switch (piece) {
            case 0 -> {
                this.form[0][1] = 1;        //[[0,1,0,0],
                this.form[1][1] = 1;        // [0,1,0,0],
                this.form[2][1] = 1;        // [0,1,0,0],
                this.form[3][1] = 1;        // [0,1,0,0]]
                this.color = Color.cyan;
            }
            case 1 -> {
                this.form[1][1] = 1;        //[[0,0,0,0],
                this.form[1][2] = 1;        // [0,1,1,0],
                this.form[2][1] = 1;        // [0,1,1,0],
                this.form[2][2] = 1;        // [0,0,0,0]]
                this.color = Color.YELLOW;
            }
            case 2 -> {
                this.form[0][1] = 1;        //[[0,1,0,0],
                this.form[1][1] = 1;        // [0,1,0,0],
                this.form[2][1] = 1;        // [0,1,1,0],
                this.form[2][2] = 1;        // [0,0,0,0]]
                this.color = Color.orange;
                this.rotation = 0;
            }
            case 3 -> {
                this.form[0][2] = 1;        //[[0,0,1,0],
                this.form[1][2] = 1;        // [0,0,1,0],
                this.form[2][2] = 1;        // [0,1,1,0],
                this.form[2][1] = 1;        // [0,0,0,0]]
                this.color = Color.blue;
                this.rotation = 0;
            }
            case 4 -> {
                this.form[1][1] = 1;        //[[0 ,0 ,0 ,0 ],
                this.form[1][2] = 1;        // [1 ,1 ,0 ,0 ],
                this.form[2][2] = 1;        // [0 ,1 ,1 ,0 ],
                this.form[2][3] = 1;        // [0 ,0 ,0 ,0 ]]
                this.color = Color.red;
            }
            case 5 -> {
                this.form[1][1] = 1;        //[[0,0,0,0],
                this.form[1][2] = 1;        // [0,1,1,0],
                this.form[2][1] = 1;        // [1,1,0,0],
                this.form[2][0] = 1;        // [0,0,0,0]]
                this.color = Color.green;
            }
            case 6 -> {
                this.form[0][1] = 1;        //[[0,1,0,0],
                this.form[1][1] = 1;        // [0,1,1,0],
                this.form[2][1] = 1;        // [0,1,0,0],
                this.form[1][2] = 1;        // [0,0,0,0]]
                this.color = Color.pink;
                this.rotation = 0;
            }
        }
    }

    public void Lower(Field field) {
        wait++;
        if (wait > 110) {
            if(field.FieldCollision(this.relativeX, this.relativeY, this.form)) {
                this.Freeze(field);
            }
            this.relativeY += 1;
            wait = 0;
        }
    }

    public void Freeze(Field field) {
        field.BlokInVeld(this.relativeX, this.relativeY, this.form);
        this.TimeForNewPiece = true;
    }

    public void Left(Field field) {
        this.relativeX -= 1;
        this.LeftEdgeCollision();
        this.LeftSideFieldCollision(field);
    }

    public void Right(Field field) {
        this.relativeX += 1;
        this.RightEdgeCollision();
        this.RightSideFieldCollision(field);
    }

    public void Down(Field field) {
        this.relativeY += 1;
        if(field.FieldCollision(this.relativeX, this.relativeY, this.form)) {
            this.Freeze(field);
        }
    }

    /*public void InABlock(Field field) {
        for(int j = 0; j<4; j++) {
            for (int i = 0; i<4; i++) {
                if(this.form[i][j] == 1) {
                    if(j+this.relativeX + 1 < 10) {
                        if (field.field[i + this.relativeY][j + this.relativeX] == 1) {
                            if(j==3) {
                                this.relativeX --;
                            }
                            if(j==0) {
                                this.relativeX++;
                            }
                        }
                    }
                }
            }
        }
    }*/

    public void RightSideFieldCollision(Field field) {
        for(int j = 0; j<4; j++) {
            for (int i = 0; i<4; i++) {
                if(this.form[i][j] == 1) {
                    if(j+this.relativeX + 1 < 10) {
                        if (field.field[i + this.relativeY][j + this.relativeX] == 1) {
                            this.relativeX--;
                        }
                    }
                }
            }
        }
    }

    public void LeftSideFieldCollision(Field field) {
        for(int j = 0; j<4; j++) {
            for (int i = 0; i<4; i++) {
                if(this.form[i][j] == 1) {
                    if(j+this.relativeX + 1 > 0) {
                        if (field.field[i + this.relativeY][j + this.relativeX] == 1) {
                            this.relativeX++;
                        }
                    }
                }
            }
        }
    }

    public void RightEdgeCollision() {
        boolean collision = false;
        for(int i = 3; i>0; i--) {
            if(this.relativeX + i > 9){
                for(int j = 0; j<4; j++) {
                    if(this.form[j][i] == 1) {
                        collision = true;
                        break;
                    }
                }
            }
        }
        if (collision) {
            this.relativeX -= 1;
        }
    }

    public void LeftEdgeCollision() {
        boolean collision = false;
        for(int i = 0; i<4; i++) {
            if(this.relativeX + i < 0){
                for(int j = 0; j<4; j++) {
                    if(this.form[j][i] == 1) {
                        collision = true;
                        break;
                    }
                }
            }
        }
        if (collision) {
            this.relativeX += 1;
        }
    }

    public boolean NoPlaceToTurn(int[][] form, Field field) {
        for(int j = 0; j<4; j++) {
            for (int i = 0; i<4; i++) {
                if(form[i][j] == 1) {
                    if(j+this.relativeX < 0 || j+this.relativeX > 9) {
                        return true;
                    }else if(field.field[i+this.relativeY][j+this.relativeX] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void Rotate(Field field) {
        int[][] Turn = {{0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0}};
        //System.out.println(Arrays.deepToString(this.form));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Turn[j][3 - i] = this.form[i][j];
            }
        }
        if(!NoPlaceToTurn(Turn, field)){
            this.form = Turn;
        }
        //System.out.println(Arrays.deepToString(this.form));
    }
}


/*
 1, 2, 3, 4
 5, 6, 7, 8
 9,10,11,12
13,14,15,16

4 1 13 16: (0,3) (0,0) (3,0) (3,3)
8 2 9 15: (1,3) (0,1) (2,0) (3,2)
12 3 5 14: (2,3) (0,2) (1,0) (3,1)

7 6 10 11: (1,2) (1,1) (2,1) (2,2)
 */
