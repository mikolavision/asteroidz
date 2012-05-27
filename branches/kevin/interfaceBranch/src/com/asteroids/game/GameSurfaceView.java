package com.asteroids.game;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameSurfaceView extends GLSurfaceView{

    private float ROTATE_SCALE_SPEED = 1.5f;
    private GameRenderer mRenderer;

    
    // sensor manager used to control the accelerometer sensor.
    private SensorManager sensorManager;
    // accelerometer sensor values.
    private float mAccelX = 0;
    private float mAccelY = 0;
    
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

    
	    // register the accelerometer so we can retrieve values
	    sensorManager = (SensorManager) activity.getSystemService("sensor");
	    sensorManager.registerListener(accelerometerSensor, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	    
	    //Display display = activity.getWindowManager().getDefaultDisplay();
	    //mRenderer.SCREEN_HEIGHT = display.getWidth();
	    //mRenderer.SCREEN_WIDTH = display.getHeight();
	    
		mRenderer = new GameRenderer();
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
		switch (e.getAction()) {
        case MotionEvent.ACTION_DOWN:
                mRenderer.isPressed = true;
                break;
        
        case MotionEvent.ACTION_UP:
                mRenderer.isPressed = false;
                break;
                }
		
		// pass the event on to the render
		mRenderer.onTouch(e);
		
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
