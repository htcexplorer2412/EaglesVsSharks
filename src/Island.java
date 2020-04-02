
public class Island {
	private String islandType;
	private String[] locationArray = new String[4];
	private String[] entryPointArray = new String[2];
	private char ownerTeam;
	private int defenseUpgradeAttribute;
	
	//Island type - 2x1 or 2x2 (remove if not useful)
	//ownerTeam - e - Eagle team, s - shark team
	//defUpgrade - +10 or +20
	//x1, x2, x3, x4, y1, y2, y3, y4 - location of island on board (either this class or tile class will store the info. Currently stored in both classes, so it can be used internally)
	public Island(String islandType, char ownerTeam, int defUpgrade, int x1, int y1, int x2, int y2)
	{
		this.islandType = islandType;
		this.ownerTeam = ownerTeam;
		this.defenseUpgradeAttribute = defUpgrade;
		locationArray[0] = x1 + "-" + y1;
		locationArray[1] = x2 + "-" + y2;
	}
	
	public Island(String islandType, char ownerTeam, int defUpgrade, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
	{
		super();
		locationArray[2] = x3 + "-" + y3;
		locationArray[3] = x4 + "-" + y4;
	}
	
	public void changeOwner(char ownerTeam)
	{
		if(this.ownerTeam != ownerTeam)
		{
			this.ownerTeam = ownerTeam;
		}
	}
	
	public char whoOwns()
	{
		return ownerTeam;
	}
	
	//This box will be outside the island, just besides the island
	public void setEntryPoints(int x1, int y1, int x2, int y2)
	{
		entryPointArray[0] = x1 + "-" + y1;
		entryPointArray[1] = x2 + "-" + y2;
	}
	
	public String[] getEntryPoints()
	{
		return entryPointArray;
	}
	
	public int getDefenseUpgradeAttribute()
	{
		return defenseUpgradeAttribute;
	}
	
	
}
