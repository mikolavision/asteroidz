package com.asteroids.game;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;

public class Ship extends Drawable implements Flyable{
	
	private float SPEED = 0.5f;

	public Ship(){
		super();
		float coords[] = {
	            // X, Y, Z
	            -0.5f/3f, -0.25f/2, 0,
	             0.0f,  0.559016994f/2, 0,
	             0.5f/3f, -0.25f/2, 0,
	            -0.5f/3f, -0.25f/2, 0
	        };
		setCoords(coords);
		setDrawMode(GL10.GL_LINE_STRIP);
	}

	public void update() {
		
		// Working on fixing issues with thrust
		position.x += thrust.x * SPEED;
		position.y += thrust.y * SPEED;
		
		//Flip the ship to the opposite side if it goes off-screen
		if(Math.abs(position.x) >= GameRenderer.SCREEN_WIDTH)
			position.x = -position.x;
		
		if(Math.abs(position.y) >= GameRenderer.SCREEN_HEIGHT)
			position.y = -position.y;
		
		
	}

	public void setAngle(float angle){
		this.angle = (float) (angle -Math.PI / 2);
	}
	
	public boolean collidesWith(Flyable obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public void explode() {
		// TODO Auto-generated method stub
		
	}

	public void move() {
		thrust.x += -.01*Math.sin(angle*Math.PI/180);
    	thrust.y += .01*Math.cos(angle*Math.PI/180);
	}
	
}
