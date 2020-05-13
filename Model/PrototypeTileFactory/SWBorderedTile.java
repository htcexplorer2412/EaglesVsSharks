package Model.PrototypeTileFactory;

public class SWBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SWBorderedTile() 
	{
		super(true, true, false, false);
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
