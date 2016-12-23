package com.atomicracers.ricracers.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DirtBush extends Obstacle
{
	private Texture dirtBush;
	private Vector2 posDirtBush;
	
	public DirtBush(int x, int y)
	{
		dirtBush = new Texture("bush1.png");
		
		posDirtBush = new Vector2(x, y);
		
		bounds = new Rectangle(x, y, 10, 50); // 20, 50
	}
	
	public Texture getDirtBush()
	{
		return dirtBush;
	}
	
	public Vector2 getPosDirtBush()
	{
		return posDirtBush;
	}
	
	public void reposition(float x , float y)
	{
		posDirtBush.set(x, y);
		bounds.setPosition(posDirtBush.x, posDirtBush.y);
	}
	
	
	public boolean collides(Rectangle player)
	{
		return player.overlaps(bounds);
	}
}
