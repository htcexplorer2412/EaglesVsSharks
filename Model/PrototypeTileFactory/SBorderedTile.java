package Model.PrototypeTileFactory;

/**
 * <H1>South Bordered Tile</H1>
 * <p>
 * A Tile with its south side blocked
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-03
 */
public class SBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the constructor of super class and passes the south side as blocked.
	 * 
	 * @since 1.0
	 */
	public SBorderedTile() 
	{
		super(true, true, false, true);
	}

	/**
	 * Overrides the goSouth method of the parent class. 
	 * Set to return false every time because the Tile is bordered at the south side.
	 * 
	 * @return FALSE every time
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goSouth() {
		return false;
	}
	
}
