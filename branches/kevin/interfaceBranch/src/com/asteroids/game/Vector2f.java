package com.asteroids.game;

public class Vector2f {
	public float x;
	public float y;
	public Vector2f(){
		x = 0.0f;
		y = 0.0f;
	}
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "x = " + x + " y = " + y;
	}
}
