package com.atomicracers.ricracers.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EmergencyLamp extends Obstacle
{
	
	private Texture emergencyLamp;
	private Vector2 posEmergencyLamp;
	
	
	
	public EmergencyLamp(int x, int y)
	{
		emergencyLamp = new Texture("emergencyLamp.png");
		
		posEmergencyLamp = new Vector2(x, y);
		
		bounds = new Rectangle(x, y, 10, 105); 
	}
	
	
	
	public Texture getEmergencyLamp()
	{
		return emergencyLamp;
	}
	
	public Vector2 getPosEmergencyLamp()
	{
		return posEmergencyLamp;
	}
	
	public void reposition(float x , float y)
	{
		posEmergencyLamp.set(x, y);
		bounds.setPosition(posEmergencyLamp.x, posEmergencyLamp.y);
	}
	
	
	public boolean collides(Rectangle player)
	{
		return player.overlaps(bounds);
	}
}