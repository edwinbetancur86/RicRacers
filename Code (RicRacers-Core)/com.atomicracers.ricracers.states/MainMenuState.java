package com.atomicracers.ricracers.states;


import com.atomicracers.ricracers.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author Richard
 * This will create the main menu where the player will be given multiple options
 */

public class MainMenuState extends State implements InputProcessor{
	private OrthographicCamera camera;
	private Texture exitButtonActive;
	private Texture exitButtonInactive;
	private Texture playButtonActive;
	private Texture playButtonInactive;
	private Texture multiplayerButtonInactive;
	private Texture multiplayerButtonActive;
	private Texture statsButtonInactive;
	private Texture statsButtonActive;
	private Texture optionsButtonInactive;
	private Texture optionsButtonActive;
	private Texture helpButtonActive;
	private Texture helpButtonInactive;
	private Texture background;
	private Sound doorActive;
	private Sound aClickSound;
	private boolean playedYet = false;//so that it only plays once when exit is hovered on
	boolean isTouched;
	

	public MainMenuState(GameStateManager aGSM) {
		super(aGSM);
		exitButtonActive = new Texture ("exitActive.png");
		exitButtonInactive = new Texture ("exitInactive.png");
		playButtonActive = new Texture ("playActive.png");
		playButtonInactive = new Texture ("playInactive.png");
		statsButtonInactive = new Texture("statsInactive.png");
		statsButtonActive = new Texture ("statsActive.png");
		helpButtonActive = new Texture("helpActiveButton.png");
		helpButtonInactive = new Texture("helpInactiveButton.png");
		background = new Texture ("mainMenuScreenBackground.png");
		optionsButtonActive = new Texture("optionsActive.png");
		optionsButtonInactive = new Texture("optionsInactive.png");
		multiplayerButtonInactive = new Texture("multiplayerInactive.png");
		multiplayerButtonActive = new Texture("multiplayerActive.png");
		aClickSound = Gdx.audio.newSound(Gdx.files.internal("clickNoise.mp3"));
		doorActive = Gdx.audio.newSound(Gdx.files.internal("door sound.mp3"));
		camera = new OrthographicCamera(650,650);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
		
	}
	/** This will handle what will happen when the exit button is interacted with*/
	public void exitHandleInput(){
		if(Gdx.input.justTouched()){
			aClickSound.play(1.0f);
			Gdx.app.exit();
		}
	}
	/** This will handle what will happen when the options button is clicked*/
	public void optionsHandleInput(){
		if(Gdx.input.justTouched()){
			aClickSound.play(1.0f);
		}
	}
	/** This will handle what will happen when the multiplayer button is clicked*/
	public void multiplayerHandleInput(){
		if(Gdx.input.justTouched()){
			aClickSound.play();
		}
	}
	/** This will handle what will happen when the help button is clicked*/
	public void helpHandleInput(){
		if(Gdx.input.justTouched()){
			aClickSound.play(1.0f);
			theGSM.set(new HelpState(theGSM));
			this.dispose();
		}
	}
	/** This will handle what will happen when the play button is clicked*/
	public void playHandleInput(){
		if(Gdx.input.justTouched()){
			aClickSound.play(1.0f);
			theGSM.set(new PlayState(theGSM));
			this.dispose();
		}
	}
	/** This handles the input for the stats button and determines what will happen*/
	public void statsHandleInput(){
		if(Gdx.input.justTouched()){
			aClickSound.play(1.0f);
			this.theGSM.set(new StatsState(theGSM));
			this.dispose();
		}
	}
	/** This handles generic input for the MainMenuState*/
	public void handleInput() {
	}
	
	/** This method will update the screen for general purposes*/
	public void update(float dt) {
		handleInput();
	}

