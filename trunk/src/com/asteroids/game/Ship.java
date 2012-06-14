package com.asteroids.game;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;

public class Ship extends Drawable implements Flyable{
	
	private float SPEED = 0.5f;
	public Vector2f position;
	public Vector2f thrust = new Vector2f(0.00001f, 0.00001f);
	protected float angle;
	public float MAX_SPEED = 5.0f;
	public float radius = .25f;
	public boolean active = true;
	
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
		
		position = new Vector2f();
		position.x = 0;
		position.y = 0;
		
		angle = 0;
	}

	public void update() {
		
		//drag is commented out so i can tell if the ship is moving on my emulator
		thrust.x = (float) (thrust.x/1.01);
		thrust.y = (float) (thrust.y/1.01);
		
		//thrust
		if(GameRenderer.isPressed)
		{
			move();
		}
		
		//Set a max speed the ship can move
		if(thrust.x > MAX_SPEED)
			thrust.x = MAX_SPEED;
		
		if(thrust.y > MAX_SPEED)
			thrust.y = MAX_SPEED;
				
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
	
	public float getAngle(){
		return angle;
	}
	
	//implement circle collisions
	public boolean collidesWith(Asteroid obj) {
		
		float distance = (float) Math.sqrt(Math.pow(position.x - obj.position.x, 2) + Math.pow(position.y - obj.position.y, 2));
		if(distance <= (radius+ obj.radius))
			return true;
		else
			return false;
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
    	System.out.println("thrust on");

	}
	
	public Vector2f getPosition()
	{
		return position;
	}

	
}
