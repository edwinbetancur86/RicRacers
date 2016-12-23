// EDWIN'S NOTES: EDITED FOR COLLISION DETECTION

package com.atomicracers.ricracers.sprites;

import java.util.Random;

import com.atomicracers.ricracers.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
/** 
 * This class represents a buggie. It includes methods to process movement, player input, and it returns its position in the world
 * @author Richard
 * Will need to make a superclass that this will inherit from. 
 */
public class Buggie 
{
	private float movement = (float) 300.0; 		//sets the speed of the game which is the movement of the buggie. 
	private float PLAYERDONEMOVEMENT = (float) 90.0; //How much additional movement is added when a player does something.
	private final int INNER_BOUND_SUB = -13; 		// The left over amount of left part of the width of the screen for boundary checking
	private final int OUTER_BOUND_SUB = 50; 		 // The amount to subtract from the right width part of the screen for boundary checking 
	private Texture buggieTopDown;
	private Vector3 position;						//the buggie's position. 
	private Vector3 velocity;
	private int playerX, playerY;
	private Rectangle bounds;
	private int healthBar;
	
	
	
	public Buggie(int x, int y)
	{
		position = new Vector3(x, y, 0);
		velocity = new Vector3(0,0,0);
		buggieTopDown = new Texture("buggietopdown11.png");
		// Create a new bounds rectangle
		bounds = new Rectangle(x, y, 
				buggieTopDown.getWidth(), buggieTopDown.getHeight());
		healthBar = 100;
		
	}
	
	/** This updates the position of the buggie. It is also what gives it movement*/
	public void update(float dt)
	{
		velocity.scl(dt);
		position.add(playerX+velocity.x+playerX, (movement*dt)+velocity.y , 0);
		bounds.setPosition(position.x, position.y);
		
		if (position.x < INNER_BOUND_SUB)
		{
			position.x = INNER_BOUND_SUB;
		}
		
		if (position.x > Game.WIDTH - OUTER_BOUND_SUB)
		{
			position.x = Game.WIDTH - OUTER_BOUND_SUB;
		}
		
	}
	
	/** This returns the buggies position in the gameworld*/
	public Vector3 getPosition()
	{
		return position;
	}
	
	 
	
	/** This just returns the texture of the buggie. May need to rename so it will work with a superclass*/
	public Texture getBuggieTexture()
	{
		return buggieTopDown;
	}
	
	/** This allows the player to move the buggie in different directions. Up and down move the camera and left and right moves the buggie*/
	public int moveBuggie(int camPosition, float dt){
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)){
			playerY += PLAYERDONEMOVEMENT * dt;
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			playerY -= PLAYERDONEMOVEMENT * dt;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			
			playerX -= PLAYERDONEMOVEMENT * dt;
			
		}
		
	     if(Gdx.input.isKeyPressed(Keys.RIGHT)){
	    	 
			playerX += PLAYERDONEMOVEMENT * dt;
			
		}
	     
		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)){ //This will cancel out the left or right movement of the buggie.
			playerX = 0;
		}
		camPosition = playerY;
		return camPosition;
		
	}
	
	public void stopBuggie()
	{
		movement = 0;
		PLAYERDONEMOVEMENT = 0;
	}
	

	public void reposition(float x, float y, float z)
	{
		position.set(x, y, z); 
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}

	public void updateMovement(boolean contin){
		
		if(contin == true)
		{
			movement = (float) (movement+ 0.1);
		}
	}
	
	public void removeHealthBar(float decemmentCount)
	{
		/*while (healthBar > 0)
		{*/
			healthBar -= decemmentCount;
		/*}*/
	}
	
	public void removeHealthCompletely()
	{
		healthBar = 0;
	}
	
	public String toStringHealthBar(){
		return "Health: %" + healthBar;
	}
	public void stopUserInput()
	{
		playerX = 0;
		playerY = 0;
		
	}
	
	public int getBuggieHealth()
	{
		return healthBar;
	}
}