	/** This handles what and where sprites and objects will be drawn on the screen for this state*/
	public void render(SpriteBatch aSpriteBatch) {
		aSpriteBatch.begin();
		aSpriteBatch.setProjectionMatrix(camera.combined);
		aSpriteBatch.draw(background, 0,0, Game.WIDTH, Game.HEIGHT);
		if(Gdx.input.getX() >10 && Gdx.input.getX()< 100 && Gdx.input.getY()>10 && Game.HEIGHT -Gdx.input.getY() < 120)
		{
			if(playedYet == false){//For when to play door sound 
				playedYet = hoveredOn();
			}
			aSpriteBatch.draw(exitButtonActive, 10, 10, 100, 100);
			this.touchUp(0, 0, 0, 1);
		}
		else{
		playedYet = false;
		aSpriteBatch.draw(exitButtonInactive, 10, 10, 100, 100);
		}
		if(Gdx.input.getX() >500 && Gdx.input.getX()< 600 && Game.HEIGHT - Gdx.input.getY()>345 && Game.HEIGHT -Gdx.input.getY() < 430){
			aSpriteBatch.draw(playButtonActive, 500, 350, 100, 75);
			this.touchUp(0, 0, 0, 2);
		}
		else{
			aSpriteBatch.draw(playButtonInactive, 500, 350, 100, 75 );
		}
		if(Gdx.input.getX() >500 && Gdx.input.getX()< 600 && Game.HEIGHT - Gdx.input.getY()>265 && Game.HEIGHT -Gdx.input.getY() < 345){
			aSpriteBatch.draw(statsButtonActive, 500, 270, 100, 75);
			this.touchUp(0, 0, 0, 3);
		}
		else{
		aSpriteBatch.draw(statsButtonInactive, 500, 270, 100, 75);
		}
		if(Gdx.input.getX() >500 && Gdx.input.getX()< 600 && Game.HEIGHT - Gdx.input.getY()>188 && Game.HEIGHT -Gdx.input.getY() < 265){
			aSpriteBatch.draw(helpButtonActive, 500, 190, 100, 75);
			this.touchUp(0, 0, 0, 4);
		}
		else{
		aSpriteBatch.draw(helpButtonInactive, 500, 190, 100, 75);
		}
		if(Gdx.input.getX() >500 && Gdx.input.getX()< 600 && Game.HEIGHT - Gdx.input.getY()> 110 && Game.HEIGHT - Gdx.input.getY() < 186){
		           aSpriteBatch.draw(multiplayerButtonActive, 500, 110, 100, 75);
		           this.touchUp(0, 0, 0, 5);
	    }
		else{
			aSpriteBatch.draw(multiplayerButtonInactive, 500, 110, 100, 75);
			}
		if(Gdx.input.getX() >500 && Gdx.input.getX()< 600 && Game.HEIGHT - Gdx.input.getY()>25 && Game.HEIGHT - Gdx.input.getY() < 100){
					aSpriteBatch.draw(optionsButtonActive, 500, 25, 100, 75);
					this.touchUp(0, 0, 0, 6);
				}
		else{
			aSpriteBatch.draw(optionsButtonInactive, 500, 25, 100, 75);
				}
		aSpriteBatch.end();	
		
	}

	/** This will destroy the current state*/
	public void dispose() {
	}
	/** This will play one door sound when the exit button is hovered over*/
	public boolean hoveredOn(){
		doorActive.play(1.0f);
		return true;
	}
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

	/** This method listens for a mouse click release. It will call the input for the back button. 
	 * Button 1 = exit, Button 2 = play, Button 3 = stats, Button 4 = help, Button 5 = multiplayer, Button 6 = options
	 * The false is not used in this program but is required by its superclass.
	 */
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == 1){
			this.exitHandleInput();
			return false;
		}
		if(button == 2){
			this.playHandleInput();
			return false;
		}
		if(button == 3){
			this.statsHandleInput();
			return false;
		}
		if(button == 4){
			this.helpHandleInput();
			return false;
		}
		if(button == 5){
			this.multiplayerHandleInput();
		    return false;
		}
		if(button == 6){
			this.optionsHandleInput();
			return false;
		}
		else{
		return false;
		}
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
