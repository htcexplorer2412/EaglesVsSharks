package Model.PrototypeTileFactory;

import java.io.Serializable;

import Model.Island;
import Model.Piece.Piece;

/*
 * Abstract Tile class which is the base of the prototypical tiles.
 * Unbordered and Bordered tile extend the behaviour of this class.
 * Clone the subclasses to use them in the application.
 */
/**
 * <H1>Tile</H1>
 * <p>
 * An abstract Tile class which is the base of the prototypical tiles.
 * <p>
 * The subclasses of this class extend the behavior of this class. 
 * Unbordered and Bordered Tile extend the Tile class and set the behavior accordingly. 
 * Bordered Tile blocks the occupier from moving in certain direction whereas Unbordered Tile lets the occupier move in any direction.
 * <br>This class is based on Prototype design pattern and hence it is clone-able. 
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-05-02
 */
public abstract class Tile implements Cloneable, Serializable 
{	
	private static final long serialVersionUID = 1L;
	/**
	 * Stores the Tile object that is at the north side of this Tile.
	 */
	private Tile northNeighbour;
	/**
	 * Stores the Tile object that is at the south side of this Tile.
	 */
	private Tile southNeighbour;
	/**
	 * Stores the Tile object that is at the east side of this Tile.
	 */
	private Tile eastNeighbour;
	/**
	 * Stores the Tile object that is at the west side of this Tile.
	 */
	private Tile westNeighbour;
	/**
	 * TRUE if east side is open
	 */
	private boolean east; 
	/**
	 * TRUE if west side is open
	 */
	private boolean west; 
	/**
	 * TRUE if north side is open
	 */
	private boolean north; 
	/**
	 * TRUE if south side is open
	 */
	private boolean south;
	/**
	 * Store the Piece object that occupies this Tile. Null if there is no occupier.
	 */
	private Piece occupier;
	/**
	 * TRUE if this Tile is a part of an Island
	 */
	private boolean isIsland;
	/**
	 * Store the Island object if it belongs to an island. Null if it isn't a part of an Island.
	 */
	private Island islandObj;
	
	/**
	 * Sets the values of all the sides according to the input parameters. 
	 * Initially there are no occupiers so it is set to null.
	 * Also neighbors are to be set using the setters, so they are set to null.
	 * Island information is also to be set by setter methods, so they are set to null/false
	 * 
	 * @param north - TRUE if north side is open.
	 * @param east - TRUE if east side is open.
	 * @param south - TRUE if south side is open.
	 * @param west - TRUE if west side is open.
	 * @since 1.0
	 */
	Tile(boolean north, boolean east, boolean south, boolean west)
	{
		this.occupier = null;
		this.northNeighbour = null; 
		this.southNeighbour = null; 
		this.eastNeighbour = null; 
		this.westNeighbour = null;
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
		this.isIsland = false;
		this.islandObj = null;
	}
	
	/**
	 * Makes a shallow clone of the class. 
	 * 
	 * @return A clone of this instance.
	 * @throws CloneNotSupportedException
	 */
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
	
	/*
	 * Check if occupier can go in any direction i.e. the direction isn't blocked by borders.
	 */
	public abstract boolean goWest();
	
	public abstract boolean goEast();
	
	public abstract boolean goNorth();
	
	public abstract boolean goSouth();
	
	/**
	 * Checks if the Tile has a neighbor at the north and east side.
	 * If it has, then it checks if the occupier can move east and then north or move north and then east.
	 * If both the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if north and east neighbors exist and occupier can move either east and then north or north and then east.
	 * <br>FALSE otherwise
	 * @version 1.1
	 * @since 1.0
	 */
	public boolean goNorthEast() {
		// TODO Auto-generated method stub
		if(this.northNeighbour != null && this.eastNeighbour != null)
		{
			if(goNorth() && this.northNeighbour.goEast())
				return true;
			
			if(goEast() && this.eastNeighbour.goNorth())
				return true;
		}
		
		return false;
	}

