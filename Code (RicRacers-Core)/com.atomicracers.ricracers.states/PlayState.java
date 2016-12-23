// NOTE: MIGHT WANT TO CHECK FOR UPPER & LOWER BOUNDARIES FOR BUGGIE
// IMPLEMENT THIS CODE IF YOU CAN

// TAKE NOTE OF THE SIZE OF THE BOUNDS AND RANDOMIZITION RANGE
package com.atomicracers.ricracers.states;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.atomicracers.ricracers.*;
import com.atomicracers.ricracers.sprites.Bench;
import com.atomicracers.ricracers.sprites.Buggie;
import com.atomicracers.ricracers.sprites.Bush;
import com.atomicracers.ricracers.sprites.DirtBush;
import com.atomicracers.ricracers.sprites.EmergencyLamp;
import com.atomicracers.ricracers.sprites.Obstacle;
import com.atomicracers.ricracers.sprites.TrashCan;
/** 
 * 
 * @author Richard
 * This class sets up the playstate of the game. This is a test level for now
 */
public class PlayState extends State{
	 
	private Texture background; 
	private String gameOver;
	private BitmapFont font;
	private Obstacle emergencyLamp, emergencyLamp2, 
	bush, bush2, dirtBush, dirtBush2, bench, bench2, 
	trashCan, trashCan2, trashCan3;
	private Vector2 backgroundPos1, backgroundPos2;
	private Buggie theBuggie;
	private int adjCamera;
	private Music music;
	private Sound aCrash;
	private Random generator;
	private boolean soundPlayed = false;
	private boolean  contin = true;
	private int randomPositionX;
	
	private final float BUSH1_DAMAGE = (float) 0.9;
	private final float BUSH20_DAMAGE = (float) 0.9;
	private final float BENCH_DAMAGE = (float) 0.9;
	private final float BENCH2_DAMAGE = (float) 0.9;
	private final float TRASH_CAN_DAMAGE = (float) 0.9;
	private final float EMERGENCY_LAMP_DAMAGE = 10;
	private final int LEFT_BOUND_LEFT_AREA = -30;
	private final int RIGHT_BOUND_LEFT_AREA = 10;
	private final int LEFT_BOUND_RIGHT_AREA = 450;
	private final int RIGHT_BOUND_RIGHT_AREA = 600;
	private final int LEFT_BOUND_MIDDLE_AREA = 50;
	private final int RIGHT_BOUND_MIDDLE_AREA = 400;
	private int theScore = 0;
	private boolean scoreUpdate = true;
    private String toStringScore;
    int updateScore = 5;
	int i = 0;

