package com.asteroids.game;

import javax.microedition.khronos.opengles.GL10;

public class Bullet extends Drawable implements Flyable {

	public Vector2f position; 
	public Vector2f thrust = new Vector2f(0.00001f, 0.00001f);
	protected float angle;
	public boolean active = true;
	private float SPEED = 5.0f;
	
	public Bullet(float x, float y, float bAngle){
		super();
		float coords[] = {
	            // X, Y, Z
				0.0f,0.0f,0.0f,
				0.0f,0.1f,0.0f
	        };
		setCoords(coords);
		setDrawMode(GL10.GL_LINE_STRIP);

		position = new Vector2f(x, y);
		
		angle = (float) (bAngle - Math.PI/2);
		
		setThrust();
	}
		
	
	@Override
	public void update() {
		//thrust
		//position.x += thrust.x * SPEED;
		position.y += thrust.y * SPEED; 
		
		//Flip the bullet to the opposite side if it goes off-screen
		if(Math.abs(position.x) >= GameRenderer.SCREEN_WIDTH)
			position.x = -position.x;
		
		if(Math.abs(position.y) >= GameRenderer.SCREEN_HEIGHT)
			position.y = -position.y;
		
	}
	
	public void setThrust()
	{
		thrust.x += -.01*Math.sin(angle*Math.PI/180);
    	thrust.y += .01*Math.cos(angle*Math.PI/180);
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

	@Override
	public Vector2f getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public float getAngle(){
		return angle;
	}

}
