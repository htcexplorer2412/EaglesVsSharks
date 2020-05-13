package Model.PrototypeTileFactory;

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
		super(true, true, true, true);
	}
	
	@Override
	public boolean goWest() {
		// TODO Auto-generated method stub
		if(this.getWestNeighbour() != null)
		{
			if(this.getWestNeighbour().isEastOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goEast() {
		// TODO Auto-generated method stub
		if(this.getEastNeighbour() != null)
		{
			if(this.getEastNeighbour().isWestOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goNorth() {
		// TODO Auto-generated method stub
		if(this.getNorthNeighbour() != null)
		{
			if(this.getNorthNeighbour().isSouthOpen())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean goSouth() {
		// TODO Auto-generated method stub
		if(this.getSouthNeighbour() != null)
		{
			if(this.getSouthNeighbour().isNorthOpen())
				return true;
		}
			
		return false;
	}

	
}