	protected PlayState(GameStateManager aGSM) {
		super(aGSM);
		camera.setToOrtho(false, Game.WIDTH, Game.HEIGHT);//sets up the camera to zoom in the gameworld to the buggie.(Will have to check to see if it works though)
		theBuggie = new Buggie(200, 300); // 200, 300
		adjCamera = 100;
		
		music = Gdx.audio.newMusic(Gdx.files.internal("gameTheme2.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		
		aCrash = Gdx.audio.newSound(Gdx.files.internal("a_car_crash.mp3"));
		
		background = new Texture("playStateBackground1.png");
		backgroundPos1 = new Vector2(camera.position.x - camera.viewportWidth /2 , 0);
		backgroundPos2 = new Vector2(0, (camera.position.y - camera.viewportHeight /2) + background.getHeight());
		
		// Create some obstacles 
		emergencyLamp = new EmergencyLamp(400, 300);
		emergencyLamp2 = new EmergencyLamp(100, -200); // 100, 200
		bush = new Bush(40, -100); // 40, 50 
		bush2 = new Bush(50, -100); 
		dirtBush = new DirtBush(350, -100); // 300, 400 
		dirtBush2 = new DirtBush(50, -100); // 50,300
		
		// The bench constructor accepts two bench images,
		// the original, and the horizontal flip image.
		bench = new Bench(-10, 100, "bench.png");
		bench2 = new Bench(525, 200, "bench2.png");
		trashCan = new TrashCan(-10, 300); 	// The left trashCan 
		trashCan2 = new TrashCan(525, 400); // The right trashCan
		trashCan3 = new TrashCan(110, 500); // the middle trashCan
		
		generator = new Random();
		font = new BitmapFont();
		font.getData().setScale(3);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear,  TextureFilter.Linear);
		gameOver = "";
		toStringScore =("Score: "+ theScore);
		aCrash = Gdx.audio.newSound(Gdx.files.internal("a_car_crash.mp3"));
		

	}

	/** This handles what the input of the user is.*/
	protected void handleInput() {
		adjCamera = theBuggie.moveBuggie(adjCamera, Gdx.graphics.getDeltaTime());
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
		{
			// brings you back to the main menu
			theGSM.set(new MainMenuState(theGSM));
			this.dispose();
		}
	}

	/** This checks to see what the input of the user is and updates the screen with what will need to be drawn next*/
	public void update(float dt) {
		this.handleInput();
		theBuggie.update(dt);
		camera.position.y = theBuggie.getPosition().y + adjCamera; //moves the camera to follow the buggie
		this.updatebackGround();	//makes the ground update correctly
		
		// Applies to EmergencyLamp
		this.updateEmergencyLamp();
		emergencyLamp.reposition(((EmergencyLamp) emergencyLamp).getPosEmergencyLamp().x, ((EmergencyLamp) emergencyLamp).getPosEmergencyLamp().y);
		emergencyLamp2.reposition(((EmergencyLamp) emergencyLamp2).getPosEmergencyLamp().x, ((EmergencyLamp) emergencyLamp2).getPosEmergencyLamp().y);
		
		// Applies to Bush
		this.updateBush();
		bush.reposition(((Bush) bush).getPosBush().x, ((Bush) bush).getPosBush().y);
		bush2.reposition(((Bush) bush2).getPosBush().x, ((Bush) bush2).getPosBush().y);
		
		// Applies to dirtBush
		this.updateDirtBush();
		dirtBush.reposition(((DirtBush) dirtBush).getPosDirtBush().x, ((DirtBush) dirtBush).getPosDirtBush().y);
		dirtBush2.reposition(((DirtBush) dirtBush2).getPosDirtBush().x, ((DirtBush) dirtBush2).getPosDirtBush().y);
		
		// Applies to Bench
		this.updateBench();
		bench.reposition(((Bench) bench).getPosBench().x, ((Bench) bench).getPosBench().y);
		bench2.reposition(((Bench) bench2).getPosBench().x, ((Bench) bench2).getPosBench().y);
		
		// Applies to TrashCan
		this.updateTrashCan();
		trashCan.reposition(((TrashCan) trashCan).getPosTrashCan().x, ((TrashCan) trashCan).getPosTrashCan().y);
		trashCan2.reposition(((TrashCan) trashCan2).getPosTrashCan().x, ((TrashCan) trashCan2).getPosTrashCan().y);
		trashCan3.reposition(((TrashCan) trashCan3).getPosTrashCan().x, ((TrashCan) trashCan3).getPosTrashCan().y);
		
		if (emergencyLamp.collides(theBuggie.getBounds()))
		{
			if(soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
				
				gameOver = "Game Over";
				theBuggie.removeHealthCompletely();
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
		}
		
		if (emergencyLamp2.collides(theBuggie.getBounds()))
		{
			if(soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
				gameOver = "Game Over";
				theBuggie.removeHealthCompletely();
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
		}
		
		if (bush.collides(theBuggie.getBounds()))
		{
			if(soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
	
			if (theBuggie.getBuggieHealth() == 0)
			{
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			}
			else	
			{
				theBuggie.removeHealthBar(BUSH20_DAMAGE);	
				soundPlayed = hitObst();
			}
		
					
		}
		
		if (bush2.collides(theBuggie.getBounds()))
		{
			if(soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
			if (theBuggie.getBuggieHealth() == 0)
			{	
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			
			}
			else	
			{
				theBuggie.removeHealthBar(BUSH20_DAMAGE);	
				soundPlayed = hitObst();
			}	
			
			
		}
		
		if (dirtBush.collides(theBuggie.getBounds()))
		{
			
			
			if (soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
			
			if (theBuggie.getBuggieHealth() == 0)
			{	
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			
			}
			else	
			{
				theBuggie.removeHealthBar(BUSH1_DAMAGE);	
				soundPlayed = hitObst();
			}	
					
		}
		
		if (dirtBush2.collides(theBuggie.getBounds()))
		{
			
				
			if (soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
			
			if (theBuggie.getBuggieHealth() == 0)
			{	
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			
			}
			else	
			{
				theBuggie.removeHealthBar(BUSH1_DAMAGE);	
				soundPlayed = hitObst();
			}
			
					
		}
		
		if (bench.collides(theBuggie.getBounds()))
		{
			if (soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
			
			if (theBuggie.getBuggieHealth() == 0)
			{	
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			
			}
			else	
			{
				theBuggie.removeHealthBar(BENCH_DAMAGE);	
				soundPlayed = hitObst();
			}
					
		}
		
		if (bench2.collides(theBuggie.getBounds()))
		{
			
			if (soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
			if (theBuggie.getBuggieHealth() == 0)
			{	
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			
			}
			else
			{
				theBuggie.removeHealthBar(BENCH2_DAMAGE);	
				soundPlayed = hitObst();
			}
		}
		
		if (trashCan.collides(theBuggie.getBounds()))
		{
			if(soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
			if (theBuggie.getBuggieHealth() == 0)
			{
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			}
			else	
			{
				theBuggie.removeHealthBar(TRASH_CAN_DAMAGE);	
				soundPlayed = hitObst();
			}
			
			
		}
		
		if (trashCan2.collides(theBuggie.getBounds()))
		{
			if(soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
			if (theBuggie.getBuggieHealth() == 0)
			{
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			}
			else	
			{
				theBuggie.removeHealthBar(TRASH_CAN_DAMAGE);	
				soundPlayed = hitObst();
			}
			
			

		}
		
		if (trashCan3.collides(theBuggie.getBounds()))
		{
			if(soundPlayed == false)
			{
				soundPlayed = hitObst();
			}
			
			if (theBuggie.getBuggieHealth() == 0)
			{
				gameOver = "Game Over";
				theBuggie.stopBuggie();
				contin = false;
				scoreUpdate = false;
			}
			else	
			{
				theBuggie.removeHealthBar(TRASH_CAN_DAMAGE);	
				soundPlayed = hitObst();
			}
		

		}
		
		camera.update();
		
	}

	/** This renders what will be on the screen.*/
	public void render(SpriteBatch aSpriteBatch) 
	{
		aSpriteBatch.setProjectionMatrix(camera.combined);//this will set the camera to our position in the gameworld
		aSpriteBatch.begin();
		aSpriteBatch.draw(background, backgroundPos1.x, backgroundPos1.y);
		aSpriteBatch.draw(background, backgroundPos2.x, backgroundPos2.y);
		aSpriteBatch.draw(theBuggie.getBuggieTexture(), theBuggie.getPosition().x, 
				theBuggie.getPosition().y);
		
		
		// Draw EmergencyLamp 
		aSpriteBatch.draw(((EmergencyLamp) emergencyLamp).getEmergencyLamp(), 
				((EmergencyLamp) emergencyLamp).getPosEmergencyLamp().x, 
				((EmergencyLamp) emergencyLamp).getPosEmergencyLamp().y, 20, 150); 
		
		// Draw EmergencyLamp2
        aSpriteBatch.draw(((EmergencyLamp) emergencyLamp2).getEmergencyLamp(), 
        		((EmergencyLamp) emergencyLamp2).getPosEmergencyLamp().x, 
        		((EmergencyLamp) emergencyLamp2).getPosEmergencyLamp().y, 20, 150);
       
        // Draw Bush
        aSpriteBatch.draw(((Bush) bush).getBush(), 
        		((Bush) bush).getPosBush().x, 
        		((Bush) bush).getPosBush().y, 50, 80);
        
        // Draw Bush2
        aSpriteBatch.draw(((Bush) bush2).getBush(), 
        		((Bush) bush2).getPosBush().x, 
        		((Bush) bush2).getPosBush().y, 50, 80);
        
        // Draw dirtBush
        aSpriteBatch.draw(((DirtBush) dirtBush).getDirtBush(), 
        		((DirtBush) dirtBush).getPosDirtBush().x, 
        		((DirtBush) dirtBush).getPosDirtBush().y, 40, 50); 
        
        // Draw dirtBush2
        aSpriteBatch.draw(((DirtBush) dirtBush2).getDirtBush(), 
        		((DirtBush) dirtBush2).getPosDirtBush().x, 
        		((DirtBush) dirtBush2).getPosDirtBush().y, 40, 50);
        
        // Draw bench
        aSpriteBatch.draw(((Bench) bench).getBench(), 
        		((Bench) bench).getPosBench().x, 
        		((Bench) bench).getPosBench().y, 55, 80); // 100, 500
        
        // Draw bench2
        aSpriteBatch.draw(((Bench) bench2).getBench(), 
        		((Bench) bench2).getPosBench().x, 
        		((Bench) bench2).getPosBench().y, 55, 80);
        
        // Draw trashCan
        aSpriteBatch.draw(((TrashCan) trashCan).getTrashCan(), 
        		((TrashCan) trashCan).getPosTrashCan().x, 
        		((TrashCan) trashCan).getPosTrashCan().y, 45, 80); 
        
        // Draw trashCan
        aSpriteBatch.draw(((TrashCan) trashCan2).getTrashCan(), 
        		((TrashCan) trashCan2).getPosTrashCan().x, 
        		((TrashCan) trashCan2).getPosTrashCan().y, 45, 80); 
        
        // Draw trashCan
        aSpriteBatch.draw(((TrashCan) trashCan3).getTrashCan(), 
        		((TrashCan) trashCan3).getPosTrashCan().x, 
        		((TrashCan) trashCan3).getPosTrashCan().y, 45, 80); 
        
        
        // Draw game over text to the screen
        font.draw(aSpriteBatch, gameOver, 300, camera.position.y);
		theBuggie.updateMovement(contin);
		
		// Update the score and display it to the screen
		font.draw(aSpriteBatch, this.updateScore(), 300, camera.position.y+300);
		
		// Display the healthbar of the buggie to the player
		font.draw(aSpriteBatch, theBuggie.toStringHealthBar(), 10, camera.position.y + 300);
		aSpriteBatch.end();
		
	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub
		aCrash.dispose();
		music.dispose();
	}
	
		
	/** This correctly repositions the new backgrounds when they goes off screen*/
	private void updatebackGround()
	{
		if(camera.position.y - (camera.viewportHeight/2) > backgroundPos1.y + background.getHeight())
		{
			backgroundPos1.add(0, background.getHeight()*2);
			
		}
		
		if(camera.position.y - (camera.viewportHeight/2) > backgroundPos2.y + background.getHeight())
		{
			backgroundPos2.add(0, background.getHeight()*2);
			
		}
	}
	
	private void updateEmergencyLamp()
	{
		// Update the emergencyLamp
		if (camera.position.y - (camera.viewportHeight/2) > 
		((EmergencyLamp) emergencyLamp).getPosEmergencyLamp().y + ((EmergencyLamp) emergencyLamp).getEmergencyLamp().getHeight())
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_MIDDLE_AREA-LEFT_BOUND_MIDDLE_AREA)+1)+LEFT_BOUND_MIDDLE_AREA;
			
			emergencyLamp.reposition(randomPositionX, ((EmergencyLamp) emergencyLamp).getPosEmergencyLamp().y + Game.HEIGHT + 500);
			
			((EmergencyLamp) emergencyLamp).getPosEmergencyLamp().add(0, ((EmergencyLamp) emergencyLamp).getEmergencyLamp().getHeight()*2);
		}
		
		// Update the emergencyLamp2
		if (camera.position.y - (camera.viewportHeight/2) > 
		((EmergencyLamp) emergencyLamp2).getPosEmergencyLamp().y + ((EmergencyLamp) emergencyLamp2).getEmergencyLamp().getHeight())
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_MIDDLE_AREA-LEFT_BOUND_MIDDLE_AREA)+1)+LEFT_BOUND_MIDDLE_AREA;
			
			emergencyLamp2.reposition(randomPositionX, ((EmergencyLamp) emergencyLamp2).getPosEmergencyLamp().y);
			
			((EmergencyLamp) emergencyLamp2).getPosEmergencyLamp().add(0, ((EmergencyLamp) emergencyLamp2).getEmergencyLamp().getHeight()*2);
		}
	}
	
	private void updateBush()
	{
		// Update the bush
		if (camera.position.y - (camera.viewportHeight/2) > 
		((Bush) bush).getPosBush().y + (((Bush) bush).getBush().getHeight() + 30))
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_LEFT_AREA-LEFT_BOUND_LEFT_AREA)+1)+LEFT_BOUND_LEFT_AREA;
			
			// Respawn the bush off the top of the screen by adding the Game height 
			// to the bush's y position
			bush.reposition(randomPositionX, ((Bush) bush).getPosBush().y + Game.HEIGHT);
			
			((Bush) bush).getPosBush().add(0, ((Bush) bush).getBush().getHeight()*2);
		}
		
		// Update the bush2
		if (camera.position.y - (camera.viewportHeight/2) > 
		((Bush) bush2).getPosBush().y + ((Bush) bush2).getBush().getHeight() + 30) 
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_RIGHT_AREA-LEFT_BOUND_RIGHT_AREA)+1)+LEFT_BOUND_RIGHT_AREA;
			
			bush2.reposition(randomPositionX, ((Bush) bush2).getPosBush().y + Game.HEIGHT + 100);
			
			((Bush) bush2).getPosBush().add(0, ((Bush) bush2).getBush().getHeight()*2);
		}
		
	}
	
	private void updateDirtBush()
	{
		// Update the bush
		if (camera.position.y - (camera.viewportHeight/2) > 
		((DirtBush) dirtBush).getPosDirtBush().y + ((DirtBush) dirtBush).getDirtBush().getHeight() + 30)
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_MIDDLE_AREA-LEFT_BOUND_MIDDLE_AREA)+1)+LEFT_BOUND_MIDDLE_AREA;
			
			dirtBush.reposition(randomPositionX, ((DirtBush) dirtBush).getPosDirtBush().y + Game.HEIGHT + 300);
			
			((DirtBush) dirtBush).getPosDirtBush().add(0, ((DirtBush) dirtBush).getDirtBush().getHeight()*2);
		}
		
		// Update the bush2
		if (camera.position.y - (camera.viewportHeight/2) > 
		((DirtBush) dirtBush2).getPosDirtBush().y + ((DirtBush) dirtBush2).getDirtBush().getHeight() + 30)
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_MIDDLE_AREA-LEFT_BOUND_MIDDLE_AREA)+1)+LEFT_BOUND_MIDDLE_AREA;
			
			dirtBush2.reposition(randomPositionX, ((DirtBush) dirtBush2).getPosDirtBush().y + Game.HEIGHT);
			
			((DirtBush) dirtBush2).getPosDirtBush().add(0, ((DirtBush) dirtBush2).getDirtBush().getHeight()*2);
		}
	}
	
	
	private void updateBench()
	{
		// Update the bench
		if (camera.position.y - (camera.viewportHeight/2) > 
		((Bench) bench).getPosBench().y + ((Bench) bench).getBench().getHeight() + 30)
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_LEFT_AREA-LEFT_BOUND_LEFT_AREA)+1)+LEFT_BOUND_LEFT_AREA;
			
			bench.reposition(randomPositionX, ((Bench) bench).getPosBench().y + Game.HEIGHT + 300);
			
			((Bench) bench).getPosBench().add(0, ((Bench) bench).getBench().getHeight()*2);
		}
		
		// Update the bench2
		if (camera.position.y - (camera.viewportHeight/2) > 
		((Bench) bench2).getPosBench().y + ((Bench) bench2).getBench().getHeight() + 30)
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_RIGHT_AREA-LEFT_BOUND_RIGHT_AREA)+1)+LEFT_BOUND_RIGHT_AREA;
			
			bench2.reposition(randomPositionX, ((Bench) bench2).getPosBench().y + Game.HEIGHT);
			
			((Bench) bench2).getPosBench().add(0, ((Bench) bench2).getBench().getHeight()*2);
		}
	}
	
	
	private void updateTrashCan()
	{
		// Update the trashCan
		if (camera.position.y - (camera.viewportHeight/2) > 
		((TrashCan) trashCan).getPosTrashCan().y + ((TrashCan) trashCan).getTrashCan().getHeight() + 30)
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_LEFT_AREA-LEFT_BOUND_LEFT_AREA)+1)+LEFT_BOUND_LEFT_AREA;
			
			trashCan.reposition(randomPositionX, ((TrashCan) trashCan).getPosTrashCan().y + Game.HEIGHT + 300);
			
			((TrashCan) trashCan).getPosTrashCan().add(0, ((TrashCan) trashCan).getTrashCan().getHeight()*2);
		}
		
		// Update the trashCan2
		if (camera.position.y - (camera.viewportHeight/2) > 
		((TrashCan) trashCan2).getPosTrashCan().y + ((TrashCan) trashCan2).getTrashCan().getHeight() + 30)
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_MIDDLE_AREA-LEFT_BOUND_MIDDLE_AREA)+1)+LEFT_BOUND_MIDDLE_AREA;
			
			trashCan2.reposition(randomPositionX, ((TrashCan) trashCan2).getPosTrashCan().y + Game.HEIGHT);
			
			((TrashCan) trashCan2).getPosTrashCan().add(0, ((TrashCan) trashCan2).getTrashCan().getHeight()*2);
		}
		
		// Update the trashCan3
		if (camera.position.y - (camera.viewportHeight/2) > 
		((TrashCan) trashCan3).getPosTrashCan().y + ((TrashCan) trashCan3).getTrashCan().getHeight() + 30)
		{
			randomPositionX = generator.nextInt((RIGHT_BOUND_RIGHT_AREA-LEFT_BOUND_RIGHT_AREA)+1)+LEFT_BOUND_RIGHT_AREA;
			
			trashCan3.reposition(randomPositionX, ((TrashCan) trashCan3).getPosTrashCan().y + Game.HEIGHT);
			
			((TrashCan) trashCan3).getPosTrashCan().add(0, ((TrashCan) trashCan3).getTrashCan().getHeight()*2);
		}
		
	}
	
	
	
	public boolean hitObst()
	{
		aCrash.play(0.5f);
		return true;
	}
	
	public String updateScore()
	{
		if(scoreUpdate == true){
			if(i == updateScore){
			theScore++;
			toStringScore = ("Score: "+theScore);
			i=0;
			return toStringScore;
			}
			else{
			i++;
			toStringScore = ("Score: "+theScore);
			return toStringScore;
			}
		}
		else
		{
			return toStringScore;
		}
	}
	
	

}
