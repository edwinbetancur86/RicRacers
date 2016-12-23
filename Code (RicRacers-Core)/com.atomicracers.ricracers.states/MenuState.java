// EDWIN NOTES: THIS IS REALLY THE WELCOME SCREEN
// EDWIN NOTES: EDITED THIS CLASS AND CHANGED THE STATE TO MainMenuState

package com.atomicracers.ricracers.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.atomicracers.ricracers.*;
/**
 * 
 * @author Richard
 * This class sets up the main menu screen
 *
 */
public class MenuState extends State
{
	BitmapFont font;
	Texture background;
	Sound gameTheme;

	public MenuState(GameStateManager aGSM) 
	{
		super(aGSM);
		background = new Texture("Rhode Island college title.png");
		font = new BitmapFont();
		font.getData().setScale(3);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//gameTheme = Gdx.audio.newSound(Gdx.files.internal("game themetest.wav"));
		//gameTheme.play(1.0f);
		
		
	}

	/** This will check to see*/
	public void handleInput() {
		if(Gdx.input.justTouched()){
			theGSM.set(new MainMenuState(theGSM));
			this.dispose();
		}
		
	}

	/** This will check to see if user has done anything*/
	public void update(float dt) {
		handleInput();
		
	}

	/** This renders what the main menu will look like */
	public void render(SpriteBatch aSpriteBatch) {
		aSpriteBatch.begin();
		aSpriteBatch.draw(background, 0,0, Game.WIDTH, Game.HEIGHT);
		//font.draw(aSpriteBatch, "Ric Racers", 100, 640);
		//font.draw(aSpriteBatch, "under construction and a little deficient", 100,620);
		//font.draw(aSpriteBatch, "click anywhere to begin", 100, 640);
		aSpriteBatch.end();
		
	}

	/** This is called when the state is switched to make sure there are no memory leaks*/
	public void dispose() {
		
		font.dispose();
	}

}
