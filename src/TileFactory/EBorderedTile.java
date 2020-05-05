package TileFactory;

public class EBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EBorderedTile() 
	{
		super();
		this.east = false;
		this.west = true;
		this.north = true;
		this.south = true;
	}

	@Override
	public boolean goEast() {
		return false;
	}

}
