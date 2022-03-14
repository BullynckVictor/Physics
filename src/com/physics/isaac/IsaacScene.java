package com.physics.isaac;

import com.physics.Renderer;
import com.physics.Scene;
import com.physics.SceneHandler;
import com.physics.Transform;
import com.physics.util.DeltaTime;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class IsaacScene extends Scene {

		/* 			//***PONG GAME***
	@Override
	public void load() {
		input = new InputManager(renderer);
		A_MOVE_DOWN	 	= input.newAction();
		A_MOVE_UP	 	= input.newAction();
		B_MOVE_DOWN 	= input.newAction();
		B_MOVE_UP		= input.newAction();

		input.addToAction(A_MOVE_DOWN, 's');
		input.addToAction(A_MOVE_UP, 'z');
		input.addToAction(B_MOVE_DOWN, 'l');
		input.addToAction(B_MOVE_UP, 'o');



	}

	@Override
	public void update(DeltaTime dt) {

		input.handleInput();

		ball.Move();
		ball.CollisionY();
		ball.CollisionX(p1.x, p1.y, p2.x, p2.y);
		if(input.getAction(A_MOVE_DOWN))
			p1.MoveDown();
		if(input.getAction(A_MOVE_UP))
			p1.MoveUp();
		if(input.getAction(B_MOVE_DOWN))
			p2.MoveDown();
		if(input.getAction(B_MOVE_UP))
			p2.MoveUp();
		ball.Reset(p1.x, p1.y, p1.Score, p2.x, p2.y, p2.Score);
		p1.Win(ball.x);
		p2.Win(ball.x);
	}

	@Override
	public void render() {
		renderer.getWindow().setTitle("Pong");
		renderer.fillRectangle(p1.x, p1.y, 0.05f, 0.4f, Color.red);
		renderer.fillRectangle(p2.x, p2.y, 0.05f, 0.4f, Color.red);
		renderer.fillCircle(ball.x, ball.y, 0.05f, Color.black);
	}
	ball ball = new ball(0,0,-0.003f,0.003f);
	paddle p1 = new paddle(-1.4f,0, false, 0);
	paddle p2 = new paddle(1.4f,0, true, 0);

	InputManager input;
	int A_MOVE_UP = -1;
	int A_MOVE_DOWN = -1;
	int B_MOVE_UP = -1;
	int B_MOVE_DOWN = -1;*/


	//TETRIS




	public IsaacScene(Renderer renderer, SceneHandler sceneHandler) {
		super(renderer, sceneHandler);

	}

	Piece piece = new Piece(ThreadLocalRandom.current().nextInt(0,7));
	Field field = new Field();
	KeyEvent e;
	int pressed;

	@Override
	public void load() {
		//field.BlokInVeld(piece.relativeX, piece.relativeY, piece.form);
		//System.out.println(Arrays.deepToString(field.field));
		//piece.Rotate();

	}

	@Override
	public void update(DeltaTime dt) throws Exception {
		piece.Lower(field);

		if (input.keyboard.keyPressed(KeyEvent.VK_LEFT)) {
			if (pressed == 0) {
				piece.Left(field);
			}
			pressed = 1;
		} else if (input.keyboard.keyPressed(KeyEvent.VK_RIGHT)) {
			if (pressed == 0) {
				piece.Right(field);
			}
			pressed = 1;
		} else if (input.keyboard.keyPressed(KeyEvent.VK_UP)) {
			if (pressed == 0) {
				piece.Rotate(field);
			}
			pressed = 1;
		} else if (input.keyboard.keyPressed(KeyEvent.VK_DOWN)) {
			if (pressed == 0) {
				piece.Down(field);
			}
			pressed = 1;
		} else {
			pressed = 0;
		}

		if(piece.TimeForNewPiece) {
			piece = new Piece(ThreadLocalRandom.current().nextInt(0,7));
		}

        for(int rowN = 0; rowN<20;rowN++){
            if(field.FullRow(rowN)){
                field.RemoveRow(rowN);
            }
        }

		updateScene();
	}



	@Override
	public void render() {
		//System.out.println(Arrays.deepToString(piece.form));
		renderer.getWindow().setTitle("Tetris");
		for(int j = 0; j<4; j++) {
			for (int i = 0; i<4; i++) {
				if(piece.form[i][j] == 1) {
					renderer.fillRectangle(0.1f*j + 0.1f * piece.relativeX - 0.5f, -0.1f*i - 0.1f * piece.relativeY + 1, 0.1f, 0.1f, piece.color );
					renderer.drawRectangle(0.1f*j + 0.1f * piece.relativeX - 0.5f, -0.1f*i - 0.1f * piece.relativeY + 1, 0.1f, 0.1f, Color.black);
					renderer.fillCircle(0.1f * piece.relativeX - 0.5f,-0.1f * piece.relativeY + 1,0.01f, Color.black);
				}
			}
		} //draw piece
		for (int k = 0; k < 10; k++) {
			for(int l = 0; l<20; l++) {
				if(field.field[l][k] == 0)  {
					renderer.drawRectangle(0.1f*k - 0.5f, -0.1f * l +1, 0.1f, 0.1f, Color.black);
				}
				if(field.field[l][k] == 1) {
					renderer.fillRectangle(0.1f*k - 0.5f, -0.1f * l +1, 0.1f, 0.1f, Color.lightGray);
					renderer.drawRectangle(0.1f*k - 0.5f, -0.1f * l +1, 0.1f, 0.1f, Color.black);
				}
				//renderer.drawLine(0.1f*k - 0.5f, 1, 0.1f*k - 0.5f, -1, Color.black  );
				//renderer.drawLine(-0.5f, -0.1f*l+1, 0.5f, -0.1f*l+1, Color.black  );

			}
		} //draw field
	}
}
