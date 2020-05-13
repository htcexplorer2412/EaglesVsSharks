package Model.PrototypeTileFactory;

public class SEBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SEBorderedTile() 
	{
		super(true, false, false, true);
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
