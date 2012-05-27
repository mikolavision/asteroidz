package com.asteroids.game;

import java.util.Vector;

import android.graphics.Point;

public interface Flyable {

	public Vector2f thrust = new Vector2f(0.00001f, 0.00001f);
	
	public void update();
	
	public boolean collidesWith(Flyable obj);
	
	public void explode();
	
}
