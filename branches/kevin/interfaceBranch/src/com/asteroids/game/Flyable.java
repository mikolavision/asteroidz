package com.asteroids.game;

import java.util.Vector;

import android.graphics.Point;

public interface Flyable {


	
	public void update();
	
	public boolean collidesWith(Flyable obj);
	
	public void explode();
	
	public Vector2f getPosition();
	
	
}
