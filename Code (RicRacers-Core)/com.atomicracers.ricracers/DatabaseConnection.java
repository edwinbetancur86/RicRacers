package com.atomicracers.ricracers;

public abstract class DatabaseConnection {
	

	
	// Test whether the database is open.
	public abstract boolean isOpen(); 
	
	
	// Close the database connection.
	 public abstract void close(); 
	 
	 // Can be done before the player logs in.
	 public abstract void createPlayer(String uname, String passw);
	 
	 // Applies when the player has logged in.
	 public abstract void removePlayer(String uname);
	 
	 // Applies when the player has logged in.
	 public abstract void showPlayerStats(String username);
	 
	 // Applies when the player has logged in.
	 public abstract void updatePlayerStats
	 (String username, int game, 
	  int score, int highScore, int level, int laps, int totalLaps);
	 
	 public abstract void validatePlayer(String username, String password);
	 
}
