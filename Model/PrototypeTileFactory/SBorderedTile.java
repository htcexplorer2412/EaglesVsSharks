package Model.PrototypeTileFactory;

public class SBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SBorderedTile() 
	{
		super(true, true, false, true);
	}

	@Override
	public boolean goSouth() {
		return false;
	}
	
}
