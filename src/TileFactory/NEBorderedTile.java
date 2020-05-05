package TileFactory;

public class NEBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NEBorderedTile() 
	{
		super();
		this.east = false;
		this.west = true;
		this.north = false;
		this.south = true;
	}

	@Override
	public boolean goNorthEast()
	{
		return false;
	}
	
	@Override
	public boolean goNorth()
	{
		return false;
	}
	
	@Override
	public boolean goEast()
	{
		return false;
	}
	
}
