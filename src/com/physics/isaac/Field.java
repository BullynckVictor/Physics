package com.physics.isaac;

import com.physics.Renderer;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Field {
    public int[][] field = {{0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0}};

    public Field() {

    }

    public void BlokInVeld(int x, int y, int[][] form) {
        for(int j = 0; j<4; j++) {
            for (int i = 0; i<4; i++) {
                if(form[i][j] == 1) {
                    this.field[i + y][j + x] = 1;
                }
            }
        }
    }

    public boolean FullRow(int rowN) {
        for(int i = 0; i<10; i++) {
            if(field[rowN][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public void RemoveRow(int rowN) {
        int NewField[][] = {{0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}};
        for(int i = 0; i<20; i++) {
            if(i<rowN) {
                System.arraycopy(this.field[i], 0, NewField[i + 1], 0, 10);
            }
            if(i>rowN) {
                System.arraycopy(this.field[i], 0, NewField[i], 0, 10);
            }
        }
        for(int j = 0; j<10; j++) {
            NewField[0][j] = 0;
        }
        this.field = NewField;
    }


    public void LeftSideFieldCollision(Piece piece) {
        for(int j = 0; j<4; j++) {
            for (int i = 0; i<4; i++) {
                if(piece.form[i][j] == 1) {
                    if(j+piece.relativeX + 1 < 10) {
                        if (this.field[i + piece.relativeY][j + piece.relativeX] == 1) {
                            piece.relativeX++;
                        }
                    }
                    if(j+piece.relativeX - 1 > 0) {
                        if (this.field[i + piece.relativeY][j + piece.relativeX] == 1) {
                            piece.relativeX -= 1;
                        }
                    }
                }
            }
        }
    }


    public boolean FieldCollision(int x, int y, int[][] form) {
        for(int j = 0; j<4; j++) {
            for (int i = 0; i<4; i++) {
                if(form[i][j] == 1) {
                    if(i+y+1 > 19) {
                        return true;
                    }else if(this.field[i+y+1][j+x] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }




}
