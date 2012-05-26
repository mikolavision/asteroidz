package com.asteroids.game;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;

public class Ship extends Drawable {

	public Ship(){
		super();
		float coords[] = {
	            // X, Y, Z
	            -0.5f/3f, -0.25f/2, 0,
	             0.0f,  0.559016994f/2, 0,
	             0.5f/3f, -0.25f/2, 0,
	             0.0f, -0.0f, 0,
	            -0.5f/3f, -0.25f/2, 0
	        };
		setCoords(coords);
		setDrawMode(GL10.GL_LINE_STRIP);
	}
	
}
