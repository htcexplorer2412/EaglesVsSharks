package Model.PrototypeTileFactory;

public class NWBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NWBorderedTile() 
	{
		super(false, true, true, false);
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
