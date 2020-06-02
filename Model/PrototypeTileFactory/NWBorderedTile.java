package Model.PrototypeTileFactory;

/**
 * <H1>North West Bordered Tile</H1>
 * <p>
 * A Tile with its north and west sides blocked
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-03
 */
public class NWBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the constructor of super class and passes the north and west sides as blocked.
	 * 
	 * @since 1.0
	 */
	public NWBorderedTile() 
	{
		super(false, true, true, false);
	}

	/**
	 * Overrides the goNorthWest method of the parent class. 
	 * Set to return false every time because the Tile is bordered at the north and west sides.
	 * 
	 * @return FALSE every time
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goNorthWest()
	{
		return false;
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
	
	/**
	 * Overrides the goWest method of the parent class. 
	 * Set to return false every time because the Tile is bordered at the west side.
	 * 
	 * @return FALSE every time
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goWest()
	{
		return false;
	}
	
}
