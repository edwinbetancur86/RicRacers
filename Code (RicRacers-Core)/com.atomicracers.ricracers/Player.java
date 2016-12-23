package com.atomicracers.ricracers;

public class Player 
{
	String username, password;
	int game, score, level, highScore, laps, totalLaps;
	private String modeSelection;	
	
	// Creates an existing player with there data 
	public Player()
	{
		modeSelection = null;
	}
	
	
	public void chooseMode(String pMSelection)
	{
		if (pMSelection.equalsIgnoreCase("single player"))
		{
			System.out.println("You have chosen "
					+ "single player mode.");
		}
		else if (pMSelection.equalsIgnoreCase("multiplayer"))
		{
			System.out.println("You have chosen "
					+ "multiplayer mode.");
		}
		else
		{
			System.out.println("That is not a mode.");
		}
	}
	
	
	// Determines the difficulty of the buggie
	public void setDifficulty(String color)
	{
		if (color.equalsIgnoreCase("Green"))
		{
			System.out.println("You have chosen the "
					+ "Green easy buggie.");
		}
		else if (color.equalsIgnoreCase("Yellow"))
		{
			System.out.println("You have chosen the "
					+ "Yellow medium buggie.");
		}
		else if (color.equalsIgnoreCase("Red"))
		{
			System.out.println("You have chosen the "
					+ "Red hard buggie.");
		}
		else
		{
			System.out.println("Wrong choice of difficulty.");
		}
		
	}
	
	public void setGameLevel(int userLvlChoice)
	{
		System.out.println("You have chosen level " + userLvlChoice);
	}
	
	
	// Validates the users login information and sets the username
	// and password if valid
	public void signOn(String userN, String pass)
	{
		System.out.println("Your login infomation is being sent "
				+ "to the database for validation.");
		System.out.println("Checking for username: " + userN);
		System.out.println("Valiating password.");
		System.out.println("The database has confrimed your "
				+ "infomation, you have succesfully logged on.");
		
	}
	
	public void logout()
	{
		System.out.println("You have sucessfully logged out.");
	}
	
	public void endGame()
	{
		System.out.println("The end has ended.");
	}
	
	
	public void deleteAccount(String userN, String pass)
	{
		System.out.println("You have sucessfully deleted your account with "
				+ "username: " + userN);
	}
	
	public void createAccount(String userN, String pass)
	{
		// check for profanity
		// check for duplicates 
		
		System.out.print("You have sucessfully created a new account "
				+ "with username: " + userN);
	}
	
	public void viewStats()
	{
		System.out.println("Abhi");
		System.out.println("Game: 4\t\t\tScore: 256\t\t\tLaps: 5");	
	}
	
	// ViewOptions() was here and was taken out to be used in some other class
	
	
	// viewInstructions() was here and was taken out to be used in some other class

	
	public void setGame(int g)
	{
		game = g;
	}
	
	public void setScore(int s)
	{
		score = s;
	}
	
	public void setLevel(int lvl)
	{
		level = lvl;
	}
	
	public void setHighScore(int hiScore)
	{
		highScore = hiScore;
	}
	
	public void setLaps(int l)
	{
		laps = l;
	}
	
	public void setTotalLaps(int totalL)
	{
		totalLaps = totalL;
	}
	
	public int getGame()
	{
		 
		return game;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getHighScore()
	{
		return highScore;
	}
	
	public int getLaps()
	{
		return laps;
	}
	
	public int totalLaps()
	{
		return totalLaps;
	}
}
