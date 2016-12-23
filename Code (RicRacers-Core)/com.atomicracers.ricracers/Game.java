package com.atomicracers.ricracers;

import com.atomicracers.ricracers.states.GameStateManager;
import com.atomicracers.ricracers.states.MenuState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author Abhi, Edwin, and Richard
 *
 */
public class Game extends ApplicationAdapter {
	public static final int WIDTH = 650;
	public static final int HEIGHT = 650;
	public static final String TITLE = "Ric Racers";
	private SpriteBatch batch;
	private GameStateManager theGameState;	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		theGameState = new GameStateManager();
		
		// Which is really the welcome screen
		theGameState.push(new MenuState(theGameState));
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		theGameState.update(Gdx.graphics.getDeltaTime());
		theGameState.render(batch);
		
	}
	


}
