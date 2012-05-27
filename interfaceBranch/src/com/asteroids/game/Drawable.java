package com.asteroids.game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;

public abstract class Drawable {
	
	private float coords[];
	private FloatBuffer fbuffer;
	private int DrawMode;
	public Vector2f position;
	protected float angle;
	
	public Drawable(){
		initializeBuffer();
		setDrawMode(GL10.GL_LINES);
		position = new Vector2f();
		position.x = 0;
		position.y = 0;
		angle = 0;
	}
	
	/**
	 * set the draw mode when drawing from a buffer
	 * set to GL_LINES by default
	 * @param gl10_drawMode
	 */
	public void setDrawMode(int gl10_drawMode) {
		DrawMode = gl10_drawMode;
	}
	
	public int getDrawMode(){
		return DrawMode;
	}

	private void initializeBuffer(){
		// Coordinates are initialized with 5 points for
		// the shape of the ship
		float icoords[] = {
				// X, Y, Z
	            -0.5f/3f, -0.25f/2, 0,
	             0.0f,  0.559016994f/2, 0,
	             0.5f/3f, -0.25f/2, 0,
	             0.0f, -0.0f, 0,
	            -0.5f/3f, -0.25f/2, 0};
		coords = icoords;
		fbuffer = createBuffer(icoords);
	}
	
	private FloatBuffer createBuffer(float coords[]){
		FloatBuffer fb;
		ByteBuffer vbb = ByteBuffer.allocateDirect(
				// (# of coordinate values * 4 bytes per float)
				coords.length * 4);
		vbb.order(ByteOrder.nativeOrder());// use the device hardware's native byte order
		fb = vbb.asFloatBuffer();          // create a floating point buffer from the ByteBuffer
		fb.put(coords);                    // add the coordinates to the FloatBuffer
		fb.position(0);                    // set the buffer to read the first coordinate
		return fb;
	}

	public FloatBuffer getBuffer(){
		return fbuffer;
	}
	
	public int getBufferSize(){
		return (int) coords.length / 3;
	}
	
	public float[] getCoords(){
		return coords;
	}
	
	public void setCoords(float[] coords){
		if(coords.length>0) this.coords = coords;
		fbuffer = createBuffer(coords);
	}
	
	public Vector2f getPosition(){
		return position;
	}
	
	public void glDraw(GL10 gl){
		gl.glPushMatrix();
		
		gl.glColor4f(0.9f, 0.9f, 0.9f, 0.0f);
		gl.glTranslatef(position.x, position.y, 0.0f);
		gl.glRotatef(angle, 0, 0, 1);
		FloatBuffer vb = getBuffer();
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vb);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, getBufferSize());
		
		gl.glPopMatrix();
	
	}
	
}
