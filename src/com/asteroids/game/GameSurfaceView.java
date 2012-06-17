package com.asteroids.game;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameSurfaceView extends GLSurfaceView{

    private float ROTATE_SCALE_SPEED = 1.9f;
    private GameRenderer mRenderer;
    private AsteroidzActivity mActivity;
    public Context context;
    
    // sensor manager used to control the accelerometer sensor.
    private SensorManager sensorManager;
    // accelerometer sensor values.
    private float mAccelX = 0;
    private float mAccelY = 0;
    
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = INVALID_POINTER_ID;
    private float lastX;
    private float lastY;
    
    private MediaPlayer mediaPlayer;
    
    private final SensorEventListener accelerometerSensor = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent event) {
			mAccelX = event.values[0];
			mAccelY = event.values[1];
			
			mRenderer.mAngle += mAccelY * ROTATE_SCALE_SPEED;
			requestRender();
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			//Not used
		}
	};
	
	
    
	public GameSurfaceView(Context context, Activity activity) {
		super(context);
		

		
		//Initialize media player
		mediaPlayer = MediaPlayer.create(context, R.raw.music);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
    
	    // register the accelerometer so we can retrieve values
	    sensorManager = (SensorManager) activity.getSystemService("sensor");
	    sensorManager.registerListener(accelerometerSensor, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	    
	    //Display display = activity.getWindowManager().getDefaultDisplay();
	    //mRenderer.SCREEN_HEIGHT = display.getWidth();
	    //mRenderer.SCREEN_WIDTH = display.getHeight();
	    
		mRenderer = new GameRenderer(context);
		setRenderer(mRenderer);
		
		// Set the Renderer for drawing on the GLSurfaceView
		// setRenderer(new AndroidzRenderer());
		
		// Render the view only when there is a change
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e){
		// MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
		
		//check to see if the user has a finger on the screen
		
		float x = e.getX();
		float y = e.getY();

		switch(GameRenderer.STATE){

		case MAIN_MENU:
			//If the user touches the screen
			switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//System.out.println("X,"+x+" Y,"+y);
				//if((x<0.5f&&x>-0.5f)&&(y<-0.1f&&y>-0.4f))
				GameRenderer.STATE = GameState.INITIALIZE;
				break;
			}
			break;//MAIN_MENU

		case GAME_OVER:
			//If the user touches the screen
			switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//if((x<0.5f&&x>-0.5f)&&(y<-0.1f&&y>-0.4f))
				GameRenderer.STATE = GameState.MAIN_MENU; 
				break;
			}
			break;//MAIN_MENU

		case PLAYING:
			switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if(x<400){
					mRenderer.isPressed = false;
					x = e.getX();
					y = e.getY();
					
					lastX = x;
					lastY = y;
					
					// Save the ID of this pointer
					
					mRenderer.playerShoot();
				}else if(x>=400){
					mRenderer.isPressed = true;
				}
				break;
			case MotionEvent.ACTION_UP:
				mRenderer.isPressed = false;
				break;
			}

			break;//PLAYING
 }
		     
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
}
