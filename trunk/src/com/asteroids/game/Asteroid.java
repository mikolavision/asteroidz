package com.asteroids.game;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;

public class Asteroid extends Drawable implements Flyable {
	
	public Vector2f position;
	public Vector2f thrust = new Vector2f(0.00001f, 0.00001f);
	protected float angle;
	protected Random random = new Random();
	public float radius = 0.3f;
	public boolean active = true; 
	public int size = 2;
	
	//For new Asteroids created at the beggining of the game (includes random spawn)
	public Asteroid(int aSize){
		super();
		size = aSize;
		
		float bigCoords[] = {
	            // X, Y, Z
	            -0.2f, -0.2f, 0,
	             -0.2f,  0.2f, 0,
	             0.2f, 0.2f, 0,
	            0.2f, -0.2f, 0,
	            -0.2f, -0.2f, 0
	        };
		float mediumCoords[] = {
	            // X, Y, Z
	            -0.1f, -0.1f, 0,
	             -0.1f,  0.1f, 0,
	             0.1f, 0.1f, 0,
	            0.1f, -0.1f, 0,
	            -0.1f, -0.1f, 0
	        };
		
		if(size == 2)
		setCoords(bigCoords);
		
		if(size == 1)
		setCoords(mediumCoords);	
		
		setDrawMode(GL10.GL_LINE_STRIP);
		
		
		position = new Vector2f();
		spawnPosition();
			
		thrust.y = (float) ((2*Math.random()-1)/10);
		thrust.x = (float) ((2*Math.random()-1)/10);
		
		angle = 0;
	}
	
	//for asteroids created DURING the game (includes defined set spawn)
	public Asteroid(int aSize, float posX, float posY)
	{
		super();
		size = aSize;
		
		float bigCoords[] = {
	            // X, Y, Z
	            -0.2f, -0.2f, 0,
	             -0.2f,  0.2f, 0,
	             0.2f, 0.2f, 0,
	            0.2f, -0.2f, 0,
	            -0.2f, -0.2f, 0
	        };
		float mediumCoords[] = {
	            // X, Y, Z
	            -0.1f, -0.1f, 0,
	             -0.1f,  0.1f, 0,
	             0.1f, 0.1f, 0,
	            0.1f, -0.1f, 0,
	            -0.1f, -0.1f, 0
	        };
		
		if(size == 2)
		setCoords(bigCoords);
		
		if(size == 1)
		setCoords(mediumCoords);	
		
		setDrawMode(GL10.GL_LINE_STRIP);
				
		position = new Vector2f();
		position.x = posX;
		position.y = posY;
			
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
	
	public void spawnPosition()
	{
		//Decide whether the asteroid spawns on the left or right side
		int side = random.nextInt(2);
		if(side == 0)
			position.x = -random.nextFloat() - 2;
		else
			position.x = random.nextFloat() + 2;
		
		position.y = random.nextFloat()*4 - 2;
		
		System.out.println("position.x = " + position.x);
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
