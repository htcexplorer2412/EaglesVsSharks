package Model.PrototypeTileFactory;

public class EBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EBorderedTile() 
	{
		super(true, false, true, true);
	}

	@Override
	public boolean goEast() {
		return false;
	}

}
