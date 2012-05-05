package com.asteroids.game;

import com.asteroids.game.GameSurfaceView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AsteroidzActivity extends Activity {
    
	private GLSurfaceView gameGLView;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
    }
    
    public void playGame(View view){
    	// load game activity
    	//poop
    	//Create a GLSurfaceView instance and set it
        //as the ContentView for this activity.
        gameGLView = new GameSurfaceView(this);
        setContentView(gameGLView);
    }
    
    public void exit(View view){
    	finish();
        System.exit(0);
    }
}