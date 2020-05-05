package TileFactory;

public class SWBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SWBorderedTile() 
	{
		super();
		this.east = true;
		this.west = false;
		this.north = true;
		this.south = false;
	}

	@Override
	public boolean goSouthWest()
	{
		return false;
	}
	
	@Override
	public boolean goSouth()
	{
		return false;
	}
	
	@Override
	public boolean goWest()
	{
		return false;
	}

}
