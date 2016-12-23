package com.atomicracers.ricracers.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bench extends Obstacle
{
	private Texture bench;
	private Vector2 posBench;
	
	public Bench(int x, int y, String imageName)
	{
		bench = new Texture(imageName);
		
		posBench = new Vector2(x, y);
		
		bounds = new Rectangle(x, y, 20, 50);
		
	}
	
	
	public Texture getBench()
	{
		return bench;
	}
	
	public Vector2 getPosBench()
	{
		return posBench;
	}
	
	public void reposition(float x , float y)
	{
		posBench.set(x, y);
		bounds.setPosition(posBench.x, posBench.y);
	}
	
	
	public boolean collides(Rectangle player)
	{
		return player.overlaps(bounds);
	}
	
	
	
}
