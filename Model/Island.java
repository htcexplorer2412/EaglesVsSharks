package Model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.java.contract.Requires;

import Model.PrototypeTileFactory.Tile;

/*
 * Store island tile objects in this class and decorate pieces from here.
 */

/**
 * <H1>Island</H1>
 * <p>
 * This class represents Islands on the Board.
 * <p>
 * The class stores the tiles that are combined to make an island. 
 * Island is either owned by a team or it is independent (i.e. It has no owner).
 * There are certain conditions to become an owner of an Island:
 * <ol><li>If an Island isn't owned by anybody then a piece can visit an island and become the owner of this Island.</li>
 * <li>If an Island is owned by a team and a piece from that team tries to enter the Island then there are 2 possibilities. 
 * <ol><li>A piece is already on that Island. In this case, the second piece isn't allowed to enter the island. 
 * Because two pieces from same team cannot be on same island.</li>
 * <li>The island is owned by the same team but the original occupying piece has left the island. 
 * In this case, the piece is allowed to enter the island.</li></ol></li>
 * <li>If an Island is owned by a team and a piece from the opposing team tries to enter the Island 
 * then the piece will be able to enter the island but there are 2 consequences.
 * <ol><li>The piece that was occupying the island, left the island.
 * In this case, the incoming piece will be able to land on the island and this piece and it's team will become the owner of this island.
 * This is because the opposing team wasn't there to challenge the acquisition.</li>
 * <li>The piece that owns the island is residing in the island.
 * In this case, the incoming piece will land on the island and battle with the occupier. Whoever wins the battle will own the island.</li></ol></li></ol>
 *  
 * 
 * @author Ayam Ajmera
 * @version 1.2
 * @since 2020-04-21
 */
public class Island implements Serializable, Cloneable
{
	private static final long serialVersionUID = 1L;
	private char ownerTeam;
	private int defenseUpgradeAttribute;
	/**
	 * Stores the tiles that this island belongs to
	 */
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	//ownerTeam - e - Eagle team, s - shark team
	//defUpgrade - +10 or +20
	
	/**
	 * Initialize the Island with an owner
	 * 
	 * @param ownerTeam character 'e' or 's' (Eagle or Shark respectively)
	 * @version 1.1
	 * @since 1.0
	 */
	@Requires("ownerTeam == 'e' || ownerTeam == 's'")
	public Island(char ownerTeam)
	{
		this.ownerTeam = ownerTeam;
	}
	
	/**
	 * Initialize the Island without any owner
	 * 
	 * @version 1.0
	 * @since 1.2
	 */
	public Island()
	{
		this.ownerTeam = ' ';
	}
	
	/**
	 * Add the tile that this island belongs to. Adds the object to the ArrayList
	 * 
	 * @param t Tile object
	 * @version 1.0
	 * @since 1.2
	 */
	public void addTile(Tile t)
	{
		tiles.add(t);
	}
	
	/**
	 * Set defense upgrade according to the size of the island
	 * 
	 * @version 1.0
	 * @since 1.2
	 */
	public void setDefenseUpgradeAttribute()
	{
		if(this.tiles.size() == 2)
		{
			this.defenseUpgradeAttribute = 10;
		}
		if(this.tiles.size() == 4)
		{
			this.defenseUpgradeAttribute = 20;
		}
	}
	
	/**
	 * Change the owner of the island
	 * 
	 * @param ownerTeam character 'e' or 's' (Eagle or Shark respectively)
	 * @version 1.1
	 * @since 1.0
	 */
	public void changeOwner(char ownerTeam)
	{
		if(this.ownerTeam == ' ')
		{
			this.ownerTeam = ownerTeam;
		}
		else
		{
			this.ownerTeam = ownerTeam;
		}
	}
	
	/**
	 * Returns the information about who owns this island
	 * 
	 * @return 'e' or 's' for Eagle and Shark respectively
	 * @version 1.0
	 * @since 1.0
	 */
	public char whoOwns()
	{
		return ownerTeam;
	}
	
	/**
	 * Check if the piece that is trying to enter the island is from same team or not. 
	 * Returns the result according to the state of the island. i.e. If island is occupied or not and who occupies it.
	 * 
	 * @param enteringTeam team the entering piece belongs to.
	 * @return STCE - If piece from same team is trying to enter and the original occupier has left the island. Piece should be allowed to enter<br>
	 * STCN - If piece from same team is trying to enter and the original occupier is still in the island. 
	 * This means that the piece shouldn't be able to enter the island.<br>
	 * CO - If the island isn't occupied by any team. Piece should be able to enter and own the island.<br>
	 * COO - If the piece from opposing team is trying to enter and the original occupier has left the island. 
	 * Piece should be allowed to enter the island and ownership should be changed<br>
	 * IB - If the piece from opposing team is trying to enter and the original occupier is still in the island.
	 * Initiate battle between the entering piece and occupying piece.<br>
	 * 
	 * @version 1.0
	 * @since 1.2
	 */
	@Requires("ownerTeam == 'e' || ownerTeam == 's'")
	public String checkIslandOccupier(char enteringTeam)
	{
		if(ownerTeam == enteringTeam)
		{
			if(checkTiles())
				return "STCE";						//STCE - Same team, can enter - This is because defender has left the island
			else
				return "STCN";						//STCN - Same team, cannot enter - Island is already being defended by a piece of same team
		}
		else if(ownerTeam == ' ')
		{
			return "CO";							//CO - Can be occupied - Island isn't owned by anyone, so whoever enters owns the island
		}
		else
		{
			if(checkTiles())
				return "COO";						//COO - Can be occupied from opposition - Opposing team defender left the island, so it is open for occupation now
			else
				return "IB";						//IB - Initiate Battle - Defender is up against the attacker.
		}
	}
	
	/**
	 * Check the tiles for any occupier.
	 * 
	 * @return TRUE if there are no occupiers<br>FALSE if there is an occupier.<br>Here occupier means a "Piece" and not team.
	 */
	private boolean checkTiles()
	{
		for(int i = 0; i < tiles.size(); i++)
		{
			if(tiles.get(i).getOccupier() != null)
			{
				return false;
			}
		}
		
		return true;
	}
	
	/*public int getDefenseUpgradeAttribute()
	{
		return defenseUpgradeAttribute;
	}*/
	
	public Object clone()
	{
		Object clone = null;
		
		try 
		{
			clone = super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return clone;
	}
	
	public void restoreToOlderState(Island i)
	{
		this.ownerTeam = i.whoOwns();
	}
}
