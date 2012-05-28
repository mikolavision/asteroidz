package com.asteroids.game;

import javax.microedition.khronos.opengles.GL10;

public class Bullet extends Drawable implements Flyable {

	public Vector2f position;
	public Vector2f thrust = new Vector2f(0.00001f, 0.00001f);
	protected float angle;
	
	public Bullet(){
		super();
		float coords[] = {
	            // X, Y, Z
				0.0f,0.0f,0.0f,
				0.0f,0.3f,0.0f
	        };
		setCoords(coords);
		setDrawMode(GL10.GL_LINE_STRIP);
		
		position = new Vector2f();
		position.x = 0;
		position.y = 0;
		
		angle = 0;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
