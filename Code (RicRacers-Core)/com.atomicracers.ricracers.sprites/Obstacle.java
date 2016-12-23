package com.atomicracers.ricracers.sprites;

import com.badlogic.gdx.math.Rectangle;


// Abhi Edwin Richard
public abstract class Obstacle 
{
	protected Rectangle bounds;
    
    
	public abstract void reposition(float x , float y);
	
		
	public abstract boolean collides(Rectangle player);
	
		
	
}


