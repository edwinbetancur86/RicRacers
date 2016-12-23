package com.atomicracers.ricracers.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TrashCan extends Obstacle
{
	private Texture trashCan;
	private Vector2 posTrashCan;
	
	public TrashCan(int x, int y)
	{
		trashCan = new Texture("TrashCan.png");
		
		posTrashCan = new Vector2(x, y);
		
		bounds = new Rectangle(x, y, 20, 50);
	}
	
	
	public Texture getTrashCan()
	{
		return trashCan;
	}
	
	public Vector2 getPosTrashCan()
	{
		return posTrashCan;
	}
	
	public void reposition(float x , float y)
	{
		posTrashCan.set(x, y);
		bounds.setPosition(posTrashCan.x, posTrashCan.y);
	}
	
	
	public boolean collides(Rectangle player)
	{
		return player.overlaps(bounds);
	}
}
