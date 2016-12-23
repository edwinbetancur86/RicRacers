package com.atomicracers.ricracers;
import java.sql.*;

import javax.swing.JOptionPane;

public class LocalDatabase extends DatabaseConnection
{
	private Connection conn;
	private boolean isopen;
	
	// Attempt to connect to the JavaDB Player database.
	// Turn off autocommit to enable transactions.
	public LocalDatabase(String uname, String pword)
	{
		try 
		{
   		 Class.forName("org.sqlite.JDBC");
   		 conn =
   		 DriverManager.getConnection("jdbc:sqlite:RicRacers Database.db",uname,pword);
   			 conn.setAutoCommit(false);
		 } 
		catch (Exception e) 
		{
			conn = null;
		}
		 isopen = (conn != null);
	}

	// Test whether the database is open.
	public boolean isOpen() {
		return isopen;
	}

	// Close the database connection.
	public void close() {
		 if (!isopen) return;
		 try 
		 {
			 conn.close();
		 }
		 catch (Exception e) 
		 {
			 
		 }
		 isopen = false;
		 conn = null;
		
	}

	// Create a new Player (only when the player is non-existing)
	public void createPlayer(String uname, String passw) {
		PreparedStatement stmt = null;
		String sql;
		
		// Return if the database is closed.
		if (!isopen) return;
		
		try 
		{
			 // validate an existing Player with that username
			
			 // validate any kind of profanity 
			  
			 // Create a PreparedStatement for the update.
			 sql = "INSERT INTO Profiles(Username, Password) VALUES (?, ?)";
			
			 stmt = conn.prepareStatement(sql);
			 
			 stmt.setString(1, uname); 
			 stmt.setString(2, passw);
			 
			 // Execute the SQL statement
			 stmt.executeUpdate();
			
			 stmt.close();
			 conn.commit();
			 
			 System.out.println("The Player with username " + uname 
				     + " was created\n");
			 
			 
		} 
		catch (Exception e) 
		{
			 System.out.printf("%s%n", e.getMessage());
			 try 
			 {
				 stmt.close();
			 }
			 catch (Exception err) 
			 {
				 
			 }
			 try 
			 {
				 conn.rollback();
			 }
			 catch (Exception err) 
			 {
				 
			 }
		 
	 	}
		
	}

	@Override
	public void removePlayer(String uname) 
	{
		 PreparedStatement stmt = null;
		 String sql;
		 
		// Return if the database is closed.
		 if (!isopen) return;
		 try 
		 {
			 
			 // Create a PreparedStatement for the query.
			 sql = "DELETE FROM Profile "
					 + "WHERE Profile.Username = " + uname; 
			 
			 stmt = conn.prepareStatement(sql);
			 
			 // Execute the update 
			 stmt.executeUpdate();
			
			 System.out.println("Player username " + uname + " was " 
					 + "removed");
		
			 stmt.close();
			 conn.commit();
			 
		 } 
		 catch (Exception e) 
		 {
			 System.out.printf("%s%n", e.getMessage());
			 try 
			 {
				 stmt.close();
			 }
			 catch (Exception err) 
			 {
				 
			 }
			 try 
			 {
				 conn.rollback();
			 }
			 catch (Exception err) 
			 {
				 
			 }
		 }
		
	}

