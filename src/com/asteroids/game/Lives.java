package com.asteroids.game;

import javax.microedition.khronos.opengles.GL10;


public class Lives extends Drawable {

	public int numLives = 3;
	
	public Lives(){
		super();
		float coords[] = {
	            // X, Y, Z
				-0.5f/6f, -0.25f/4, 0,
	             0.0f,  0.559016994f/4, 0,
	             0.5f/6f, -0.25f/4, 0,
	             0.0f, -0.0f, 0,
	            -0.5f/6f, -0.25f/4, 0
	        };
		setCoords(coords);
		setDrawMode(GL10.GL_LINE_STRIP);
		
	}

}
