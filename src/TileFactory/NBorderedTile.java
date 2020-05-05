package TileFactory;

public class NBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NBorderedTile()
	{
		super();
		this.east = true;
		this.west = true;
		this.north = false;
		this.south = true;
	}
	
	@Override
	public boolean goNorth()
	{
		return false;
	}
	
}
