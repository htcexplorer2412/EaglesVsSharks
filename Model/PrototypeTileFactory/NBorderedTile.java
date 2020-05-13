package Model.PrototypeTileFactory;

public class NBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NBorderedTile()
	{
		super(false, true, true, true);
	}
	
	@Override
	public boolean goNorth()
	{
		return false;
	}
	
}
