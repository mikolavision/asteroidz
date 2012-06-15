package com.asteroids.game;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Explosion extends Drawable {
	
	private FloatBuffer fb_line;
	public float angle = 0;
	public boolean active = false;
	public long startTime;
	public Vector2f position;
	public float scale = 1;
	
	public Explosion()
	{
		float[] _line = {
				-0.1f, 0.0f, 0.0f,
				0.1f, 0.0f, 0.0f
		};
		
		fb_line = createBuffer(_line);
		
		position = new Vector2f();
		
	}
	
	public void update()
	{
		
		angle += 10;
		scale += 0.05f;
		
		//let the explosion last for 3 seconds
		if(System.currentTimeMillis() - startTime > 2000)
			active = false;
		
			
	}
	
	public void glDraw(GL10 gl){
			
			gl.glColor4f(0.9f, 0.9f, 0.9f, 0.0f);
	
			gl.glPushMatrix();
			gl.glTranslatef(position.x, position.y, 0.0f);
			gl.glScalef(scale, scale, 0);
			
			//first line
			gl.glPushMatrix();
			gl.glTranslatef(0.1f, 0.1f, 0.0f);
			gl.glRotatef(angle ,0 ,0 ,1);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_line);
			gl.glDrawArrays(GL10.GL_LINES, 0, 2);
			gl.glPopMatrix();
			
			//second line
			gl.glPushMatrix();
			gl.glTranslatef(-0.1f, 0.1f, 0.0f);
			gl.glRotatef(angle ,0 ,0 ,1);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_line);
			gl.glDrawArrays(GL10.GL_LINES, 0, 2);
			gl.glPopMatrix();
			
			//second line
			gl.glPushMatrix();
			gl.glTranslatef(0.0f, -0.1f, 0.0f);
			gl.glRotatef(angle ,0 ,0 ,1);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb_line);
			gl.glDrawArrays(GL10.GL_LINES, 0, 2);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
	}
	
}
