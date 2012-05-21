package com.asteroids.game;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEventSource;

public class GameSurfaceView extends GLSurfaceView{

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float ROTATE_SCALE_SPEED = 1.5f;
    private GameRenderer mRenderer;
    private float mPreviousX;
    private float mPreviousY;
    
    // sensor manager used to control the accelerometer sensor.
    private SensorManager sensorManager;
    // accelerometer sensor values.
    private float mAccelX = 0;
    private float mAccelY = 0;
    private Sensor accelerometer;
    
    private final SensorEventListener accelerometerSensor = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent event) {
			System.out.printf("Movement! X: %f Y: %f Z:%f\n", event.values[0],
															  event.values[1],
															  event.values[2]);
			mAccelX = event.values[0];
			mAccelY = event.values[1];
			
			mRenderer.mAngle += (mAccelY) * ROTATE_SCALE_SPEED;
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
		
		/* this code is not used anymore
		 * float x = e.getX();
        float y = e.getY();
        System.out.println("TOUCH");
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:

            float dx = x - mPreviousX;
            float dy = y - mPreviousY;

            // reverse direction of rotation above the mid-line
            if (y > getHeight() / 2) {
              dx = dx * -1 ;
            }

            // reverse direction of rotation to left of the mid-line
            if (x < getWidth() / 2) {
              dy = dy * -1 ;
            }
          
            // mRenderer.mAngle += (mAccelY) * TOUCH_SCALE_FACTOR;
            //requestRender();
    	}

        mPreviousX = x;
        mPreviousY = y; */
		
		switch (e.getAction()) {
        case MotionEvent.ACTION_DOWN:
        	mRenderer.isPressed = true;
        	break;
        
        case MotionEvent.ACTION_UP:
        	mRenderer.isPressed = false;
        	break;
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
