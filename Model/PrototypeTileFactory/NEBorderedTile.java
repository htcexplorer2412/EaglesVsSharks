package Model.PrototypeTileFactory;

/**
 * <H1>North East Bordered Tile</H1>
 * <p>
 * A Tile with its north and east sides blocked
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-03
 */
public class NEBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the constructor of super class and passes the north and east sides as blocked.
	 * 
	 * @since 1.0
	 */
	public NEBorderedTile() 
	{
		super(false, false, true, true);
	}

	/**
	 * Overrides the goNorthEast method of the parent class. 
	 * Set to return false every time because the Tile is bordered at the north and east sides.
	 * 
	 * @return FALSE every time
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goNorthEast()
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
	 * Overrides the goEast method of the parent class. 
	 * Set to return false every time because the Tile is bordered at the east side.
	 * 
	 * @return FALSE every time
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goEast()
	{
		return false;
	}
	
}
