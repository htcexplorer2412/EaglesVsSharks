package Model.PrototypeTileFactory;

/**
 * <H1>Unbordered Tile</H1>
 * <p>
 * A Tile with all the sides open.
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-05-02
 */
public class unborderedTile extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the constructor of the super class and passes all the sides as open.
	 * 
	 * @version 1.1
	 * @since 1.0
	 */
	public unborderedTile()
	{
		super(true, true, true, true);
	}
	
	/**
	 * Overrides the goWest method of the parent class. 
	 * Checks if the Tile has a neighbor at the west side.
	 * If it has, then it checks if the east side of the (west) neighbor is open.
	 * If all the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if west neighbor exists and east side of west neighbor is open.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goWest() {
		// TODO Auto-generated method stub
		if(this.getWestNeighbour() != null)
		{
			if(this.getWestNeighbour().isEastOpen())
				return true;
		}
		
		return false;
	}

	/**
	 * Overrides the goEast method of the parent class. 
	 * Checks if the Tile has a neighbor at the east side.
	 * If it has, then it checks if the west side of the (east) neighbor is open.
	 * If all the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if east neighbor exists and west side of east neighbor is open.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goEast() {
		// TODO Auto-generated method stub
		if(this.getEastNeighbour() != null)
		{
			if(this.getEastNeighbour().isWestOpen())
				return true;
		}
		
		return false;
	}

	/**
	 * Overrides the goNorth method of the parent class. 
	 * Checks if the Tile has a neighbor at the north side.
	 * If it has, then it checks if the south side of the (north) neighbor is open.
	 * If all the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if north neighbor exists and south side of north neighbor is open.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goNorth() {
		// TODO Auto-generated method stub
		if(this.getNorthNeighbour() != null)
		{
			if(this.getNorthNeighbour().isSouthOpen())
				return true;
		}
		
		return false;
	}

	/**
	 * Overrides the goSouth method of the parent class. 
	 * Checks if the Tile has a neighbor at the south side.
	 * If it has, then it checks if the north side of the (south) neighbor is open.
	 * If all the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if south neighbor exists and north side of south neighbor is open.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goSouth() {
		// TODO Auto-generated method stub
		if(this.getSouthNeighbour() != null)
		{
			if(this.getSouthNeighbour().isNorthOpen())
				return true;
		}
			
		return false;
	}

	
}
