package Model;

import java.util.HashMap;

/**
 * <H1>Player Registry</H1>
 * <p>
 * This class stores the Player objects that are created at the start of the game in a HashMap. 
 * These objects are used by different classes during the course of the game.
 * 
 * @author	Ayam Ajmera
 * @version	1.0
 * @since	2020-05-13
 *
 */
public class PlayerRegistry {
	//Add a mapping for Player -> client and Player -> AI
	private static HashMap<Integer, Player> playerTeamMapping = new HashMap<Integer, Player>();
	
	/**
	 * This method is called during the Team selection phase before the game starts. 
	 * When Player 1 selects the team, a Player object is created with that team. Player 2 is alloted the other team.
	 * 
	 * @param nameSelected A string which says Eagle or Shark
	 */
	public static void setPlayerTeams(String nameSelected) 
	{
		if(nameSelected.equals("Eagle"))
		{
			//System.out.println("Eagle selected");
			playerTeamMapping.put(1, new Player('e'));
			playerTeamMapping.put(2, new Player('s'));
		}
		else
		{
			//System.out.println("Shark selected");
			playerTeamMapping.put(1, new Player('s'));
			playerTeamMapping.put(2, new Player('e'));
		}
	} 
	
	public static void loadPlayers(Player player1, Player player2)
	{
		playerTeamMapping.put(1, player1);
		playerTeamMapping.put(2, player2);
	}
	
	/**
	 * This method returns the concerned Player's team from the HashMap.
	 * 
	 * @param player true to receive Player 1's team, false for Player 2's team
	 * @return character which defines the team of the Player requested.
	 */
	public static char getPlayerTeam(boolean player)
	{
		if(player)
		{
			return playerTeamMapping.get(1).getTeam();
		}
		else
		{
			return playerTeamMapping.get(2).getTeam();
		}
	}
	
	/**
	 * This method returns the concerned Player object from the HashMap.
	 * 
	 * @param player true to receive Player 1 object, false for Player 2 object
	 * @return Player object according to the input parameter
	 */
	public static Player getPlayerObj(boolean player)
	{
		if(player)
		{
			return playerTeamMapping.get(1);
		}
		else
		{
			return playerTeamMapping.get(2);
		}
	}
}
