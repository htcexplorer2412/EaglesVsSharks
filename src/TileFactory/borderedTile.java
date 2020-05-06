package TileFactory;
/*
 * Abstract class. Initialize a subclass to get specific border for a tile
 */

public abstract class borderedTile extends Tile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean goWest() {
		// TODO Auto-generated method stub
		if(this.westNeighbour != null)
		{
			if(this.west && this.westNeighbour.isEastOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goEast() {
		// TODO Auto-generated method stub
		if(this.eastNeighbour != null)
		{
			if(this.east && this.eastNeighbour.isWestOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goNorth() {
		// TODO Auto-generated method stub
		if(this.northNeighbour != null)
		{
			if(this.north && this.northNeighbour.isSouthOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goSouth() {
		// TODO Auto-generated method stub
		if(this.southNeighbour != null)
		{
			if(this.south && this.southNeighbour.isNorthOpen())
				return true;
		}
		
		return false;
	}

}
