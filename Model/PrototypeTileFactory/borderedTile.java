package Model.PrototypeTileFactory;
/*
 * Abstract class. Initialize a subclass to get specific border for a tile
 */

public abstract class borderedTile extends Tile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	borderedTile(boolean north, boolean east, boolean south, boolean west) 
	{
		super(north, east, south, west);
	}
	
	@Override
	public boolean goWest() {
		// TODO Auto-generated method stub
		if(this.getWestNeighbour() != null)
		{
			if(this.isWestOpen() && this.getWestNeighbour().isEastOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goEast() {
		// TODO Auto-generated method stub
		if(this.getEastNeighbour() != null)
		{
			if(this.isEastOpen() && this.getEastNeighbour().isWestOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goNorth() {
		// TODO Auto-generated method stub
		if(this.getNorthNeighbour() != null)
		{
			if(this.isNorthOpen() && this.getNorthNeighbour().isSouthOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goSouth() {
		// TODO Auto-generated method stub
		if(this.getSouthNeighbour() != null)
		{
			if(this.isSouthOpen() && this.getSouthNeighbour().isNorthOpen())
				return true;
		}
		
		return false;
	}

}
