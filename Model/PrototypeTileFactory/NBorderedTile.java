package Model.PrototypeTileFactory;

/**
 * <H1>North Bordered Tile</H1>
 * <p>
 * A Tile with its north side blocked
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-03
 */
public class NBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the constructor of super class and passes the north side as blocked.
	 * 
	 * @since 1.0
	 */
	public NBorderedTile()
	{
		super(false, true, true, true);
	}
	
	/**
	 * Overrides the goNorth method of the parent class. 
	 * Set to return false every time because the Tile is bordered at the north side.
	 * 
	 * @return FALSE every time
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goNorth()
	{
		return false;
	}
	
}
