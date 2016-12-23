package com.atomicracers.ricracers.states;
import com.atomicracers.ricracers.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;
/**
 * 
 * @author Richard
 * This is a superclass for states. Right now it is setup for mouse input and not keyboard. This will have to change
 */

public abstract class State {
	protected OrthographicCamera camera;
	protected Vector3 mouse;
	protected GameStateManager theGSM;
	
	protected State(GameStateManager aGSM){
		this.theGSM = aGSM;
		camera = new OrthographicCamera();
		mouse = new Vector3();
	}
	
	protected abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch aSpriteBatch);
	public abstract void dispose();

}
