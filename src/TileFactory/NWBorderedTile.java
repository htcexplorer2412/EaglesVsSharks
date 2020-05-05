package TileFactory;

public class NWBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NWBorderedTile() 
	{
		super();
		this.east = true;
		this.west = false;
		this.north = false;
		this.south = true;
	}

	@Override
	public boolean goNorthWest()
	{
		return false;
	}
	
	@Override
	public boolean goNorth()
	{
		return false;
	}
	
	@Override
	public boolean goWest()
	{
		return false;
	}
	
}