	/**
	 * Checks if the Tile has a neighbor at the north and west side.
	 * If it has, then it checks if the occupier can move west and then north or move north and then west.
	 * If both the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if north and west neighbors exist and occupier can move either west and then north or north and then west.
	 * <br>FALSE otherwise
	 * @version 1.1
	 * @since 1.0
	 */
	public boolean goNorthWest() {
		// TODO Auto-generated method stub
		if(this.northNeighbour != null && this.westNeighbour != null)
		{
			if(goNorth() && this.northNeighbour.goWest())
				return true;
		
			if(goWest() && this.westNeighbour.goNorth())
				return true;
		}
		
		return false;
	}

	/**
	 * Checks if the Tile has a neighbor at the south and east side.
	 * If it has, then it checks if the occupier can move east and then south or move south and then east.
	 * If both the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if south and east neighbors exist and occupier can move either east and then south or south and then east.
	 * <br>FALSE otherwise
	 * @version 1.1
	 * @since 1.0
	 */
	public boolean goSouthEast() {
		// TODO Auto-generated method stub
		if(this.southNeighbour != null && this.eastNeighbour != null)
		{
			if(goSouth() && this.southNeighbour.goEast())
				return true;
		
			if(goEast() && this.eastNeighbour.goSouth())
				return true;
		}
		
		return false;
	}

	/**
	 * Checks if the Tile has a neighbor at the south and west side.
	 * If it has, then it checks if the occupier can move west and then south or move south and then west.
	 * If both the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if south and west neighbors exist and occupier can move either west and then south or south and then west.
	 * <br>FALSE otherwise
	 * @version 1.1
	 * @since 1.0
	 */
	public boolean goSouthWest() {
		// TODO Auto-generated method stub
		if(this.southNeighbour != null && this.westNeighbour != null)
		{
			if(goSouth() && this.southNeighbour.goWest())
				return true;
		
			if(goWest() && this.westNeighbour.goSouth())
				return true;
		}
		
		return false;
	}
	
	/*
	 * Setters and getters for borders and neighbouring tiles
	 */
	
	/**
	 * Set if west is open or not
	 * 
	 * @param value TRUE if west should be open, FALSE if west should be blocked.
	 * @version 1.0
	 * @since 1.0
	 */
	public void setWest(boolean value)
	{
		this.west = value;
	}
	
	/**
	 * Set if east is open or not
	 * 
	 * @param value TRUE if east should be open, FALSE if east should be blocked.
	 * @version 1.0
	 * @since 1.0
	 */
	public void setEast(boolean value)
	{
		this.east = value;
	}
	
	/**
	 * Set if north is open or not
	 * 
	 * @param value TRUE if north should be open, FALSE if north should be blocked.
	 * @version 1.0
	 * @since 1.0
	 */
	public void setNorth(boolean value)
	{
		this.north = value;
	}
	
	/**
	 * Set if west is open or not
	 * 
	 * @param value TRUE if south should be open, FALSE if south should be blocked.
	 * @version 1.0
	 * @since 1.0
	 */
	public void setSouth(boolean value)
	{
		this.south = value;
	}
	
	/**
	 * Returns if west side is open or not
	 * 
	 * @return TRUE if west side is open<br>FALSE if west side is blocked.
	 * @version 1.0
	 * @since 1.0
	 */
	public boolean isWestOpen() 
	{
		return this.west;
	}

	/**
	 * Returns if east side is open or not
	 * 
	 * @return TRUE if east side is open<br>FALSE if east side is blocked.
	 * @version 1.0
	 * @since 1.0
	 */
	public boolean isEastOpen() 
	{
		return this.east;
	}

	/**
	 * Returns if north side is open or not
	 * 
	 * @return TRUE if north side is open<br>FALSE if north side is blocked.
	 * @version 1.0
	 * @since 1.0
	 */
	public boolean isNorthOpen() 
	{
		return this.north;
	}

	/**
	 * Returns if south side is open or not.
	 * 
	 * @return TRUE if south side is open<br>FALSE if south side is blocked.
	 * @version 1.0
	 * @since 1.0
	 */
	public boolean isSouthOpen() 
	{
		return this.south;
	}
	
	/**
	 * Set a neighbor Tile on north side. By default, set to NULL
	 * 
	 * @param t - Tile that is on north side of this tile
	 * @version 1.0
	 * @since 1.0
	 */
	public void setNorthNeighbour(Tile t)
	{
		this.northNeighbour = t;
	}
	
