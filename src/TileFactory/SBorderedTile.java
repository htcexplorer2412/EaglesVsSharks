package TileFactory;

public class SBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SBorderedTile() 
	{
		super();
		this.east = true;
		this.west = true;
		this.north = true;
		this.south = false;
	}

	@Override
	public boolean goSouth() {
		return false;
	}
	
}
