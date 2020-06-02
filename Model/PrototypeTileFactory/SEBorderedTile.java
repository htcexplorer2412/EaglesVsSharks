package Model.PrototypeTileFactory;

/**
 * <H1>South East Bordered Tile</H1>
 * <p>
 * A Tile with its south and east sides blocked
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-03
 */
public class SEBorderedTile extends borderedTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the constructor of super class and passes the south and east sides as blocked.
	 * 
	 * @since 1.0
	 */
	public SEBorderedTile() 
	{
		super(true, false, false, true);
	}
	
	/**
	 * Overrides the goSouthEast method of the parent class. 
	 * Set to return false every time because the Tile is bordered at the south and east sides.
	 * 
	 * @return FALSE every time
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goSouthEast()
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
