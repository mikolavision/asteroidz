package com.asteroids.game;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;

public class Asteroid extends Drawable implements Flyable {
	
	public Vector2f position;
	public Vector2f thrust = new Vector2f(0.00001f, 0.00001f);
	protected float angle;
	
	public Asteroid(){
		super();
		float coords[] = {
	            // X, Y, Z
	            -0.2f, -0.2f, 0,
	             -0.2f,  0.2f, 0,
	             0.2f, 0.2f, 0,
	            0.2f, -0.2f, 0,
	            -0.2f, -0.2f, 0
	        };
		setCoords(coords);
		setDrawMode(GL10.GL_LINE_STRIP);
		
		position = new Vector2f();
		position.x = 0;
		position.y = 0;
			
		thrust.y = (float) ((2*Math.random()-1)/10);
		thrust.x = (float) ((2*Math.random()-1)/10);
		
		angle = 0;
	}
	
	@Override
	public void update() {
		//thrust
		position.x += thrust.x;
		position.y += thrust.y; 
		
		//slowly rotate the asteroid
		angle = (float) (angle - Math.PI/16);
		
		//Flip the ship to the opposite side if it goes off-screen
		if(Math.abs(position.x) >= GameRenderer.SCREEN_WIDTH)
			position.x = -position.x;
		
		if(Math.abs(position.y) >= GameRenderer.SCREEN_HEIGHT)
			position.y = -position.y;
		
	}

	@Override
	public boolean collidesWith(Flyable obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub
		
	}
	
	public Vector2f getPosition()
	{
		return position;
	}
	
	public float getAngle(){
		return angle;
	}

}
