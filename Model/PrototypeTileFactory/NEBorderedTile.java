package Model.PrototypeTileFactory;

public class NEBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NEBorderedTile() 
	{
		super(false, false, true, true);
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
