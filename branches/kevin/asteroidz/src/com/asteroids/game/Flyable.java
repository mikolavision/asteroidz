/**
 * 
 */
package com.asteroids.game;

/**
 * @author Kevin
 *
 */
public interface Flyable {

	/**
	 * The main function that will be called each frame
	 */
	public void update();
	
	/**
	 * returns the velocity of the moving object
	 * @return float velocity
	 */
	public float getVelocity();
	
	/**
	 * Checks to see if this object collides with another object
	 * @param obj
	 * @return True if there is a collision, false if not
	 */
	public boolean collidesWith(Flyable obj);
	
	/**
	 * Destroys the object in a cliche fashion
	 */		
	public void explode ();
}
