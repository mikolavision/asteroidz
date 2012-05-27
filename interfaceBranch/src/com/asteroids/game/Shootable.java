/**
 * 
 */
package com.asteroids.game;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * @author Kevin
 *
 */
public interface Shootable {
	
	/**
	 * Takes in an ArrayList of Bullets that the object can take bullets from
	 * @param bulletList
	 */
	public void Bullet(ArrayList bulletList);
	
	/**
	 * Adds a Bullet to the ArrayList and shoots it
	 * @param gl
	 */
	public void shoot(GL10 gl);

}
