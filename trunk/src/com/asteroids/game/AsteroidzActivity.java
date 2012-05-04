package com.asteroids.game;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AsteroidzActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
       //android:screenOrientation="landscape" - in manifest - JB
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
    }
    
    public void playGame(View view){
    	// load game activity
    }
    
    public void exit(View view){
    	finish();
        System.exit(0);
    }
}