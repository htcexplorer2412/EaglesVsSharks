package TileFactory;

public class SEBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SEBorderedTile() 
	{
		super();
		this.east = false;
		this.west = true;
		this.north = true;
		this.south = false;
	}
	
	@Override
	public boolean goSouthEast()
	{
		return false;
	}
	
	@Override
	public boolean goSouth()
	{
		return false;
	}
	
	@Override
	public boolean goEast()
	{
		return false;
	}

}
