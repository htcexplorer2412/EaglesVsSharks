package Model.PrototypeTileFactory;

import java.io.Serializable;

/*
 * Abstract Tile class which is the base of the prototypical tiles.
 * Unbordered and Bordered tile extend the behaviour of this class.
 * Clone the subclasses to use them in the application.
 */

public abstract class Tile implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tile northNeighbour, southNeighbour, eastNeighbour, westNeighbour;
	private boolean east, west, north, south;
	private String occupierName;
	//private Piece occupier;
	
	Tile(boolean north, boolean east, boolean south, boolean west)
	{
		this.occupierName = "";
		this.northNeighbour = null; 
		this.southNeighbour = null; 
		this.eastNeighbour = null; 
		this.westNeighbour = null;
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	
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
	
	public boolean goNorthEast() {
		// TODO Auto-generated method stub
		if(this.northNeighbour != null && this.eastNeighbour != null)
		{
			if(goNorth() && this.northNeighbour.isEastOpen())
				return true;
			
			if(goEast() && this.eastNeighbour.isNorthOpen())
				return true;
		}
		
		return false;
	}

	public boolean goNorthWest() {
		// TODO Auto-generated method stub
		if(this.northNeighbour != null && this.westNeighbour != null)
		{
			if(goNorth() && this.northNeighbour.isWestOpen())
				return true;
		
			if(goWest() && this.westNeighbour.isNorthOpen())
				return true;
		}
		
		return false;
	}

	public boolean goSouthEast() {
		// TODO Auto-generated method stub
		if(this.southNeighbour != null && this.eastNeighbour != null)
		{
			if(goSouth() && this.southNeighbour.isEastOpen())
				return true;
		
			if(goEast() && this.eastNeighbour.isSouthOpen())
				return true;
		}
		
		return false;
	}

	public boolean goSouthWest() {
		// TODO Auto-generated method stub
		if(this.southNeighbour != null && this.westNeighbour != null)
		{
			if(goSouth() && this.southNeighbour.isWestOpen())
				return true;
		
			if(goWest() && this.westNeighbour.isSouthOpen())
				return true;
		}
		
		return false;
	}
	
	/*
	 * Setters and getters for borders and neighbouring tiles
	 */
	
	public void setWest(boolean value)
	{
		this.west = value;
	}
	
	public void setEast(boolean value)
	{
		this.east = value;
	}
	
	public void setNorth(boolean value)
	{
		this.north = value;
	}
	
	public void setSouth(boolean value)
	{
		this.south = value;
	}
	
	public boolean isWestOpen() 
	{
		return this.west;
	}

	public boolean isEastOpen() 
	{
		return this.east;
	}

	public boolean isNorthOpen() 
	{
		return this.north;
	}

	public boolean isSouthOpen() 
	{
		return this.south;
	}
	
	public void setNorthNeighbour(Tile t)
	{
		this.northNeighbour = t;
	}
	
	public void setWestNeighbour(Tile t)
	{
		this.westNeighbour = t;
	}
	
	public void setEastNeighbour(Tile t)
	{
		this.eastNeighbour = t;
	}
	
	public void setSouthNeighbour(Tile t)
	{
		this.southNeighbour = t;
	}
	
	public Tile getNorthNeighbour()
	{
		return this.northNeighbour;
	}
	
	public Tile getWestNeighbour()
	{
		return this.westNeighbour;
	}
	
	public Tile getEastNeighbour()
	{
		return this.eastNeighbour;
	}
	
	public Tile getSouthNeighbour()
	{
		return this.southNeighbour;
	}
	
	public void setOccupierName(String name)
	{
		this.occupierName = name;
	}
	
	public String getOccupierName()
	{
		return this.occupierName;
	}
}
