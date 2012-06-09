package com.asteroids.game;

import java.nio.*;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

public class GameRenderer implements Renderer {

	public static float SCREEN_WIDTH = 3.5f;
	public static float SCREEN_HEIGHT = 2.5f;
	public static int numAsteroids = 20;
	public float mAngle;
	public static boolean isPressed = false;
	public boolean isIterating = false;
	public boolean bulletWaiting = false;
	
	public Ship player;
	public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>(); 
	public ArrayList<Asteroid> asteroidsWaiting = new ArrayList<Asteroid>(); 
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	private FloatBuffer triangleVB;
	private FloatBuffer starVB;
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//Set the background frame color
		gl.glClearColor(0f, 0f, 0f, 1.0f);

		initShapes();
		intitialize();
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		
	}
	
	public void intitialize() {

		//create the player
		player = new Ship();
		
		//create a list of asteroids
		for(int i=0; i < numAsteroids; i++)
		{
			asteroids.add(new Asteroid(2));
		}
		
		
	}

	public void onDrawFrame(GL10 gl) {
		
	
		
		// Redraw background color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0, 0, 0, 0);
		
		//Set GL_MODELVIEW transfomration mode
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();	// reset the matrix to it's default state
		
		//When using GL_MODELVIEW, you must set the view point
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		
		
		
		
		// So far all the code we need to get the ship class working...
		player.setAngle(mAngle);
		player.update();
		player.glDraw(gl, player.getPosition(), player.getAngle());
		
		
		//update the asteroids
		for(Asteroid asteroid : asteroids)
		{
			if(asteroid.active)
			{
				asteroid.update();
				asteroid.glDraw(gl, asteroid.getPosition(), asteroid.getAngle());
			}
		}
		
		//update the active bullets
		isIterating = true;
		for(Bullet bullet : bullets)
		{
			if(bullet.active)
			{
				bullet.update();
				
				//check for collisions between the bullets and asteroids
				for(Asteroid asteroid : asteroids)
					if(bullet.collidesWith(asteroid) && asteroid.active)
					{
						System.out.println("Collision!!!!!1");
						bullet.active = false;
						asteroid.active = false;
						
						if(asteroid.size == 2)
						{
							asteroidsWaiting.add(new Asteroid(1,asteroid.position.x, asteroid.position.y));
							asteroidsWaiting.add(new Asteroid(1,asteroid.position.x, asteroid.position.y));
						}
					}
				
				bullet.glDraw(gl, bullet.position, bullet.getAngle());	
			}
		}
		
		//add the asteroids on the waiting list to the main asteroid lists
		asteroids.addAll(asteroidsWaiting);
		asteroidsWaiting.clear();
		
		isIterating = false;
		
		if(bulletWaiting)
			playerShoot();
		bulletWaiting = false;
	
	}
		
	//handle creating or reusing player bullets
	public void playerShoot()
	{
		if(!isIterating)
		{	
			boolean createNew = true;
			for(Bullet bullet : bullets)
			{
				if(bullet.active == false)
				{
					createNew = false;
					bullet.position.x = player.position.x;
					bullet.position.y = player.position.y;
					bullet.active = true;
					bullet.angle = (float) (mAngle - Math.PI/2);
					bullet.setThrust();
					bullet.startTime = System.currentTimeMillis();
					break;
					
				}
			}
			
			//If there are no inactive bullets, create a new one.
			if(createNew)
			{
				bullets.add(new Bullet(player.position.x, player.position.y, mAngle));
				System.out.println("created bullet");
			}
		}
		else
			bulletWaiting = true;
	}
	


	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		
		//make adjustments for screen ratio
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);		//set the matrix to projetion mode
		gl.glLoadIdentity();						//reset the matrix to its default state
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);	//apply the projection matrix
	}
	
	private void initShapes(){
	    
        float triangleCoords[] = {
            // X, Y, Z
            -0.5f/3f, -0.25f/2, 0,
             0.0f,  0.559016994f/2, 0,
             0.5f/3f, -0.25f/2, 0,
             0.0f, -0.0f, 0,
            -0.5f/3f, -0.25f/2, 0
        }; 
        
        // initialize vertex Buffer for triangle  
        ByteBuffer vbb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                triangleCoords.length * 4); 
        vbb.order(ByteOrder.nativeOrder());// use the device hardware's native byte order
        triangleVB = vbb.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
        triangleVB.put(triangleCoords);    // add the coordinates to the FloatBuffer
        triangleVB.position(0);            // set the buffer to read the first coordinate
        
    }

}
