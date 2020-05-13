package Model.PrototypeTileFactory;

public class WBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WBorderedTile() 
	{
		super(true, true, true, false);
	}

	@Override
	public boolean goWest() 
	{
		return false;
	}

}
