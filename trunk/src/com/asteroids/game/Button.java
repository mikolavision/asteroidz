package com.asteroids.game;

import javax.microedition.khronos.opengles.GL10;

public class Button extends Drawable{
	
	public Button(){
		super();
		float coords[] = {
				//X, Y, Z
				-0.5f, -0.1f, 0.0f,
				0.5f, -0.1f, 0.0f,
				0.5f, -0.4f, 0.0f,
				-0.5f, -0.4f, 0.0f
		};
		setDrawMode(GL10.GL_LINE_LOOP);
		setCoords(coords);
	}
	
}
