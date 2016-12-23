package com.atomicracers.ricracers.states;

import com.atomicracers.ricracers.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** 
 * This class sets up the gui for the help screen.
 * */
public class HelpState extends State implements InputProcessor {
	private Texture helpBackground;
	private Texture backButtonActive;
	private Texture backButtonInactive;
	private Sound aClickSound;
	

	protected HelpState(GameStateManager aGSM) {
		super(aGSM);
		helpBackground = new Texture ("HelpScreenBackground1.png");
		backButtonActive = new Texture ("goBackButtonActive.png");
		backButtonInactive = new Texture ("goBackButtonInactive.png");
		aClickSound = Gdx.audio.newSound(Gdx.files.internal("clickNoise.mp3"));
	}

	/** This will handle the input for the back button*/
	public void backHandleInput(){
		if(Gdx.input.justTouched()){
		  aClickSound.play(1.0f);
		  theGSM.set(new MainMenuState(theGSM));
		  this.dispose();
		}
	}
	
	/** This method deals with generic input*/
	protected void handleInput() {	
	}
	
	/** This method handles the update of the state*/
	public void update(float dt) {
	}

	/** This method displays what and where it will be on screen. BackButton size 130 x 30*/
	public void render(SpriteBatch aSpriteBatch) {
		aSpriteBatch.begin();
		aSpriteBatch.draw(helpBackground, 0,0);
		 if(Gdx.input.getX() >10 && Gdx.input.getX()< 140 && Gdx.input.getY()> 15 && Game.HEIGHT -Gdx.input.getY() < 40)
		{		
			aSpriteBatch.draw(backButtonActive, 10, 10, 130, 30);
	        this.touchUp(0,0,0,0);
		}
		else{
		aSpriteBatch.draw(backButtonInactive, 10, 10, 130, 30);
		}
		aSpriteBatch.end();
	}
	/** This method disposes the state*/
	public void dispose() {
	}
	//inputProcessor methods

	/** This method was not used. It listens for a key press*/
	public boolean keyDown(int keycode) {
		return false;
	}

	/** This method was not used. It listens for a key release*/
	public boolean keyUp(int keycode) {
		return false;
	}

	/** This method was not used. It listens for a typed key*/
	public boolean keyTyped(char character) {
		return false;
	}

	/** This method was not used. It listens for a click*/
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/** This method listens for a mouse click release. It will call the input for the back button*/
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		this.backHandleInput();
		return false;
	}

	/** This method was not used. It listens for a click and drag*/
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/** This method was not used. It listens for any mouse movement*/
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/** This method was not used. It listens for any scroll*/
	public boolean scrolled(int amount) {
		return false;
	}

	
}
