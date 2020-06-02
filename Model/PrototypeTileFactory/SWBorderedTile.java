package Model.PrototypeTileFactory;

/**
 * <H1>South West Bordered Tile</H1>
 * <p>
 * A Tile with its south and west sides blocked
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-03
 */
public class SWBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the constructor of super class and passes the south and west sides as blocked.
	 * 
	 * @since 1.0
	 */
	public SWBorderedTile() 
	{
		super(true, true, false, false);
	}

	/**
	 * Overrides the goSouthWest method of the parent class. 
	 * Set to return false every time because the Tile is bordered at the south and west sides.
	 * 
	 * @return FALSE every time
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goSouthWest()
	{
		return false;
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
	public boolean goSouth()
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
