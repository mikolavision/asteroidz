package com.asteroids.game;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Title extends Drawable{
	
	int t;

	private FloatBuffer fb_a;
	private FloatBuffer fb_s;
	private FloatBuffer fb_t;
	private FloatBuffer fb_e;
	private FloatBuffer fb_r;
	private FloatBuffer fb_o;
	private FloatBuffer fb_i;
	private FloatBuffer fb_d;
	private FloatBuffer fb_z;
	
	private Vector2f[] newA;
	private Vector2f[] random;
	
	public Title(){
		t = 0;
		random = new Vector2f[9];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				
		newA = new Vector2f[2];
		newA[0] = new Vector2f(0.0f, 0.5f);
		newA[1] = new Vector2f(0.0f, 0.5f);
		createLetters();
	}
	
	public void updateLetters(int t){
		this.t = t;
	}
	
	public void createLetters(){
		float[] _a = {
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f
		};
		float[] _s = {
				0.5f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.25f, 0.0f,
				0.5f, 0.25f, 0.0f,
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.0f
		};
		float[] _t = {
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.25f, 0.5f, 0.0f,
				0.25f, 0.0f, 0.0f
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
				0.0f, 0.5f, 0.0f
		};
		float[] _i = {
				0.25f, 0.0f, 0.0f,
				0.25f, 0.5f, 0.0f
		};
		float[] _I = {
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.25f, 0.0f, 0.0f,
				0.25f, 0.5f, 0.0f,
				0.5f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f
		};
		float[] _d = {
				0.5f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.5f, 0.5f, 0.0f
		};
		float[] _z = {
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.5f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f
		};
		fb_a = createBuffer(_a);
		fb_s = createBuffer(_s);
		fb_t = createBuffer(_t);
		fb_e = createBuffer(_e);
		fb_r = createBuffer(_r);
		fb_o = createBuffer(_o);
		fb_i = createBuffer(_I);
		fb_d = createBuffer(_d);
		fb_z = createBuffer(_z);
	}
		
	@Override
	public void glDraw(GL10 gl, float x, float y){
		
		gl.glColor4f(0.9f, 0.9f, 0.9f, 0.0f);

		gl.glPushMatrix();
		gl.glTranslatef(x, y, 0.0f);

		gl.glScalef(1.0f + (float)Math.cos(t*.1f)*.03f, 1.0f + (float)Math.cos(t*.1f)*.03f, 1.0f);
		
		//A
		gl.glPushMatrix();
		gl.glTranslatef(2.4f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_a);
		gl.glDrawArrays(GL10.GL_LINES, 0, 4);
		gl.glPopMatrix();
		
		//S
		gl.glPushMatrix();
		gl.glTranslatef(1.8f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_s);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 6);
		gl.glPopMatrix();

		//T
		gl.glPushMatrix();
		gl.glTranslatef(1.2f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_t);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 4);
		gl.glPopMatrix();

		//E
		gl.glPushMatrix();
		gl.glTranslatef(0.6f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_e);
		gl.glDrawArrays(GL10.GL_LINES, 0, 6);
		gl.glPopMatrix();

		//R
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_r);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 6);
		gl.glPopMatrix();
		
		//O
		gl.glPushMatrix();
		gl.glTranslatef(-0.6f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_o);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 4);
		gl.glPopMatrix();

		//I
		gl.glPushMatrix();
		gl.glTranslatef(-1.2f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_i);
		gl.glDrawArrays(GL10.GL_LINES, 0, 6);
		gl.glPopMatrix();

		//D
		gl.glPushMatrix();
		gl.glTranslatef(-1.8f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_d);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 4);
		gl.glPopMatrix();

		//D
		gl.glPushMatrix();
		gl.glTranslatef(-2.4f, 0.5f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_z);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 4);
		gl.glPopMatrix();
		
		
		gl.glPopMatrix();
	}
	
}
