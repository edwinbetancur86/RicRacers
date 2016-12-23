package com.atomicracers.ricracers.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bush extends Obstacle
{
	private Texture bush;
	private Vector2 posBush;
	private Rectangle bounds;
	
	public Bush(int x, int y)
	{
		bush = new Texture("bush20.png");
		
		posBush = new Vector2(x, y);
		
		bounds = new Rectangle(x, y, 42, 39); // 42, 39
	}
	
	public Texture getBush()
	{
		return bush;
	}
	
	
	public Vector2 getPosBush()
	{
		return posBush;
	}
	
	public void reposition(float x , float y)
	{
		posBush.set(x, y);
		bounds.setPosition(posBush.x, posBush.y);
	}
	
	
	public boolean collides(Rectangle player)
	{
		return player.overlaps(bounds);
	}
}