	/**
	 * Set a neighbor Tile on west side. By default, set to NULL
	 * 
	 * @param t - Tile that is on west side of this tile
	 * @version 1.0
	 * @since 1.0
	 */
	public void setWestNeighbour(Tile t)
	{
		this.westNeighbour = t;
	}
	
	/**
	 * Set a neighbor Tile on east side. By default, set to NULL
	 * 
	 * @param t - Tile that is on east side of this tile
	 * @version 1.0
	 * @since 1.0
	 */
	public void setEastNeighbour(Tile t)
	{
		this.eastNeighbour = t;
	}
	
	/**
	 * Set a neighbor Tile on south side. By default, set to NULL
	 * 
	 * @param t - Tile that is on south side of this tile
	 * @version 1.0
	 * @since 1.0
	 */
	public void setSouthNeighbour(Tile t)
	{
		this.southNeighbour = t;
	}
	
	/**
	 * Get the Tile that is on north side of this Tile. By default, set to NULL
	 * 
	 * @return Tile object that is on north side of this Tile
	 * @version 1.0
	 * @since 1.0
	 */
	public Tile getNorthNeighbour()
	{
		return this.northNeighbour;
	}
	
	/**
	 * Get the Tile that is on west side of this Tile. By default, set to NULL
	 * 
	 * @return Tile object that is on west side of this Tile
	 * @version 1.0
	 * @since 1.0
	 */
	public Tile getWestNeighbour()
	{
		return this.westNeighbour;
	}
	
	/**
	 * Get the Tile that is on east side of this Tile. By default, set to NULL
	 * 
	 * @return Tile object that is on east side of this Tile
	 * @version 1.0
	 * @since 1.0
	 */
	public Tile getEastNeighbour()
	{
		return this.eastNeighbour;
	}
	
	/**
	 * Get the Tile that is on south side of this Tile. By default, set to NULL
	 * 
	 * @return Tile object that is on south side of this Tile
	 * @version 1.0
	 * @since 1.0
	 */
	public Tile getSouthNeighbour()
	{
		return this.southNeighbour;
	}
	
	/**
	 * Set a piece as the occupier of this Tile. If NULL is passed then the Tile is not occupied by any Piece.
	 * By default, set to NULL
	 * 
	 * @param p - Piece (object) that will occupy this Tile, NULL if a piece is leaving the Tile.
	 * @version 1.0
	 * @since 1.0
	 */
	public void setOccupier(Piece p)
	{
		this.occupier = p;
	}
	
	/**
	 * Returns the Piece object that is occupying the Tile. If no piece is occupying the tile then returns NULL. 
	 * By default, set to NULL
	 * 
	 * @return Piece (object) that is occupying this Tile.<br>NULL if there is no piece on this Tile.
	 * @version 1.0
	 * @since 1.0
	 */
	public Piece getOccupier()
	{
		return this.occupier;
	}
	
	/**
	 * Set this tile as a part of an Island. By default, set to FALSE
	 * 
	 * @param value - TRUE if it should be a part of an Island. FALSE otherwise.
	 * @version 1.0
	 * @since 1.1
	 */
	public void setAsIsland(boolean value)
	{
		this.isIsland = value;
	}
	
	/**
	 * Returns if this tile is a part of an Island or not. By default, set to FALSE
	 * 
	 * @return TRUE if it is part of an Island.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.1
	 */
	public boolean isIsland()
	{
		return this.isIsland;
	}
	
	/**
	 * Set the reference to the Island object this Tile belongs to. By default, set to NULL
	 * 
	 * @param i - Island object this Tile belongs to. 
	 * @version 1.0
	 * @since 1.1
	 */
	public void setIslandObj(Island i)
	{
		if(this.isIsland())
			this.islandObj = i;
		else
			this.islandObj = null;
	}
	
	/**
	 * Returns the Island object this Tile belongs to. By default, set to NULL
	 * 
	 * @return Island object if this Tile belongs to an Island.<br>NULL otherwise.
	 * @version 1.0
	 * @since 1.1
	 */
	public Island getIslandObj()
	{
		return this.islandObj;
	}
	
}
