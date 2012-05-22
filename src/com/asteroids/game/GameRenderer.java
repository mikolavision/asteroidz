package com.asteroids.game;

import java.nio.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;
import android.view.Display;

public class GameRenderer implements Renderer {

	private float SCREEN_WIDTH = 3.5f;
	private float SCREEN_HEIGHT = 2.5f;
	
	public float mAngle;
	public float thrustX, thrustY;
	public float posX, posY;
	public boolean isPressed = false;
	
	private FloatBuffer triangleVB;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//Set the background frame color
		gl.glClearColor(0f, 0f, 0f, 1.0f);

		initShapes();
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		
		//Since we are in 2D there is no need for depth
		gl.glDisable(GL10.GL_DEPTH_TEST);
		
		// Redraw background color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0, 0, 0, 0);
		
		//Set GL_MODELVIEW transfomration mode
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();	// reset the matrix to it's default state
		
		//When using GL_MODELVIEW, you must set the view point
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		
		// Use the mAngle member as the rotation value
		findPosition();
		gl.glTranslatef(posX, posY, 0.0f);
        gl.glRotatef(mAngle, 0.0f, 0.0f, 1.0f); 
        System.out.printf("Angle: %f\n ThrustX: %f\n ThrustY: %f\n", mAngle, thrustX, thrustY);
		
		//Draw the triangle
		gl.glColor4f(0.9f, 0.9f, 0.9f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, triangleVB);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 3);
		
	
		
	}
	
	public void findPosition()
	{
		//find the position of the ship based on velocity and acceleration
		if(isPressed)
		{
			//convert polar coordinates to Cartesian 
			thrustY += .01*Math.cos(mAngle*Math.PI/180);
			thrustX += -.01*Math.sin(mAngle*Math.PI/180);
		}
		
		posY += thrustY;
		posX += thrustX;
		
		//Flip the sip to the opposite side if it goes off-screen
		if(Math.abs(posX) >= SCREEN_WIDTH)
			posX = -posX;
		
		if(Math.abs(posY) >= SCREEN_HEIGHT)
			posY = -posY;
		System.out.printf("PosX: %f\n posY: %f\n", posX, posY);
	}

	@Override
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
            -0.5f, -0.25f, 0,
             0.5f, -0.25f, 0,
             0.0f,  0.559016994f, 0
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
