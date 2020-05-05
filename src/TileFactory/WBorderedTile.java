package TileFactory;

public class WBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WBorderedTile() 
	{
		super();
		this.east = true;
		this.west = false;
		this.north = true;
		this.south = true;
	}

	@Override
	public boolean goWest() 
	{
		return false;
	}

}