	@Override
	public void showPlayerStats(String username) 
	{
		PreparedStatement stmt = null;
		ResultSet rset = null;
		ResultSetMetaData meta = null;
		String sql;
		int game, score, level;
		
		// Return if the database is closed.
		if (!isopen) return;
	 	try
	 	{
	 		
	 		// Create a PreparedStatement for the query.
	 		sql = "SELECT PlayerData.Game, PlayerData.Score, "
	 				+ "PlayerData.Lvl FROM Profiles, PlayerData "
	 				+ "WHERE Profiles.UsernameID = PlayerData.Username "
	 				+ "AND Profiles.UsernameID = '" + username 
	 				+ "' ORDER BY PlayerData.Game DESC;";
	 		
	 		stmt = conn.prepareStatement(sql);
	 		
	 		// Execute the query and print the result set.
	 		rset = stmt.executeQuery();
	 		
	 		// Use the metadata to print the column headings.
	        String label; 
	        meta = rset.getMetaData();
	        int ncols = meta.getColumnCount();
	        for (int col = 1; col <= ncols; ++col) {
	            label = meta.getColumnName(col);
	            System.out.printf("%-40s ", label);  
	        }
	        System.out.printf("%n");
	        
	 		
	 		
		 while (rset.next())
		 {
			 game = rset.getInt(1);
	         score = rset.getInt(2);
	         level = rset.getInt(3);
	        
	         System.out.printf("%-40d %-40s %-40s%n", game, score, level);
		}
		 	stmt.close();
		 	conn.commit();
		 	
		}
	 	catch (Exception e) 
	 	{
			 System.out.printf("%s%n", e.getMessage());
			 try 
			 {
				 stmt.close();
			 }
			 catch (Exception err) 
			 {
				 
			 }
			 try 
			 {
				 conn.rollback();
			 }
			 catch (Exception err) 
			 {
				 
			 }
		}
	}

	@Override
	public void updatePlayerStats(String username, int game, 
			  int score, int highScore, 
			  int level, int laps, int totalLaps) 
	{
		 PreparedStatement stmt = null;
		 String sql;
		 
		// Return if the database is closed.
		if (!isopen) return;
		
		
		try {
			 
			 // Create a PreparedStatement for the query.
			 sql = "INSERT INTO PlayerData (Username, Game, "
			 		+ "Score, Lvl, HighScore, Laps, TotalLaps) VALUES " 
					+ "('" + username + "', " + game + ", " + score 
					+ ", " + level + ", " + highScore + ", "
					+ laps + ", " + totalLaps + ");";
			 
			 stmt = conn.prepareStatement(sql);
			  
			 // Execute the update
			 stmt.executeUpdate();
			
			 System.out.println("PlayerData for "
			 		+ username + "has been updated"); 
					 
		
			 stmt.close();
			 conn.commit();
			 
		 } catch (Exception e) 
		{
			 System.out.printf("%s%n", e.getMessage());
			 try 
			 {
				 stmt.close();
			 }
			 catch (Exception err) 
			 {
				 
			 }
			 
			 try 
			 {
				 conn.rollback();
		     }
			 catch (Exception err) 
			 {
				 
			 }
		 }
	}
	
	// To check to see if the player with the specified username
	// exists and to see if the player has entered the right 
	// password for that username
	public void validatePlayer(String username, String password)
	{
		PreparedStatement stmt = null;
		ResultSet rset = null;
		String sql;
		
		// Return if the database is closed.
		if (!isopen) return;
	 	try
	 	{
	 		// Create a PreparedStatement for the query.
	 		sql = "SELECT Profiles.UsernameID " 
	 				+ "FROM Profiles "
	 				+ "WHERE Profiles.UsernameID = "
	 				+ "'" + username + "';";
	 		
	 		stmt = conn.prepareStatement(sql);
	 			
	 		// Check to see if the username exist
	 		if  (stmt.executeQuery() == null)
	 		{
	 			
	 			JOptionPane.showMessageDialog(null, "Username " + username + " doesn't exist.");
	 			
	 		}
	 		else
	 		{
	 			rset = stmt.executeQuery();
	 			
	 			if (password.equals(rset.getString(1)))
	 			{
	 				JOptionPane.showMessageDialog(null, "That is the CORRECT password");
	 			
	 			}
	 			else
	 			{
	 				JOptionPane.showMessageDialog(null, "Sorry, that is the INCORRECT password");
	 			}
	 		}
	 	    
			 	stmt.close();
			 	conn.commit();
		 	
		}
	 	catch (Exception e) 
	 	{
			 System.out.printf("%s%n", e.getMessage());
			 try 
			 {
				 stmt.close();
			 }
			 catch (Exception err) 
			 {
				 
			 }
			 try 
			 {
				 conn.rollback();
			 }
			 catch (Exception err) 
			 {
				 
			 }
		}
	}
	
	/*public static void main (String[] args)
	{
		DatabaseConnection ricRacers = new LocalDatabase(null, null);
		ricRacers.showPlayerStats("jackson123");
	}*/

}
