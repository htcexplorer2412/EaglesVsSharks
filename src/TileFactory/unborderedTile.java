package TileFactory;

/*
 * Unbordered tile - occupier can move in any direction from this tile
 */
public class unborderedTile extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public unborderedTile()
	{
		super();
		this.east = true;
		this.west = true;
		this.north = true;
		this.south = true;
	}
	
	@Override
	public boolean goWest() {
		// TODO Auto-generated method stub
		if(this.westNeighbour != null)
		{
			if(this.westNeighbour.isEastOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goEast() {
		// TODO Auto-generated method stub
		if(this.eastNeighbour != null)
		{
			if(this.eastNeighbour.isWestOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goNorth() {
		// TODO Auto-generated method stub
		if(this.northNeighbour != null)
		{
			if(this.northNeighbour.isSouthOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goSouth() {
		// TODO Auto-generated method stub
		if(this.southNeighbour != null)
		{
			if(this.southNeighbour.isNorthOpen())
				return true;
		}
			
		return false;
	}

	
}
