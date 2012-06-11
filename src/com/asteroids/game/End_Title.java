package com.asteroids.game;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class End_Title extends Drawable {

	private FloatBuffer fb_a;
	private FloatBuffer fb_e;
	private FloatBuffer fb_r;
	private FloatBuffer fb_o;
	private FloatBuffer fb_g;
	private FloatBuffer fb_m;
	private FloatBuffer fb_v; 
	
	public End_Title(){
		
		float[] _a = {
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.0f
		};
		float[] _g = {
				0.13f, 0.5f, 0.0f,
				0.45f, 0.5f, 0.0f,
				0.45f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.25f, 0.0f,
				0.25f, 0.25f, 0.0f
		};
		float[] _m = {
				0.0f, 0.0f, 0.0f,
				0.12f, 0.48f, 0.0f,
				0.25f, 0.2f, 0.0f,
				0.37f, 0.48f, 0.0f,
				0.5f, 0.0f, 0.0f
		};
		float[] _e = {
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.5f, 0.25f, 0.0f,
				0.0f, 0.25f, 0.0f,
				0.5f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f
		};
		float[] _r = {
				0.5f, 0.0f, 0.0f,
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.25f, 0.0f,
				0.5f, 0.25f, 0.0f,
				0.0f, 0.0f, 0.0f
		};
		float[] _o = {
				0.0f, 0.0f, 0.0f,
				0.5f, 0.0f, 0.0f,
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.0f,
		};
		float[] _v = {
				0.0f, 0.5f, 0.0f,
				0.25f, 0.0f, 0.0f,
				0.5f, 0.5f, 0.0f
			

		};
		fb_a = createBuffer(_a);
		fb_g = createBuffer(_g);
		fb_m = createBuffer(_m);
		fb_v = createBuffer(_v);
		fb_e = createBuffer(_e);
		fb_r = createBuffer(_r);
		fb_o = createBuffer(_o);
	
	}
	
	@Override
	public void glDraw(GL10 gl, float x, float y){
		
		gl.glColor4f(0.9f, 0.9f, 0.9f, 0.0f);

		gl.glPushMatrix();
		gl.glTranslatef(x, y, 0.0f);
		//G
		gl.glPushMatrix();
		gl.glTranslatef(2.4f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_g);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 6);
		gl.glPopMatrix();
		
		//A
		gl.glPushMatrix();
		gl.glTranslatef(1.8f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_a);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 3);
		gl.glPopMatrix();

		//M
		gl.glPushMatrix();
		gl.glTranslatef(1.2f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_m);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 5);
		gl.glPopMatrix();

		//E
		gl.glPushMatrix();
		gl.glTranslatef(0.6f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_e);
		gl.glDrawArrays(GL10.GL_LINES, 0, 6);
		gl.glPopMatrix();

		//O
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_o);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 4);
		gl.glPopMatrix();
		
		//V
		gl.glPushMatrix();
		gl.glTranslatef(-0.6f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_v);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 3);
		gl.glPopMatrix();

		//E
		gl.glPushMatrix();
		gl.glTranslatef(-1.2f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_e);
		gl.glDrawArrays(GL10.GL_LINES, 0, 6);
		gl.glPopMatrix();

		//R
		gl.glPushMatrix();
		gl.glTranslatef(-1.8f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_r);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 6);
		gl.glPopMatrix();

		
	}
}
