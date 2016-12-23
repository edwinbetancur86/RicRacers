package com.atomicracers.ricracers.states;
import java.util.Stack;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/******************************
 * 
 * @author Richard Cenedella
 * This class sets up a game state manager. All the games states will be pushed onto a stack where it will process 
 * the state at the top of the stack.
 */
public class GameStateManager {
	private Stack<State> theStates;
	
	public GameStateManager(){
		theStates = new Stack<State>();
	}
	
	/** */
	public void push(State aState){
		theStates.push(aState);
	}
	
	/** */
	public void pop(){
		theStates.pop();
	}
	
	/** */
	public void set(State aState){
		theStates.pop();
		theStates.push(aState);
	}
	
	/** This will update the top state with a new deltatime for updating purposes*/
	public void update(float dt){
		theStates.peek().update(dt);
	}
	
	/** This will render the state at the top of the state*/
	public void render(SpriteBatch aSpriteBatch){
		theStates.peek().render(aSpriteBatch);
	}

}
