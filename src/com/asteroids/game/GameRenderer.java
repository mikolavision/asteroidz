package com.asteroids.game;

import java.nio.*;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

public class GameRenderer implements Renderer {

	public static float SCREEN_WIDTH = 3.5f;
	public static float SCREEN_HEIGHT = 2.5f;
	public float mAngle;
	public static boolean isPressed = false;
	public boolean isIterating = false;
	public boolean bulletWaiting = false;
	public int level = 1;
	
	//Main Menu
	public static GameState STATE = GameState.MAIN_MENU;
	public Button playButton;	
	public Title title;
	public End_Title endTitle;
	
	public Ship player;
	public Lives lives;
	public Explosion explosion;
	public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>(); 
	public ArrayList<Asteroid> asteroidsWaiting = new ArrayList<Asteroid>(); 
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	private FloatBuffer triangleVB;
	private FloatBuffer starVB;
	public Context context;
	private SoundManager mSoundManager;
	
	public GameRenderer(Context aContext)
	{
		context = aContext;
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//Set the background frame color
		gl.glClearColor(0f, 0f, 0f, 1.0f);

		STATE = GameState.MAIN_MENU;
		initializeMenu();
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		
	}
	
	public void intitialize() {

		//create the player
		player = new Ship();
		lives = new Lives();
		explosion = new Explosion();
		
		//Initialize sound
		mSoundManager = new SoundManager();
		mSoundManager.initSounds(context);
		mSoundManager.addSound(1, R.raw.fire);
		mSoundManager.addSound(2, R.raw.explosion); 
	
		newLevel();
		
	}
	
	//Start a new level
	public void newLevel()
	{
		//clear all array lists 
		asteroids.clear();
		asteroidsWaiting.clear();
		bullets.clear();
		lives.numLives = 3;
		
		//create a list of asteroids
		for(int i=0; i < level*2; i++)
		{
			asteroids.add(new Asteroid(2));
		}
		
	
		
	}
	

	public void onDrawFrame(GL10 gl) {
		
		
		switch(STATE){
			
		case MAIN_MENU:
			// Redraw background color
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			gl.glClearColor(0, 0, 0, 0);
			
			//Set GL_MODELVIEW transfomration mode
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();	// reset the matrix to it's default state
			
			//When using GL_MODELVIEW, you must set the view point
			GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
			
			//Draw objects
			title.glDraw(gl, -0.2f, 0.0f);
			playButton.glDraw(gl, 0.0f, 0.0f);
			
			break;//MAIN_MENU
			
		case GAME_OVER:
			// Redraw background color
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			gl.glClearColor(0, 0, 0, 0);
			
			//Set GL_MODELVIEW transfomration mode
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();	// reset the matrix to it's default state
			
			//When using GL_MODELVIEW, you must set the view point
			GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
			
			//reset lives
			lives.numLives = 3;
			
			//Draw objects
			endTitle.glDraw(gl, -0.4f, 0.0f);
			
			break;//GAME_OVER
			
		case INITIALIZE:

			initShapes();
			intitialize();
			
			STATE = GameState.PLAYING;
			break;//INITIALIZE
			
		case PLAYING:
			// Redraw background color
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			gl.glClearColor(0, 0, 0, 0);
			
			//Set GL_MODELVIEW transfomration mode
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();	// reset the matrix to it's default state
			
			//When using GL_MODELVIEW, you must set the view point
			GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
			
			
			//handle explosions
			if(explosion.active)
			{
				explosion.update();
				explosion.glDraw(gl);
			}
			else
				player.active = true;
			
			// So far all the code we need to get the ship class working...
			if(player.active)
			{
				player.setAngle(mAngle);
				player.update();
				player.glDraw(gl, player.getPosition(), player.getAngle());
			}
			else
				{
					player.position.x = 0;
					player.position.y = 0;
					player.thrust.x = 0;
					player.thrust.y = 0;
				}
			
			//player hud (now only displays number of lives) 
			for(int i = 0; i < lives.numLives; i++)
			lives.glDraw(gl, (float) (2 - 0.2*i), 1.3f, 0.0f);
			
			//update the asteroids and check if there are still active asteroids
			boolean stillAsteroids = false;
			for(Asteroid asteroid : asteroids)
			{
				if(asteroid.active)
				{
					asteroid.update();
					asteroid.glDraw(gl, asteroid.getPosition(), asteroid.getAngle());
					stillAsteroids = true;
					
					//check for collisions 
					if(player.collidesWith(asteroid) && player.active)
					{
						playerDie();
					}
				}
				
				
				
				
			}
			
			//If there are no more active asteroids, start the next level
			if(!stillAsteroids)
			{
				level++;
				newLevel();
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
							
							mSoundManager.playSound(2);
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
			
			
			//check to see if the player loses the game
			
			
			break;//PLAYING
			
		default:
			break;//default
			
		}
		
	}
	
	
	//do stuff when the player dies
	public void playerDie()
	{
		lives.numLives--;
		
		//create an explosion
		player.active = false;
		explosion.active = true;
		explosion.position.x = player.position.x;
		explosion.position.y = player.position.y;
		explosion.scale = 0;
		explosion.startTime = System.currentTimeMillis();
		
		if(lives.numLives <= 0)
			STATE = GameState.GAME_OVER;
	}
	
		
	//handle creating or reusing player bullets
	public void playerShoot()
	{
		if(player.active)
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
			
			mSoundManager.playSound(1);
		}
	}
	


	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		
		//make adjustments for screen ratio
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);		//set the matrix to projetion mode
		gl.glLoadIdentity();						//reset the matrix to its default state
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);	//apply the projection matrix
	}
	


	private void initializeMenu(){
		playButton = new Button();
		title = new Title();
		endTitle = new End_Title();
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
