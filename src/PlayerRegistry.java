import java.util.HashMap;

public class PlayerRegistry {
	
	private static HashMap<Integer, Player> playerTeamMapping = new HashMap<Integer, Player>();
	
	public static void setPlayerTeams(String nameSelected) 
	{
		if(nameSelected.equals("Eagle"))
		{
			System.out.println("Eagle selected");
			playerTeamMapping.put(1, new Player('e'));
			playerTeamMapping.put(2, new Player('s'));
		}
		else
		{
			System.out.println("Shark selected");
			playerTeamMapping.put(1, new Player('s'));
			playerTeamMapping.put(2, new Player('e'));
		}
	} 
	
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
